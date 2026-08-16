package com.infamous.dungeons_mobs.mobenchants;

import baguchi.enchantwithmob.EnchantWithMob;
import baguchi.enchantwithmob.api.IEnchantCap;
import baguchi.enchantwithmob.mobenchant.MobEnchant;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Consumer;

import static baguchi.enchantwithmob.utils.MobEnchantUtils.getMobEnchantLevelFromHandler;

public class NewMobEnchantUtils {

    public static void executeIfPresentWithLevel(LivingEntity entity, Holder<MobEnchant> mobEnchantment, Consumer<Integer> consumer) {
        if (entity != null) {
            if (entity instanceof IEnchantCap enchantCap) {
                int level = getMobEnchantLevelFromHandler(enchantCap.getEnchantCap().getMobEnchants(), mobEnchantment.getKey());
                if (level > 0) {
                    consumer.accept(level);
                }
            }
        }
    }
}
