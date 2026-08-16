package com.infamous.dungeons_mobs.client.renderer.armor;

import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearRenderer;
import com.infamous.dungeons_mobs.client.models.armor.IllusionerArmorGearModel;
import com.infamous.dungeons_mobs.items.armor.IllusionerArmorGear;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class IllusionerArmorGearRenderer extends ArmorGearRenderer<IllusionerArmorGear> {

    private final LivingEntity livingEntity;

    public IllusionerArmorGearRenderer(LivingEntity livingEntity) {
        super(new IllusionerArmorGearModel<>(), livingEntity);
        this.livingEntity = livingEntity;
    }

    @Override
    public void renderRecursively(PoseStack poseStack, IllusionerArmorGear animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {

        GeoModel<IllusionerArmorGear> geoModelProvider = getGeoModel();
        if (geoModelProvider instanceof IllusionerArmorGearModel) {
            ((IllusionerArmorGearModel<IllusionerArmorGear>) geoModelProvider).setWearer(this.livingEntity);
        }
        super.renderRecursively(poseStack, animatable, getBodyBone(model), renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
