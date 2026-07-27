package com.infamous.dungeons_libraries.utils;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class EnchantmentUtil {
	public static int getItemEnchantmentLevel(ResourceKey<Enchantment> power, ItemStack bowStack, Level world) {
		var enchantmentRegistry = world.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
		Optional<Holder.Reference<Enchantment>> holder = enchantmentRegistry.getHolder(power);
		return holder.map(enchantmentReference -> EnchantmentHelper.getItemEnchantmentLevel(enchantmentReference, bowStack)).orElse(0);
	}
}
