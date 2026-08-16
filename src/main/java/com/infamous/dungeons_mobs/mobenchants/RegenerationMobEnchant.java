package com.infamous.dungeons_mobs.mobenchants;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import static com.infamous.dungeons_mobs.DungeonsMobs.PROXY;
import static com.infamous.dungeons_mobs.mobenchants.NewMobEnchantUtils.executeIfPresentWithLevel;
import static com.infamous.dungeons_mobs.mod.ModMobEnchants.REGENERATION;

public class RegenerationMobEnchant extends MobEnchant {

    public RegenerationMobEnchant(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onLivingUpdate(EntityTickEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            executeIfPresentWithLevel(livingEntity, REGENERATION, (level) -> {
                if (livingEntity.getHealth() < livingEntity.getMaxHealth() && livingEntity.tickCount % getTickCountForLevel(level) == 0) {
                    livingEntity.heal(1.0F);
                    PROXY.spawnParticles(livingEntity, ParticleTypes.HEART);
                }
            });
        }
    }

    private static int getTickCountForLevel(Integer level) {
        return 62 - level * 12;
    }
}