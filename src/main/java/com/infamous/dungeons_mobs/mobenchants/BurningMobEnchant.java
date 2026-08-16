package com.infamous.dungeons_mobs.mobenchants;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import com.infamous.dungeons_mobs.capabilities.properties.MobProps;
import com.infamous.dungeons_mobs.capabilities.properties.MobPropsHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import static com.infamous.dungeons_libraries.utils.AreaOfEffectHelper.applyToNearbyEntities;
import static com.infamous.dungeons_libraries.utils.AreaOfEffectHelper.getCanApplyToEnemyPredicate;
import static com.infamous.dungeons_mobs.DungeonsMobs.PROXY;
import static com.infamous.dungeons_mobs.mobenchants.NewMobEnchantUtils.executeIfPresentWithLevel;
import static com.infamous.dungeons_mobs.mod.ModMobEnchants.BURNING;

public class BurningMobEnchant extends MobEnchant {

    public BurningMobEnchant(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void OnLivingUpdate(EntityTickEvent.Post event) {
        Entity e = event.getEntity();
        if (e instanceof LivingEntity entity) {

        executeIfPresentWithLevel(entity, BURNING, (level) -> {
            MobProps comboCap = MobPropsHelper.getMobPropsCapability(entity);
            if (comboCap == null) return;
            int burnNearbyTimer = comboCap.getBurnNearbyTimer();
            if (burnNearbyTimer <= 0) {
                PROXY.spawnParticles(entity, ParticleTypes.FLAME);
                applyToNearbyEntities(entity, 1.5F,
                        getCanApplyToEnemyPredicate(entity), (LivingEntity nearbyEntity) -> {
                            nearbyEntity.hurt(nearbyEntity.damageSources().onFire(), 0.5F * level);
                            PROXY.spawnParticles(nearbyEntity, ParticleTypes.FLAME);
                        }
                );
                comboCap.setBurnNearbyTimer(20);
            } else {
                comboCap.setBurnNearbyTimer(burnNearbyTimer - 1);
            }
        });
    }
    }
}