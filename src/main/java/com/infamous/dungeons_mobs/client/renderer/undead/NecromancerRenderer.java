package com.infamous.dungeons_mobs.client.renderer.undead;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.undead.NecromancerModel;
import com.infamous.dungeons_mobs.client.renderer.layers.PulsatingGlowLayer;
import com.infamous.dungeons_mobs.entities.undead.NecromancerEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.specialty.DynamicGeoEntityRenderer;

public class NecromancerRenderer extends DynamicGeoEntityRenderer<NecromancerEntity> {

    @SuppressWarnings("unchecked")
    public NecromancerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NecromancerModel());
        this.addRenderLayer(new PulsatingGlowLayer(this, GeneralUtil.mobsLoc( "textures/entity/skeleton/necromancer_eyes.png"), 0.2F, 1.0F, 0.5F));
    }

    @Override
    protected void applyRotations(NecromancerEntity entityLiving, PoseStack matrixStackIn, float ageInTicks,
                                  float rotationYaw, float partialTicks) {
        float scaleFactor = 1.3F;
        matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

    }



    //FIXME
//
//    @Override
//    public void renderRecursively(PoseStack poseStack, NecromancerEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
//        if (this.isArmorBone(bone)) {
//            bone.setCubesHidden(true);
//        }
//        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, colour);
//    }
//
//    @Override
//    protected boolean isArmorBone(GeoBone bone) {
//        return bone.getName().startsWith("armor");
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getTextureForBone(String s, NecromancerEntity windcallerEntity) {
//        return null;
//    }
//
//    @Override
//    protected ItemStack getHeldItemForBone(String boneName, NecromancerEntity currentEntity) {
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
//    protected void preRenderItem(PoseStack stack, ItemStack item, String boneName, NecromancerEntity currentEntity, IBone bone) {
//        if (item == this.mainHand) {
//            stack.mulPose(Vector3f.XP.rotationDegrees(-90f));
//
//            if (item.getItem() instanceof ShieldItem)
//                stack.translate(0, 0.125, -0.25);
//        }
//        else if (item == this.offHand) {
//            stack.mulPose(Vector3f.XP.rotationDegrees(-90f));
//
//            if (item.getItem() instanceof ShieldItem) {
//                stack.translate(0, 0.125, 0.25);
//                stack.mulPose(Vector3f.YP.rotationDegrees(180));
//            }
//        }
//    }
//
//    @Override
//    protected void postRenderItem(PoseStack matrixStack, ItemStack item, String boneName, NecromancerEntity currentEntity, IBone bone) {
//
//    }
//
//    @Override
//    protected BlockState getHeldBlockForBone(String boneName, NecromancerEntity currentEntity) {
//        return null;
//    }
//
//    @Override
//    protected void preRenderBlock(PoseStack matrixStack, BlockState block, String boneName,
//                                  NecromancerEntity currentEntity) {
//
//    }
//
//    @Override
//    protected void postRenderBlock(PoseStack matrixStack, BlockState block, String boneName,
//                                   NecromancerEntity currentEntity) {
//
//    }
//
//    @Nullable
//    @Override
//    protected ItemStack getArmorForBone(String boneName, NecromancerEntity currentEntity) {
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
//    protected EquipmentSlot getEquipmentSlotForArmorBone(String boneName, NecromancerEntity currentEntity) {
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
//    protected void setLimbBoneVisible(GeoArmorRenderer<? extends GeoArmorItem> armorRenderer, ModelPart limb, HumanoidModel<?> armorModel, EquipmentSlot slot) {
//        if (limb == armorModel.head || limb == armorModel.hat) {
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.headBone).setHidden(false);
//        }
//        else if (limb == armorModel.body) {
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.bodyBone).setHidden(false);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftArmBone).setHidden(true);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightArmBone).setHidden(true);
//        }
//        else if (limb == armorModel.leftArm) {
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.bodyBone).setHidden(true);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftArmBone).setHidden(false);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightArmBone).setHidden(true);
//        }
//        else if (limb == armorModel.leftLeg) {
//            armorRenderer.getGeoModelProvider().getBone((slot == EquipmentSlot.FEET ? armorRenderer.leftBootBone : armorRenderer.leftLegBone)).setHidden(false);
//            armorRenderer.getGeoModelProvider().getBone((slot == EquipmentSlot.FEET ? armorRenderer.leftLegBone : armorRenderer.leftBootBone)).setHidden(true);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightBootBone).setHidden(true);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightLegBone).setHidden(true);
//        }
//        else if (limb == armorModel.rightArm) {
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.bodyBone).setHidden(true);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftArmBone).setHidden(true);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.rightArmBone).setHidden(false);
//        }
//        else if (limb == armorModel.rightLeg) {
//            armorRenderer.getGeoModelProvider().getBone((slot == EquipmentSlot.FEET ? armorRenderer.rightBootBone : armorRenderer.rightLegBone)).setHidden(false);
//            armorRenderer.getGeoModelProvider().getBone((slot == EquipmentSlot.FEET ? armorRenderer.rightLegBone : armorRenderer.rightBootBone)).setHidden(true);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftBootBone).setHidden(true);
//            armorRenderer.getGeoModelProvider().getBone(armorRenderer.leftLegBone).setHidden(true);
//        }
//    }

//    protected void handleGeoArmorBoneVisibility(GeoArmorRenderer<? extends GeoArmorItem> geoArmorRenderer, ModelRenderer sourceLimb, BipedModel<?> armorModel, EquipmentSlot slot) {
//        super.handleGeoArmorBoneVisibility(geoArmorRenderer, sourceLimb, armorModel, slot);
//        if(geoArmorRenderer instanceof NecromancerArmorGearRenderer) {
//            IBone gbHood = geoArmorRenderer.getGeoModelProvider().getBone(((NecromancerArmorGearRenderer) geoArmorRenderer).hoodBodyBone);
//            IBone gbLeggingsBody = geoArmorRenderer.getGeoModelProvider().getBone(((NecromancerArmorGearRenderer) geoArmorRenderer).leggingsBodyBone);
//            gbHood.setHidden(true);
//            gbLeggingsBody.setHidden(true);
//
//            if (sourceLimb == armorModel.body) {
//                gbHood.setHidden(false);
//                return;
//            }
//            if (sourceLimb == armorModel.rightLeg) {
//                gbLeggingsBody.setHidden(true);
//                return;
//            }
//        }
//    }
}