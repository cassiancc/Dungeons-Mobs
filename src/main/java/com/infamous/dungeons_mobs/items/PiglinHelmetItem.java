package com.infamous.dungeons_mobs.items;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.core.registries.BuiltInRegistries;

import javax.annotation.Nullable;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;

public class PiglinHelmetItem extends ArmorItem {

    public PiglinHelmetItem(Holder<ArmorMaterial> armorMaterial, ArmorItem.Type slotType, Properties properties) {
        super(armorMaterial, slotType, properties);
    }

    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return String.format(MODID + ":textures/models/armor/%s.png", BuiltInRegistries.ITEM.getKey(this).getPath());
    }
}
