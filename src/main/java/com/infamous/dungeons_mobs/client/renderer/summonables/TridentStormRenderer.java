package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.TridentStormModel;
import com.infamous.dungeons_mobs.entities.projectiles.NecromancerOrbEntity;
import com.infamous.dungeons_mobs.entities.summonables.TridentStormEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TridentStormRenderer extends GeoEntityRenderer<TridentStormEntity> {
    public TridentStormRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TridentStormModel());
    }

    @Override
    public void preRender(PoseStack poseStack, TridentStormEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {


        poseStack.mulPose(Axis.YP.rotationDegrees(animatable.getYRot() * ((float) Math.PI / 180F)));

        if (animatable.lifeTime <= 1) {
            float scaleFactor = 0.0F;
            poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        } else {

        }
    }

    @Override
    protected int getBlockLightLevel(TridentStormEntity p_225624_1_, BlockPos p_225624_2_) {
        return 15;
    }
}