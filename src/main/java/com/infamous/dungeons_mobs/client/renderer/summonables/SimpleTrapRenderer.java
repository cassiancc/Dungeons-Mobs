package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.SimpleTrapModel;
import com.infamous.dungeons_mobs.entities.projectiles.NecromancerOrbEntity;
import com.infamous.dungeons_mobs.entities.summonables.SimpleTrapEntity;
import com.infamous.dungeons_mobs.entities.summonables.SummonSpotEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SimpleTrapRenderer extends GeoEntityRenderer<SimpleTrapEntity> {
    public SimpleTrapRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SimpleTrapModel<SimpleTrapEntity>());
    }

    @Override
    public void preRender(PoseStack poseStack, SimpleTrapEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {

        float scaleFactor = 2.0F;
        if (animatable.lifeTime <= 1) {
            scaleFactor = 0.0F;
        } else {
            scaleFactor = 2.0F;
        }
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
    }
}