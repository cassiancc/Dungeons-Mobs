package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.WindcallerTornadoModel;
import com.infamous.dungeons_mobs.entities.projectiles.NecromancerOrbEntity;
import com.infamous.dungeons_mobs.entities.summonables.WindcallerTornadoEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WindcallerTornadoRenderer extends GeoEntityRenderer<WindcallerTornadoEntity> {
    public WindcallerTornadoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WindcallerTornadoModel());
    }

    @Override
    public void preRender(PoseStack poseStack, WindcallerTornadoEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {

        if (!animatable.isBlast()) {
            float scaleFactor = 1.25F;
            poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        } else {
            poseStack.mulPose(Axis.YP.rotationDegrees(animatable.getYRot() * ((float) Math.PI / 180F)));
        }

        if (animatable.lifeTime <= 1) {
            float scaleFactor = 0.0F;
            poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        } else {

        }
    }
}