package com.infamous.dungeons_mobs.client.renderer.jungle;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.jungle.WhispererModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.entities.jungle.WhispererEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.specialty.DynamicGeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class WhispererRenderer extends DynamicGeoEntityRenderer<WhispererEntity> {

    @SuppressWarnings("unchecked")
    public WhispererRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WhispererModel());
        this.addRenderLayer(new GeoEyeLayer(this, GeneralUtil.mobsLoc( "textures/entity/jungle/whisperer_glow.png")));
    }

    @Override
    protected void applyRotations(WhispererEntity entityLiving, PoseStack matrixStackIn, float ageInTicks,
                                  float rotationYaw, float partialTicks) {
        float scaleFactor = 1.0F;
        matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

    }

    //FIXME
//
//    @Override
//    public void renderRecursively(PoseStack poseStack, WhispererEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
//        if (this.isArmorBone(bone)) {
//            bone.setChildrenHidden(true);
//        }
//        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
//    }
//
//    @Override
//    protected boolean isArmorBone(GeoBone bone) {
//        return bone.getName().startsWith("armor");
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getTextureForBone(String s, WhispererEntity windcallerEntity) {
//        return null;
//    }
//
//    @Override
//    protected ItemStack getHeldItemForBone(String boneName, WhispererEntity currentEntity) {
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
//    protected void preRenderItem(PoseStack stack, ItemStack item, String boneName, WhispererEntity currentEntity, GeoBone bone) {
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
//    @Override
//    protected void postRenderItem(PoseStack matrixStack, ItemStack item, String boneName, WhispererEntity currentEntity, IBone bone) {
//
//    }
//
//    @Override
//    protected BlockState getHeldBlockForBone(String boneName, WhispererEntity currentEntity) {
//        return null;
//    }
//
//    @Override
//    protected void preRenderBlock(PoseStack matrixStack, BlockState block, String boneName,
//                                  WhispererEntity currentEntity) {
//
//    }
//
//    @Override
//    protected void postRenderBlock(PoseStack matrixStack, BlockState block, String boneName,
//                                   WhispererEntity currentEntity) {
//
//    }
//
//    @Nullable
//    @Override
//    protected ItemStack getArmorForBone(String boneName, WhispererEntity currentEntity) {
//        switch (boneName) {
//            case "armorBipedLeftFoot":
//            case "armorBipedRightFoot":
//                return boots;
//            case "armorBipedLeftLeg":
//            case "armorBipedRightLeg":
//                return leggings;
//            case "armorBipedBody":
//            case "armorBipedRightArm":
//            case "armorBipedLeftArm":
//            case "armorIllagerRightArm":
//            case "armorIllagerLeftArm":
//                return chestplate;
//            case "armorBipedHead":
//                return helmet;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    protected EquipmentSlot getEquipmentSlotForArmorBone(String boneName, WhispererEntity currentEntity) {
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
}