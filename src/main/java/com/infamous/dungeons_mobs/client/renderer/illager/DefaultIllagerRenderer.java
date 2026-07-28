package com.infamous.dungeons_mobs.client.renderer.illager;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class DefaultIllagerRenderer<T extends Mob & GeoAnimatable> extends GeoEntityRenderer<T> {
    private float scaleFactor = 0.9375F;

    public DefaultIllagerRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
    }

    public DefaultIllagerRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> modelProvider, float scaleFactor) {
        super(renderManager, modelProvider);
        this.scaleFactor = scaleFactor;
    }

    @Override
    protected void applyRotations(T animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
    }

    @Override
    public RenderType getRenderType(T animatable, ResourceLocation texture,
                                    @Nullable MultiBufferSource bufferSource,
                                    float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void renderRecursively(PoseStack poseStack, T animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        if (this.isArmorBone(bone)) {
            bone.setChildrenHidden(true);
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }

//    @Override
    protected boolean isArmorBone(GeoBone bone) {
        return bone.getName().startsWith("armor");
    }

// FIXME
//    @Override
//    protected ItemStack getHeldItemForBone(String boneName, T currentEntity) {
//        switch (boneName) {
//            case DefaultBipedBoneIdents.LEFT_HAND_BONE_IDENT:
//                return currentEntity.isLeftHanded() ? mainHand : offHand;
//            case DefaultBipedBoneIdents.RIGHT_HAND_BONE_IDENT:
//                return currentEntity.isLeftHanded() ? offHand : mainHand;
//            case DefaultBipedBoneIdents.POTION_BONE_IDENT:
//                break;
//        }
//        return null;
//    }
//
//    @Override
//    protected TransformType getCameraTransformForItemAtBone(ItemStack boneItem, String boneName) {
//        switch (boneName) {
//            case DefaultBipedBoneIdents.LEFT_HAND_BONE_IDENT:
//                return TransformType.THIRD_PERSON_RIGHT_HAND;
//            case DefaultBipedBoneIdents.RIGHT_HAND_BONE_IDENT:
//                return TransformType.THIRD_PERSON_RIGHT_HAND;
//            default:
//                return TransformType.NONE;
//        }
//    }
//
//    @Override
//    protected void preRenderItem(PoseStack stack, ItemStack item, String boneName, T currentEntity, IBone bone) {
//        if (item == this.mainHand) {
//            stack.mulPose(Axis.XP.rotationDegrees(-90f));
//
//            if (item.getItem() instanceof ShieldItem)
//                stack.translate(0, 0.125, -0.25);
//        }
//        else if (item == this.offHand) {
//            stack.mulPose(Axis.XP.rotationDegrees(-90f));
//
//            if (item.getItem() instanceof ShieldItem) {
//                stack.translate(0, 0.125, 0.25);
//                stack.mulPose(Axis.YP.rotationDegrees(180));
//            }
//        }
//    }
//
//    @Nullable
//    @Override
//    protected ItemStack getArmorForBone(String boneName, T currentEntity) {
//        switch (boneName) {
//            case "armorBipedLeftFoot":
//            case "armorBipedRightFoot":
//                return currentEntity.getItemBySlot(EquipmentSlot.FEET);
//            case "armorBipedLeftLeg":
//            case "armorBipedRightLeg":
//                return currentEntity.getItemBySlot(EquipmentSlot.LEGS);
//            case "armorBipedBody":
//            case "armorBipedRightArm":
//            case "armorBipedLeftArm":
//            case "armorIllagerRightArm":
//            case "armorIllagerLeftArm":
//                return currentEntity.getItemBySlot(EquipmentSlot.CHEST);
//            case "armorBipedHead":
//                return currentEntity.getItemBySlot(EquipmentSlot.HEAD);
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    protected EquipmentSlot getEquipmentSlotForArmorBone(String boneName, T currentEntity) {
//        switch (boneName) {
//            case "armorBipedLeftFoot":
//            case "armorBipedRightFoot":
//                return EquipmentSlot.FEET;
//            case "armorBipedLeftLeg":
//            case "armorBipedRightLeg":
//                return EquipmentSlot.LEGS;
//            case "armorBipedRightHand":
//                return !currentEntity.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
//            case "armorBipedLeftHand":
//                return currentEntity.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
//            case "armorBipedRightArm":
//            case "armorBipedLeftArm":
//            case "armorIllagerRightArm":
//            case "armorIllagerLeftArm":
//            case "armorBipedBody":
//                return EquipmentSlot.CHEST;
//            case "armorBipedHead":
//                return EquipmentSlot.HEAD;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    protected ModelPart getArmorPartForBone(String name, HumanoidModel<?> armorBipedModel) {
//        switch (name) {
//            case "armorBipedLeftFoot":
//            case "armorBipedLeftLeg":
//                return armorBipedModel.leftLeg;
//            case "armorBipedRightFoot":
//            case "armorBipedRightLeg":
//                return armorBipedModel.rightLeg;
//            case "armorBipedRightArm":
//            case "armorIllagerRightArm":
//                return armorBipedModel.rightArm;
//            case "armorBipedLeftArm":
//            case "armorIllagerLeftArm":
//                return armorBipedModel.leftArm;
//            case "armorBipedBody":
//                return armorBipedModel.body;
//            case "armorBipedHead":
//                return armorBipedModel.head;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    protected void prepareArmorPositionAndScale(GeoBone bone, List<ModelPart.Cube> cubeList, ModelPart sourceLimb, PoseStack stack, boolean geoArmor, boolean modMatrixRot) {
//        super.prepareArmorPositionAndScale(bone, cubeList, sourceLimb, stack, geoArmor, modMatrixRot);
//        if (bone.getName().equals("armorBipedHead") && geoArmor && BuiltInRegistries.ITEM.getKey(helmet.getItem()).getNamespace().equals(MODID)) {
//            stack.translate(0, 0.125, 0); // 1y is 1 cube up, we want 2/16
//        }
//    }
//
//    @Override
//    protected void setLimbBoneVisible(GeoArmorRenderer<? extends GeoItem> armorRenderer, ModelPart limb, HumanoidModel<?> armorModel, EquipmentSlot slot) {
//        if (limb == armorModel.head || limb == armorModel.hat) {
//            armorRenderer.getHeadBone().setHidden(false);
//        } else if (limb == armorModel.body) {
//            armorRenderer.getBodyBone().setHidden(false);
//            armorRenderer.getLeftArmBone().setHidden(true);
//            armorRenderer.getRightArmBone().setHidden(true);
//        } else if (limb == armorModel.leftArm) {
//            armorRenderer.getBodyBone().setHidden(true);
//            armorRenderer.getLeftArmBone().setHidden(false);
//            armorRenderer.getRightArmBone().setHidden(true);
//        } else if (limb == armorModel.leftLeg) {
//            if (slot == EquipmentSlot.FEET)
//                armorRenderer.getLeftBootBone().setHidden(false);
//            else armorRenderer.getLeftLegBone().setHidden(false);
//            if (slot == EquipmentSlot.FEET)
//                armorRenderer.getLeftLegBone().setHidden(true);
//            else armorRenderer.getLeftBootBone().setHidden(true);
//            armorRenderer.getRightBootBone().setHidden(true);
//            armorRenderer.getRightLegBone().setHidden(true);
//        } else if (limb == armorModel.rightArm) {
//            armorRenderer.getBodyBone().setHidden(true);
//            armorRenderer.getLeftArmBone().setHidden(true);
//            armorRenderer.getRightArmBone().setHidden(false);
//        } else if (limb == armorModel.rightLeg) {
//            if (slot == EquipmentSlot.FEET)
//                armorRenderer.getRightBootBone().setHidden(false);
//            else armorRenderer.getRightLegBone().setHidden(false);
//            if (slot == EquipmentSlot.FEET)
//                armorRenderer.getRightLegBone().setHidden(true);
//            else armorRenderer.getRightBootBone().setHidden(true);
//            armorRenderer.getLeftBootBone().setHidden(true);
//            armorRenderer.getLeftLegBone().setHidden(true);
//        }
//    }
}