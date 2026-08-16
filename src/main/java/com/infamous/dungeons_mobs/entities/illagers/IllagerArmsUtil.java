package com.infamous.dungeons_mobs.entities.illagers;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;

public class IllagerArmsUtil {
    public static boolean armorHasCrossedArms(AbstractIllager p_241739_3_, ItemStack itemstack) {
        return !(itemstack.getItem() instanceof ArmorItem) || resourceExists(getArmorResourceStatic(p_241739_3_, itemstack, EquipmentSlot.CHEST));
    }

    private static ResourceLocation getArmorResourceStatic(Entity entity, ItemStack stack, EquipmentSlot slot) {
        ArmorItem item = (ArmorItem) stack.getItem();
        ResourceLocation texture = item.getMaterial().getKey().location();
        ArmorMaterial.Layer defaultString = new ArmorMaterial.Layer(GeneralUtil.loc(String.format("%s:textures/models/armor/%s_layer_%d%s.png", texture.getNamespace(), texture.getPath(), 1, "crossed" == null ? "" : String.format("_%s", "crossed"))));

        ResourceLocation s1 = ClientHooks.getArmorTexture(entity, stack, defaultString, false, slot);
        if (!s1.getPath().endsWith("_crossed.png")) {
            s1 = s1.withPath(p-> p.replace(".png", "_crossed.png"));
        }

        return s1;
    }

    public static boolean resourceExists(ResourceLocation resourceLocation) {
        if (resourceLocation != null) {
            return Minecraft.getInstance().getResourceManager().getResource(resourceLocation).isPresent();

        }
        return false;
    }
}
