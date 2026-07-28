package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.SummonSpotModel;
import com.infamous.dungeons_mobs.entities.projectiles.NecromancerOrbEntity;
import com.infamous.dungeons_mobs.entities.summonables.SummonSpotEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SummonSpotRenderer extends GeoEntityRenderer<SummonSpotEntity> {
    public SummonSpotRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SummonSpotModel<SummonSpotEntity>());
    }

    @Override
    public void preRender(PoseStack poseStack, SummonSpotEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {

        if (animatable.lifeTime <= 1) {
            float scaleFactor = 0.0F;
            poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        } else {

        }
    }

    @Override
    protected int getBlockLightLevel(SummonSpotEntity p_225624_1_, BlockPos p_225624_2_) {
        return 15;
    }
}