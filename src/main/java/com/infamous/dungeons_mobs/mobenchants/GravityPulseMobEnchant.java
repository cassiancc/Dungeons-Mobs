package com.infamous.dungeons_mobs.mobenchants;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import com.infamous.dungeons_mobs.capabilities.properties.MobProps;
import com.infamous.dungeons_mobs.capabilities.properties.MobPropsHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import static com.infamous.dungeons_libraries.utils.AreaOfEffectHelper.applyToNearbyEntities;
import static com.infamous.dungeons_libraries.utils.AreaOfEffectHelper.getCanApplyToEnemyPredicate;
import static com.infamous.dungeons_mobs.DungeonsMobs.PROXY;
import static com.infamous.dungeons_mobs.mobenchants.NewMobEnchantUtils.executeIfPresentWithLevel;
import static com.infamous.dungeons_mobs.mod.ModMobEnchants.GRAVITY_PULSE;

public class GravityPulseMobEnchant extends MobEnchant {

    public static final double PULL_IN_SPEED_FACTOR = 0.1;

    public GravityPulseMobEnchant(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void OnLivingUpdate(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();

        if (entity instanceof LivingEntity livingEntity) {
            executeIfPresentWithLevel(livingEntity, GRAVITY_PULSE, (level) -> {
                MobProps comboCap = MobPropsHelper.getMobPropsCapability(livingEntity);
                if (comboCap == null) return;
                int gravityPulseTimer = comboCap.getGravityPulseTimer();
                if (gravityPulseTimer <= 0) {
                    PROXY.spawnParticles(livingEntity, ParticleTypes.PORTAL);
                    applyToNearbyEntities(livingEntity, 5F,
                            getCanApplyToEnemyPredicate(livingEntity), (LivingEntity nearbyEntity) -> {
                                pullVictimTowardsTarget(livingEntity, nearbyEntity, ParticleTypes.PORTAL, level);
                            }
                    );
                    comboCap.setGravityPulseTimer(100);
                } else {
                    comboCap.setGravityPulseTimer(gravityPulseTimer - 1);
                }
            });
        }
    }

    public static void pullVictimTowardsTarget(LivingEntity target, LivingEntity nearbyEntity, SimpleParticleType particleType, Integer level) {
        double motionX = target.getX() - (nearbyEntity.getX());
        double motionY = target.getY() - (nearbyEntity.getY());
        double motionZ = target.getZ() - (nearbyEntity.getZ());
        Vec3 vector3d = new Vec3(motionX, motionY, motionZ).scale(PULL_IN_SPEED_FACTOR * level);

        nearbyEntity.setDeltaMovement(vector3d);
        PROXY.spawnParticles(nearbyEntity, particleType);
    }
}