package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.KelpTrapModel;
import com.infamous.dungeons_mobs.entities.summonables.KelpTrapEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KelpTrapRenderer extends GeoEntityRenderer<KelpTrapEntity> {
    public KelpTrapRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KelpTrapModel<KelpTrapEntity>());
    }

    @Override
    public void preRender(PoseStack stackIn, KelpTrapEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue,
                          float alpha) {
        float scaleFactor = 2.0F;
        stackIn.scale(scaleFactor, scaleFactor, scaleFactor);
    }
}