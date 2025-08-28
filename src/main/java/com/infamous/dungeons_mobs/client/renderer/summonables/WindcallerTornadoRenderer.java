package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.WindcallerTornadoModel;
import com.infamous.dungeons_mobs.entities.summonables.WindcallerTornadoEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WindcallerTornadoRenderer extends GeoEntityRenderer<WindcallerTornadoEntity> {
    public WindcallerTornadoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WindcallerTornadoModel());
    }

    @Override
    public void renderEarly(WindcallerTornadoEntity animatable, PoseStack stackIn, float partialTicks,
                            MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float alpha) {
        if (!animatable.isBlast()) {
            float scaleFactor = 1.25F;
            stackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        } else {
            stackIn.mulPose(Axis.YP.rotationDegrees(animatable.getYRot() * ((float) Math.PI / 180F)));
        }

        if (animatable.lifeTime <= 1) {
            float scaleFactor = 0.0F;
            stackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        } else {

        }
    }
}