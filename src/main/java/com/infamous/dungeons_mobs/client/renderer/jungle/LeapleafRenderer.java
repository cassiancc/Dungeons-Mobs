package com.infamous.dungeons_mobs.client.renderer.jungle;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.jungle.LeapleafModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.entities.jungle.LeapleafEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LeapleafRenderer extends GeoEntityRenderer<LeapleafEntity> {
    @SuppressWarnings("unchecked")
    public LeapleafRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LeapleafModel());
        this.addRenderLayer(new GeoEyeLayer(this, GeneralUtil.mobsLoc( "textures/entity/jungle/leapleaf_glow.png")));
    }

    @Override
    protected void applyRotations(LeapleafEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        float scaleFactor = 0.9375F;
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
    }
}
