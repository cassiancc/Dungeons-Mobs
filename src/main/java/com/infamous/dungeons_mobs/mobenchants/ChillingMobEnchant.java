package com.infamous.dungeons_mobs.mobenchants;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import com.infamous.dungeons_mobs.capabilities.properties.MobProps;
import com.infamous.dungeons_mobs.capabilities.properties.MobPropsHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import static com.infamous.dungeons_libraries.utils.AreaOfEffectHelper.applyToNearbyEntities;
import static com.infamous.dungeons_libraries.utils.AreaOfEffectHelper.getCanApplyToEnemyPredicate;
import static com.infamous.dungeons_mobs.DungeonsMobs.PROXY;
import static com.infamous.dungeons_mobs.mobenchants.NewMobEnchantUtils.executeIfPresentWithLevel;
import static com.infamous.dungeons_mobs.mod.ModMobEnchants.CHILLING;

public class ChillingMobEnchant extends MobEnchant {

    public ChillingMobEnchant(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void OnLivingUpdate(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();

        if (entity instanceof LivingEntity livingEntity) {
            executeIfPresentWithLevel(livingEntity, CHILLING, (level) -> {
                MobProps comboCap = MobPropsHelper.getMobPropsCapability(livingEntity);
                if (comboCap == null) return;
                int freezeNearbyTimer = comboCap.getFreezeNearbyTimer();
                if (freezeNearbyTimer <= 0) {
                    PROXY.spawnParticles(entity, ParticleTypes.ITEM_SNOWBALL);
                    applyToNearbyEntities(livingEntity, 1.5F,
                            getCanApplyToEnemyPredicate(livingEntity), (LivingEntity nearbyEntity) -> {
                                freezeEnemy(1, nearbyEntity, level);
                                PROXY.spawnParticles(nearbyEntity, ParticleTypes.ITEM_SNOWBALL);
                            }
                    );
                    comboCap.setFreezeNearbyTimer(40);
                } else {
                    comboCap.setFreezeNearbyTimer(freezeNearbyTimer - 1);
                }
            });
        }
    }

    private static void freezeEnemy(int amplifier, LivingEntity nearbyEntity, int durationInSeconds) {
        MobEffectInstance slowness = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, durationInSeconds * 20, amplifier);
        MobEffectInstance fatigue = new MobEffectInstance(MobEffects.DIG_SLOWDOWN, durationInSeconds * 20, Math.max(0, amplifier * 2 - 1));
        nearbyEntity.addEffect(slowness);
        nearbyEntity.addEffect(fatigue);
        PROXY.spawnParticles(nearbyEntity, ParticleTypes.ITEM_SNOWBALL);
    }
}