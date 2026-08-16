package com.infamous.dungeons_mobs.items;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.client.models.armor.VindicatorHelmetModel;
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
import static com.infamous.dungeons_mobs.client.models.geom.ModModelLayers.VINDICATOR_HELMET;

public class VindicatorHelmetItem extends ArmorItem {
    private final boolean isDiamond;

    public VindicatorHelmetItem(Holder<ArmorMaterial> materialIn, ArmorItem.Type slot, Properties builderIn, boolean isDiamondIn) {
        super(materialIn, slot, builderIn);
        this.isDiamond = isDiamondIn;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<net.neoforged.neoforge.client.extensions.common.IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private VindicatorHelmetModel model;

            @Nullable
            @Override
            public net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, net.minecraft.client.model.HumanoidModel<?> _default) {
                if (null == model) {
                    model = new VindicatorHelmetModel(
                            net.minecraft.client.Minecraft.getInstance().getEntityModels()
                                    .bakeLayer(VINDICATOR_HELMET), entityLiving, isDiamond);
                }
                return model;
            }
        });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        if (this.isDiamond) {
            return GeneralUtil.mobsLoc("textures/models/armor/diamond_vindicator_helmet.png");
        }
        return GeneralUtil.mobsLoc("textures/models/armor/gold_vindicator_helmet.png");
    }
}
