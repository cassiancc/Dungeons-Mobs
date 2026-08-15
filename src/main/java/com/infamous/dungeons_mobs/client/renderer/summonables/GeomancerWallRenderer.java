package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.GeomancerConstructModel;
import com.infamous.dungeons_mobs.entities.summonables.GeomancerWallEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GeomancerWallRenderer extends GeoEntityRenderer<GeomancerWallEntity> {
    public GeomancerWallRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeomancerConstructModel());
        //this.addLayer(new GeoEyeLayer<>(this, GeneralUtil.mobsLoc( "textures/entity/enchanter/enchanter_eyes.png")));
        //this.addLayer(new GeoHeldItemLayer<>(this, 0.0, 0.0, 0.5));
    }

    @Override
    protected void applyRotations(GeomancerWallEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
    }
}
