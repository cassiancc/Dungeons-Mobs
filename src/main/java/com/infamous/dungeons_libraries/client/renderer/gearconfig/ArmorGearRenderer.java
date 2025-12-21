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
import software.bernie.geckolib.util.RenderUtils;

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
                                  int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        this.prepMatrixForBone(poseStack, bone);
        renderCubesOfBone(poseStack, bone, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        renderChildBones(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.popPose();
    }

    public void prepMatrixForBone(PoseStack stack, GeoBone bone) {
        RenderUtils.translateMatrixToBone(stack, bone);
        RenderUtils.translateToPivotPoint(stack, bone);
        EntityRenderer<? super LivingEntity> entityRenderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(livingEntity);
        if (!(entityRenderer instanceof GeoEntityRenderer) || !bone.getName().contains("armor")) {
            RenderUtils.rotateMatrixAroundBone(stack, bone);
        }
        RenderUtils.scaleMatrixForBone(stack, bone);
        //FIXME this may be an unsafe cast
        ArmorMaterial material = ((ArmorItem) this.currentStack.getItem()).getMaterial();
        if (bone.getName().contains("Body") && material instanceof DungeonsArmorMaterial && ((DungeonsArmorMaterial) material).getBaseType() == ArmorMaterialBaseType.CLOTH) {
            stack.scale(1.0F, 1.0F, 0.93F);
        }
        RenderUtils.translateAwayFromPivotPoint(stack, bone);
    }

    @Override
    public void renderCubesOfBone(PoseStack poseStack, GeoBone bone, VertexConsumer buffer, int packedLight,
                                  int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.isHidden())
            return;

        for (GeoCube cube : bone.getCubes()) {
            if (!bone.isHidingChildren()) {
                poseStack.pushPose();
                if (livingEntity instanceof SpawnArmoredMob
                // FIXME
                // && ((SpawnArmoredMob) livingEntity).getArmorSet().getRegistryName() == this.currentArmorItem.getArmorSet()
                ) {
                    renderCube(poseStack, cube, buffer, packedLight, LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F), red, green, blue, alpha);
                } else {
                    renderCube(poseStack, cube, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                }
                poseStack.popPose();
            }
        }
    }
}