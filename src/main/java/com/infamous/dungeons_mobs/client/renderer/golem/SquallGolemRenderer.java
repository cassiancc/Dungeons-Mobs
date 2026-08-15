package com.infamous.dungeons_mobs.client.renderer.golem;

import com.infamous.dungeons_mobs.client.models.golem.SquallGolemModel;
import com.infamous.dungeons_mobs.entities.golem.SquallGolemEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class SquallGolemRenderer extends GeoEntityRenderer<SquallGolemEntity> {
	
    public SquallGolemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SquallGolemModel());
    }

    @Override
    protected void applyRotations(SquallGolemEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        float scaleFactor = 1.0f;
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
    }
}