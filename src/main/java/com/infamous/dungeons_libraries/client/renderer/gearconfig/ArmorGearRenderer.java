package com.infamous.dungeons_libraries.client.renderer.gearconfig;

import com.infamous.dungeons_libraries.entities.SpawnArmoredMob;
import com.infamous.dungeons_libraries.items.gearconfig.ArmorGear;
import com.infamous.dungeons_libraries.items.materials.armor.ArmorMaterialBaseType;
import com.infamous.dungeons_libraries.items.materials.armor.DungeonsArmorMaterial;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.cache.object.GeoCube;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.util.RenderUtil;

public class ArmorGearRenderer<T extends ArmorGear & GeoItem> extends GeoArmorRenderer<T> {
    private final LivingEntity livingEntity;

    public ArmorGearRenderer(LivingEntity livingEntity) {
        super(new ArmorGearModel<>());
        this.livingEntity = livingEntity;
    }

    public ArmorGearRenderer(ArmorGearModel<T> model, LivingEntity livingEntity) {
        super(model);
        this.livingEntity = livingEntity;
    }

    @Override
    public void renderRecursively(PoseStack poseStack, T animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight,
                                  int packedOverlay, int colour) {
        poseStack.pushPose();
        this.prepMatrixForBone(poseStack, bone);
        renderCubesOfBone(poseStack, bone, buffer, packedLight, packedOverlay, colour);
        renderChildBones(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        poseStack.popPose();
    }

    public void prepMatrixForBone(PoseStack stack, GeoBone bone) {
        RenderUtil.translateMatrixToBone(stack, bone);
        RenderUtil.translateToPivotPoint(stack, bone);
        EntityRenderer<? super LivingEntity> entityRenderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(livingEntity);
        if (!(entityRenderer instanceof GeoEntityRenderer) || !bone.getName().contains("armor")) {
            RenderUtil.rotateMatrixAroundBone(stack, bone);
        }
        RenderUtil.scaleMatrixForBone(stack, bone);
        //FIXME this may be an unsafe cast
        ArmorMaterial material = ((ArmorItem) this.currentStack.getItem()).getMaterial().value();
        if (bone.getName().contains("Body") && material instanceof DungeonsArmorMaterial && ((DungeonsArmorMaterial) material).getBaseType() == ArmorMaterialBaseType.CLOTH) {
            stack.scale(1.0F, 1.0F, 0.93F);
        }
        RenderUtil.translateAwayFromPivotPoint(stack, bone);
    }

    @Override
    public void renderCubesOfBone(PoseStack poseStack, GeoBone bone, VertexConsumer buffer, int packedLight, int packedOverlay,
                                  int colour) {
        if (bone.isHidden())
            return;

        for (GeoCube cube : bone.getCubes()) {
            if (!bone.isHidingChildren()) {
                poseStack.pushPose();
                if (livingEntity instanceof SpawnArmoredMob
                // FIXME
                // && ((SpawnArmoredMob) livingEntity).getArmorSet().getRegistryName() == this.currentArmorItem.getArmorSet()
                ) {
                    renderCube(poseStack, cube, buffer, packedLight, LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F), colour);
                } else {
                    renderCube(poseStack, cube, buffer, packedLight, packedOverlay, colour);
                }
                poseStack.popPose();
            }
        }
    }
}