package com.infamous.dungeons_mobs.mobenchants;

import baguchi.enchantwithmob.EnchantWithMob;
import baguchi.enchantwithmob.api.IEnchantCap;
import baguchi.enchantwithmob.mobenchant.MobEnchant;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Consumer;

import static baguchi.enchantwithmob.utils.MobEnchantUtils.getMobEnchantLevelFromHandler;

public class NewMobEnchantUtils {

    public static void executeIfPresentWithLevel(LivingEntity entity, MobEnchant mobEnchantment, Consumer<Integer> consumer) {
        if (entity != null) {
            if (entity instanceof IEnchantCap enchantCap) {
                int level = getMobEnchantLevelFromHandler(enchantCap.getEnchantCap().getMobEnchants(), mobEnchantment);
                if (level > 0) {
                    consumer.accept(level);
                }
            }
        }
    }
}
