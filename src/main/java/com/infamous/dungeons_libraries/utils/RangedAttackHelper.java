package com.infamous.dungeons_libraries.utils;

import com.infamous.dungeons_libraries.event.BowEvent;
import com.infamous.dungeons_libraries.event.CrossbowEvent;
import com.infamous.dungeons_libraries.items.gearconfig.BowGear;
import com.infamous.dungeons_libraries.items.gearconfig.CrossbowGear;
import com.infamous.dungeons_libraries.mixin.CrossbowItemInvoker;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.infamous.dungeons_libraries.attribute.AttributeRegistry.RANGED_DAMAGE_MULTIPLIER;


public class RangedAttackHelper {

    public static float getBowChargeTime(LivingEntity livingEntity, ItemStack stack) {
        float defaultChargeTime = stack.getItem() instanceof BowGear ? ((BowGear) stack.getItem()).getDefaultChargeTime() : 20.0F;
        int quickChargeLevel = EnchantmentUtil.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack, livingEntity.level());
        float minTime = 1;
        BowEvent.ChargeTime event = new BowEvent.ChargeTime(livingEntity, stack, defaultChargeTime);
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(event);
        return Math.max(event.getChargeTime() - 5 * quickChargeLevel, minTime);
    }

    public static float getVanillaCrossbowChargeTime(@Nullable LivingEntity livingEntity, ItemStack stack) {
        int quickChargeLevel = EnchantmentUtil.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack, livingEntity.level());
        float minTime = 1;
        CrossbowEvent.ChargeTime event = new CrossbowEvent.ChargeTime(livingEntity, stack, 25.0F);
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(event);
        return Math.max(event.getChargeTime() - 5 * quickChargeLevel, minTime);
    }

    public static float getCrossbowChargeTime(@Nullable LivingEntity livingEntity, ItemStack stack) {
        float chargeTime;
        if (stack.getItem() instanceof CrossbowGear crossbowGear) {
            chargeTime = crossbowGear.getCrossbowChargeTime(livingEntity, stack);
        } else {
            chargeTime = getVanillaCrossbowChargeTime(livingEntity, stack);
        }
        return chargeTime;
    }


}
