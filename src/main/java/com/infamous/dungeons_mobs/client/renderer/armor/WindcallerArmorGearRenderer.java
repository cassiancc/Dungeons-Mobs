package com.infamous.dungeons_mobs.client.renderer.armor;

import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearRenderer;
import com.infamous.dungeons_mobs.client.models.armor.WindcallerArmorGearModel;
import com.infamous.dungeons_mobs.items.armor.WindcallerArmorGear;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class WindcallerArmorGearRenderer extends ArmorGearRenderer<WindcallerArmorGear> {

    private final LivingEntity livingEntity;

    public WindcallerArmorGearRenderer(LivingEntity livingEntity) {
        super(new WindcallerArmorGearModel<>(), livingEntity);
        this.livingEntity = livingEntity;
    }

    // FIXME
//    @Override
//    public void preparePositionRotationScale(GeoBone bone, PoseStack stack) {
//        RenderUtils.translate(bone, stack);
//        RenderUtils.moveToPivot(bone, stack);
//        EntityRenderer<? super LivingEntity> entityRenderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entityLiving);
//        if (!(entityRenderer instanceof GeoEntityRenderer) || !bone.getName().contains("armor")) {
//            RenderUtils.rotate(bone, stack);
//        }
//        RenderUtils.scale(bone, stack);
//        ArmorMaterial material = this.currentArmorItem.getMaterial();
//        if (bone.getName().contains("Body") && material instanceof DungeonsArmorMaterial && ((DungeonsArmorMaterial) material).getBaseType() == ArmorMaterialBaseType.CLOTH) {
//            stack.scale(1.0F, 1.0F, 0.85F);
//        }
//        if (entityLiving instanceof WindcallerEntity && bone.getName().contains("Head")) {
//            stack.scale(0.93F, 0.93F, 0.93F);
//            stack.translate(0.0D, 0.116D, 0.0D);
//        }
//        RenderUtils.moveBackFromPivot(bone, stack);
//    }

    @Override
    public void renderRecursively(PoseStack poseStack, WindcallerArmorGear animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        GeoModel<WindcallerArmorGear> geoModelProvider = getGeoModel();
        if (geoModelProvider instanceof WindcallerArmorGearModel) {
            ((WindcallerArmorGearModel<WindcallerArmorGear>) geoModelProvider).setWearer(this.livingEntity);
        }
        super.renderRecursively(poseStack, animatable, getBodyBone(), renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
