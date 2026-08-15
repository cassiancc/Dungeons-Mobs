package com.infamous.dungeons_mobs.mobenchants;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.bus.api.SubscribeEvent;

import static com.infamous.dungeons_libraries.utils.AreaOfEffectHelper.applyToNearbyEntities;
import static com.infamous.dungeons_libraries.utils.AreaOfEffectHelper.getCanHealPredicate;
import static com.infamous.dungeons_mobs.DungeonsMobs.PROXY;
import static com.infamous.dungeons_mobs.mobenchants.NewMobEnchantUtils.executeIfPresentWithLevel;
import static com.infamous.dungeons_mobs.mod.ModMobEnchants.RADIANCE;

public class RadianceMobEnchant extends MobEnchant {


    public RadianceMobEnchant(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        Entity attacker;
        if (!event.getSource().is(DamageTypeTags.IS_PROJECTILE)) {
            attacker = event.getSource().getDirectEntity();
        } else {
            attacker = event.getSource().getEntity();
        }
        if (attacker instanceof LivingEntity)
            executeIfPresentWithLevel((LivingEntity) attacker, RADIANCE.get(), (level) -> {
                LivingEntity source = event.getSource().is(DamageTypeTags.IS_PROJECTILE) ? event.getEntity() : (LivingEntity) attacker;
                applyToNearbyEntities(source, 1.5F,
                        getCanHealPredicate(source), (LivingEntity nearbyEntity) -> {
                            nearbyEntity.heal(level);
                            PROXY.spawnParticles(nearbyEntity, ParticleTypes.HEART);
                        }
                );
            });
    }
}