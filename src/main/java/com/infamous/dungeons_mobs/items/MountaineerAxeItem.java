package com.infamous.dungeons_mobs.items;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class MountaineerAxeItem extends PickaxeItem {
    public MountaineerAxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, builder.attributes(PickaxeItem.createAttributes(tier, attackDamageIn, attackSpeedIn)));
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.supportsEnchantment(Items.IRON_SWORD.getDefaultInstance(), enchantment) || super.supportsEnchantment(Items.IRON_AXE.getDefaultInstance(), enchantment) && !enchantment.is(Enchantments.SWEEPING_EDGE);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
}
