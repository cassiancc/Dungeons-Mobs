package com.infamous.dungeons_mobs.client.renderer.armor;

import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearRenderer;
import com.infamous.dungeons_mobs.client.models.armor.MageArmorGearModel;
import com.infamous.dungeons_mobs.items.armor.MageArmorGear;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class MageArmorGearRenderer extends ArmorGearRenderer<MageArmorGear> {

    private final LivingEntity livingEntity;

    public MageArmorGearRenderer(LivingEntity livingEntity) {
        super(new MageArmorGearModel<>(), livingEntity);
        this.livingEntity = livingEntity;
    }

    @Override
    public void renderRecursively(PoseStack poseStack, MageArmorGear animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {

        GeoModel<MageArmorGear> geoModelProvider = getGeoModel();
        if (geoModelProvider instanceof MageArmorGearModel) {
            ((MageArmorGearModel<MageArmorGear>) geoModelProvider).setWearer(this.livingEntity);
        }
        super.renderRecursively(poseStack, animatable, getBodyBone(), renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
