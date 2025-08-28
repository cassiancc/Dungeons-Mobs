package com.infamous.dungeons_mobs.client.renderer.blaze;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.blaze.WildfireModel;
import com.infamous.dungeons_mobs.client.renderer.layers.PulsatingGlowLayer;
import com.infamous.dungeons_mobs.entities.blaze.WildfireEntity;
import com.infamous.dungeons_mobs.entities.jungle.WhispererEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BlazeRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

import javax.annotation.Nullable;

public class WildfireRenderer extends DynamicGeoEntityRenderer<WildfireEntity> {
    public WildfireRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WildfireModel());
        this.addRenderLayer(new PulsatingGlowLayer<>(this, new ResourceLocation(DungeonsMobs.MODID, "textures/entity/blaze/wildfire.png"), 0.1F, 1.0F, 0.25F));
    }

    @Override
    protected int getBlockLightLevel(WildfireEntity p_225624_1_, BlockPos p_225624_2_) {
        return 15;
    }

    @Override
    protected void applyRotations(WildfireEntity entityLiving, PoseStack matrixStackIn, float ageInTicks,
                                  float rotationYaw, float partialTicks) {
        float scaleFactor = 1.25F;
        matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

    }



    //FIXME
//
//    @Override
//    public void renderRecursively(PoseStack poseStack, WildfireEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        if (this.isArmorBone(bone)) {
//            bone.setChildrenHidden(true);
//        }
//        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
//    }
//
//    @Override
//    protected boolean isArmorBone(GeoBone bone) {
//        return bone.getName().startsWith("armor");
//    }
//
//    @Override
//    protected ItemDisplayContext getCameraTransformForItemAtBone(ItemStack boneItem, String boneName) {
//        return ItemDisplayContext.NONE;
//    }
//
//    @Override
//    protected void preRenderItem(PoseStack stack, ItemStack item, String boneName, WildfireEntity currentEntity, IBone bone) {
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
//
//
//    @Nullable
//    @Override
//    protected ItemStack getArmorForBone(String boneName, WildfireEntity currentEntity) {
//        switch (boneName) {
//            case "armorHead":
//                return helmet;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    protected EquipmentSlot getEquipmentSlotForArmorBone(String boneName, WildfireEntity currentEntity) {
//        switch (boneName) {
//            case "armorHead":
//                return EquipmentSlot.HEAD;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    protected ModelPart getArmorPartForBone(String name, HumanoidModel<?> armorBipedModel) {
//        switch (name) {
//            case "armorHead":
//                return armorBipedModel.head;
//            default:
//                return null;
//        }
//    }
}