package com.infamous.dungeons_mobs.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class PulsatingGlowLayer<T extends LivingEntity & GeoAnimatable> extends GeoRenderLayer<T> {

    public ResourceLocation textureLocation;

    public float pulseSpeed;
    public float pulseAmount;
    public float minimumPulseAmount;

    public PulsatingGlowLayer(GeoRenderer<T> endermanReplacementRenderer, ResourceLocation textureLocation, float pulseSpeed, float pulseAmount, float minimumPulseAmount) {
        super(endermanReplacementRenderer);
        this.textureLocation = textureLocation;
        this.pulseSpeed = pulseSpeed;
        this.pulseAmount = pulseAmount;
        this.minimumPulseAmount = minimumPulseAmount;
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType,
                       MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
                       int packedLight, int packedOverlay) {

        GeoModel<T> geomodel = this.getGeoModel();

        // original speed: 0.045F
        // original amount: 0.25F

        float glow = Math.max(minimumPulseAmount, Mth.cos(ageInTicks * pulseSpeed) * pulseAmount);
        renderModel(geomodel, textureLocation, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0F, glow, glow, glow);
    }

    @Override
    public RenderType getRenderType(ResourceLocation textureLocation) {
        return RenderType.eyes(textureLocation);
    }

}