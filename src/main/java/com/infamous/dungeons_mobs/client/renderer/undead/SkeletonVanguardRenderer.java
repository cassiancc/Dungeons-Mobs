package com.infamous.dungeons_mobs.client.renderer.undead;

import com.infamous.dungeons_mobs.client.models.undead.SkeletonVanguardModel;
import com.infamous.dungeons_mobs.entities.undead.SkeletonVanguardEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.example.client.DefaultBipedBoneIdents;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import javax.annotation.Nullable;


public class SkeletonVanguardRenderer extends DynamicGeoEntityRenderer<SkeletonVanguardEntity> {
    public SkeletonVanguardRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SkeletonVanguardModel());
    }

    @Override
    protected void applyRotations(SkeletonVanguardEntity entityLiving, PoseStack matrixStackIn, float ageInTicks,
                                  float rotationYaw, float partialTicks) {
        float scaleFactor = 1.0F;
        matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

    }

    @Override
    public RenderType getRenderType(SkeletonVanguardEntity animatable, ResourceLocation texture,
                                    @Nullable MultiBufferSource bufferSource,
                                    float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void renderRecursively(PoseStack poseStack, SkeletonVanguardEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.isArmorBone(bone)) {
            bone.setChildrenHidden(true);
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

//    @Override
    protected boolean isArmorBone(GeoBone bone) {
        return bone.getName().startsWith("armor");
    }


    @Override
    protected ItemStack getHeldItemForBone(String boneName, SkeletonVanguardEntity currentEntity) {
        switch (boneName) {
            case DefaultBipedBoneIdents.LEFT_HAND_BONE_IDENT:
                return currentEntity.isLeftHanded() ? mainHand : offHand;
            case DefaultBipedBoneIdents.RIGHT_HAND_BONE_IDENT:
                return currentEntity.isLeftHanded() ? offHand : mainHand;
            case DefaultBipedBoneIdents.POTION_BONE_IDENT:
                break;
        }
        return null;
    }

    @Override
    protected ItemDisplayContext getCameraTransformForItemAtBone(ItemStack boneItem, String boneName) {
        switch (boneName) {
            case DefaultBipedBoneIdents.LEFT_HAND_BONE_IDENT:
                return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
            case DefaultBipedBoneIdents.RIGHT_HAND_BONE_IDENT:
                return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
            default:
                return ItemDisplayContext.NONE;
        }
    }

    @Override
    protected void preRenderItem(PoseStack stack, ItemStack item, String boneName, SkeletonVanguardEntity currentEntity, IBone bone) {
        if (item == this.mainHand) {
            stack.mulPose(Axis.XP.rotationDegrees(-90f));

            if (item.getItem() instanceof ShieldItem)
                stack.translate(0, 0.125, -0.25);
        }
        else if (item == this.offHand) {
            stack.mulPose(Axis.XP.rotationDegrees(-90f));

            if (item.getItem() instanceof ShieldItem) {
                stack.translate(0, 0.125, 0.25);
                stack.mulPose(Axis.YP.rotationDegrees(180));
            }
        }
    }

    @Override
    protected void postRenderItem(PoseStack matrixStack, ItemStack item, String boneName, SkeletonVanguardEntity currentEntity, IBone bone) {

    }

    @Override
    protected BlockState getHeldBlockForBone(String boneName, SkeletonVanguardEntity currentEntity) {
        return null;
    }

    @Override
    protected void preRenderBlock(PoseStack matrixStack, BlockState block, String boneName,
                                  SkeletonVanguardEntity currentEntity) {

    }

    @Override
    protected void postRenderBlock(PoseStack matrixStack, BlockState block, String boneName,
                                   SkeletonVanguardEntity currentEntity) {

    }

    @Nullable
    @Override
    protected ItemStack getArmorForBone(String boneName, SkeletonVanguardEntity currentEntity) {
        return switch (boneName) {
            case "armorBipedLeftFoot", "armorBipedRightFoot" -> currentEntity.getItemBySlot(EquipmentSlot.FEET);
            case "armorBipedLeftLeg", "armorBipedRightLeg" -> currentEntity.getItemBySlot(EquipmentSlot.LEGS);
            case "armorBipedBody", "armorBipedRightArm", "armorBipedLeftArm" -> currentEntity.getItemBySlot(EquipmentSlot.CHEST);
            case "armorBipedHead" -> currentEntity.getItemBySlot(EquipmentSlot.HEAD);
            default -> null;
        };
    }

    @Override
    protected EquipmentSlot getEquipmentSlotForArmorBone(String boneName, SkeletonVanguardEntity currentEntity) {
        return switch (boneName) {
            case "armorBipedLeftFoot", "armorBipedRightFoot" -> EquipmentSlot.FEET;
            case "armorBipedLeftLeg", "armorBipedRightLeg" -> EquipmentSlot.LEGS;
            case "armorBipedRightHand" -> !currentEntity.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            case "armorBipedLeftHand" -> currentEntity.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            case "armorBipedRightArm", "armorBipedLeftArm", "armorBipedBody" -> EquipmentSlot.CHEST;
            case "armorBipedHead" -> EquipmentSlot.HEAD;
            default -> null;
        };
    }

    @Override
    protected ModelPart getArmorPartForBone(String name, HumanoidModel<?> armorBipedModel) {
        return switch (name) {
            case "armorBipedLeftFoot", "armorBipedLeftLeg" -> armorBipedModel.leftLeg;
            case "armorBipedRightFoot", "armorBipedRightLeg" -> armorBipedModel.rightLeg;
            case "armorBipedRightArm" -> armorBipedModel.rightArm;
            case "armorBipedLeftArm" -> armorBipedModel.leftArm;
            case "armorBipedBody" -> armorBipedModel.body;
            case "armorBipedHead" -> armorBipedModel.head;
            default -> null;
        };
    }

    @Override
    protected void setLimbBoneVisible(GeoArmorRenderer<? extends GeoArmorItem> armorRenderer, ModelPart limb, HumanoidModel<?> armorModel, EquipmentSlot slot) {
        if (limb == armorModel.head || limb == armorModel.hat) {
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.headBone).setHidden(false);
        }
        else if (limb == armorModel.body) {
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.bodyBone).setHidden(false);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftArmBone).setHidden(true);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightArmBone).setHidden(true);
        }
        else if (limb == armorModel.leftArm) {
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.bodyBone).setHidden(true);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftArmBone).setHidden(false);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightArmBone).setHidden(true);
        }
        else if (limb == armorModel.leftLeg) {
            armorRenderer.getGeoModelProvider().getBone((slot == EquipmentSlot.FEET ? armorRenderer.leftBootBone : armorRenderer.leftLegBone)).setHidden(false);
            armorRenderer.getGeoModelProvider().getBone((slot == EquipmentSlot.FEET ? armorRenderer.leftLegBone : armorRenderer.leftBootBone)).setHidden(true);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightBootBone).setHidden(true);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightLegBone).setHidden(true);
        }
        else if (limb == armorModel.rightArm) {
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.bodyBone).setHidden(true);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftArmBone).setHidden(true);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightArmBone).setHidden(false);
        }
        else if (limb == armorModel.rightLeg) {
            armorRenderer.getGeoModelProvider().getBone((slot == EquipmentSlot.FEET ? armorRenderer.rightBootBone : armorRenderer.rightLegBone)).setHidden(false);
            armorRenderer.getGeoModelProvider().getBone((slot == EquipmentSlot.FEET ? armorRenderer.rightLegBone : armorRenderer.rightBootBone)).setHidden(true);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftBootBone).setHidden(true);
            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftLegBone).setHidden(true);
        }
    }
}