package com.infamous.dungeons_libraries.mixin;

import com.infamous.dungeons_libraries.utils.EnchantmentUtil;
import com.infamous.dungeons_libraries.utils.RangedAttackHelper;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {
	@Inject(method = "shoot",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z", shift = At.Shift.AFTER))
	private void createAdditionalArrows(ServerLevel level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, List<ItemStack> projectileItems, float velocity, float inaccuracy, boolean isCrit, LivingEntity target, CallbackInfo ci, @Local Projectile projectile) {
		if (projectile instanceof AbstractArrow originalArrow && shooter instanceof Player player) {
			// Make some last minute changes to the original arrow
			if(velocity >= 1.0F){
				originalArrow.setCritArrow(true);
			}

			int piercingLevel = EnchantmentUtil.getItemEnchantmentLevel(Enchantments.PIERCING, weapon, level);
			if (piercingLevel > 0) {
				originalArrow.setPierceLevel((byte) piercingLevel);
			}

			// Finished making changes to original arrow - now to the multishot stuff!
			int multishotLevel = EnchantmentUtil.getItemEnchantmentLevel(Enchantments.MULTISHOT, weapon, level);
			if(multishotLevel > 0){
				int additionalArrowCount = multishotLevel * 2;
				for(int arrowIndex = 1; arrowIndex <= additionalArrowCount; arrowIndex++){
					RangedAttackHelper.createBowArrow(weapon, level, player, projectileItems, velocity, arrowIndex, EnchantmentUtil.getItemEnchantmentLevel(Enchantments.INFINITY, weapon, level)>0);
				}
			}
		}

	}
}
