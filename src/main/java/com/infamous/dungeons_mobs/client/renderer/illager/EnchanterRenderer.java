package com.infamous.dungeons_mobs.client.renderer.illager;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.illager.EnchanterModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.entities.illagers.EnchanterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EnchanterRenderer extends GeoEntityRenderer<EnchanterEntity> {
    public EnchanterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EnchanterModel());
        this.addRenderLayer(new GeoEyeLayer<>(this, GeneralUtil.mobsLoc( "textures/entity/enchanter/enchanter_eyes.png")));
        //this.addLayer(new GeoHeldItemLayer<>(this, 0.0, 0.0, 0.5));
    }

    @Override
    protected void applyRotations(EnchanterEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        float scaleFactor = 0.9375F;
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
    }
}
