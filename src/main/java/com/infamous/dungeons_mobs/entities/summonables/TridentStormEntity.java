package com.infamous.dungeons_mobs.entities.summonables;

import com.infamous.dungeons_mobs.mod.ModDamageSources;
import com.infamous.dungeons_mobs.mod.ModSoundEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

import static software.bernie.geckolib.core.animation.Animation.LoopType.LOOP;

public class TridentStormEntity extends Entity implements GeoAnimatable {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public int lifeTime;
    public Entity owner;

    public TridentStormEntity(EntityType<? extends TridentStormEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 1, this::predicate));

    }

    private <P extends GeoAnimatable> PlayState predicate(AnimationState<P> event) {
        event.getController().setAnimation(RawAnimation.begin().then("trident_storm_strike", LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void baseTick() {
        super.baseTick();

        this.refreshDimensions();

        List<Entity> list = this.level().getEntities(this, this.getBoundingBox(), Entity::isAlive);
        if (!list.isEmpty() && !this.level().isClientSide) {
            for (Entity entity : list) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (this.lifeTime >= 80 && this.lifeTime <= 90) {
                        if (this.owner != null) {
                            if (livingEntity != this.owner) {
                                livingEntity.hurt(damageSources().source(ModDamageSources.SUMMONED_TRIDENT_STORM, this, owner), 20);
                            }
                        } else {
                            livingEntity.hurt(damageSources().source(ModDamageSources.TRIDENT_STORM,this), 20);
                        }
                    }
                }

            }
        }

        this.lifeTime++;

        if (this.lifeTime == 80) {
            this.playSound(ModSoundEvents.DROWNED_NECROMANCER_TRIDENT_STORM_HIT.get(), 3.0F, 1.0F);
        }

        if (this.lifeTime >= 500 && !this.level().isClientSide()) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_70037_1_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_213281_1_) {

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public double getTick(Object object) {
        return tickCount;
    }
}
