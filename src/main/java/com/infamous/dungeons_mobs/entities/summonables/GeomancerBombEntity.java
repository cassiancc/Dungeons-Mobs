package com.infamous.dungeons_mobs.entities.summonables;

import com.infamous.dungeons_mobs.mod.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import static software.bernie.geckolib.animation.Animation.LoopType.LOOP;
import static software.bernie.geckolib.animation.Animation.LoopType.PLAY_ONCE;


public class GeomancerBombEntity extends ConstructEntity implements GeoAnimatable {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final float explosionRadius = 3.0F;

    public GeomancerBombEntity(Level worldIn) {
        super(ModEntityTypes.GEOMANCER_BOMB.get(), worldIn);
    }

    public GeomancerBombEntity(Level worldIn, double x, double y, double z, LivingEntity casterIn, int lifeTicksIn) {
        super(ModEntityTypes.GEOMANCER_BOMB.get(), worldIn, x, y, z, casterIn, lifeTicksIn);
    }

    public GeomancerBombEntity(EntityType<? extends GeomancerBombEntity> explodingPillarEntityEntityType, Level world) {
        super(explodingPillarEntityEntityType, world);
    }

    @Override
    public void handleExpiration() {
        super.handleExpiration();
        if (!this.level().isClientSide) {
            this.explode();
        }
    }

    private void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), this.explosionRadius, Level.ExplosionInteraction.NONE);
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 0.0D).add(Attributes.MOVEMENT_SPEED, 0.0D).add(Attributes.ATTACK_DAMAGE, 0.0D);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));

    }

    private <P extends GeoAnimatable> PlayState predicate(AnimationState<P> event) {
        if (this.getLifeTicks() > 75) {
            event.getController().setAnimation(RawAnimation.begin().then("geomancer_pillar_appear", PLAY_ONCE));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("geomancer_pillar_idle", LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return tickCount;
    }


    //  @Override
    //  public IPacket<?> getAddEntityPacket() {
    //       return NetworkHooks.getEntitySpawningPacket(this);
    //   }
}
