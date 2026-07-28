package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.WraithFireModel;
import com.infamous.dungeons_mobs.entities.projectiles.NecromancerOrbEntity;
import com.infamous.dungeons_mobs.entities.summonables.WraithFireEntity;
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

public class WraithFireRenderer extends GeoEntityRenderer<WraithFireEntity> {
    public WraithFireRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WraithFireModel());
    }

    @Override
    public void preRender(PoseStack poseStack, WraithFireEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {

        float scaleFactor = 1.0F;
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
    }

    @Override
    protected int getBlockLightLevel(WraithFireEntity p_225624_1_, BlockPos p_225624_2_) {
        return 15;
    }
}