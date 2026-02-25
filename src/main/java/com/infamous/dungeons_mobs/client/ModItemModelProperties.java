package com.infamous.dungeons_mobs.client;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.mod.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemModelProperties {

    public static void registerProperties() {
        ItemProperties.register(ModItems.ROYAL_GUARD_SHIELD.get(),
                GeneralUtil.mcLoc("blocking"),
                (stack, clientWorld, livingEntity, i) -> {
                    return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == stack ? 1.0F : 0.0F;
                });
        ItemProperties.register(ModItems.VANGUARD_SHIELD.get(),
                GeneralUtil.mcLoc("blocking"),
                (stack, clientWorld, livingEntity, i) -> {
                    return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == stack ? 1.0F : 0.0F;
                });
        ItemProperties.register(ModItems.YELLOW_TRIDENT.get(),
                GeneralUtil.mcLoc("throwing"),
                (stack, clientWorld, livingEntity, i) -> {
                    return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == stack ? 1.0F : 0.0F;
                });
        ItemProperties.register(ModItems.PURPLE_TRIDENT.get(),
                GeneralUtil.mcLoc("throwing"),
                (stack, clientWorld, livingEntity, i) -> {
                    return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == stack ? 1.0F : 0.0F;
                });
    }
}
