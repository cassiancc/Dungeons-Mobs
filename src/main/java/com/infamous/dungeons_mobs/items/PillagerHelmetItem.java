package com.infamous.dungeons_mobs.items;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.client.models.armor.PillagerHelmetModel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;
import static com.infamous.dungeons_mobs.client.models.geom.ModModelLayers.PILLAGER_HELMET;

public class PillagerHelmetItem extends ArmorItem {
    private final boolean isDiamond;

    public PillagerHelmetItem(Holder<ArmorMaterial> materialIn, ArmorItem.Type slot, Properties builderIn, boolean isDiamondIn) {
        super(materialIn, slot, builderIn);
        this.isDiamond = isDiamondIn;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<net.neoforged.neoforge.client.extensions.common.IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private PillagerHelmetModel model;

            @Nullable
            @Override
            public net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, net.minecraft.client.model.HumanoidModel<?> _default) {
                if (null == model) {
                    model = new PillagerHelmetModel(
                            net.minecraft.client.Minecraft.getInstance().getEntityModels()
                                    .bakeLayer(PILLAGER_HELMET),
                            entityLiving);
                }
                return model;
            }
        });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        if (this.isDiamond) {
            return GeneralUtil.mobsLoc("textures/models/armor/diamond_pillager_helmet.png");
        }
        return GeneralUtil.mobsLoc(":textures/models/armor/gold_pillager_helmet.png");
    }
}
