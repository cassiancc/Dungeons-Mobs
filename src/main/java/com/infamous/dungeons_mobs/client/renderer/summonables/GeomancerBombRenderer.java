package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.summonables.GeomancerConstructModel;
import com.infamous.dungeons_mobs.client.renderer.layers.PulsatingGlowLayer;
import com.infamous.dungeons_mobs.entities.summonables.GeomancerBombEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GeomancerBombRenderer extends GeoEntityRenderer<GeomancerBombEntity> {
    public GeomancerBombRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeomancerConstructModel());
        this.addRenderLayer(new PulsatingGlowLayer<GeomancerBombEntity>(this, new ResourceLocation(DungeonsMobs.MODID, "textures/entity/constructs/geomancer_bomb.png"), 0.5F, 0.6F, 0.2F) {
            @Override
            public void render(PoseStack matrixStackIn, GeomancerBombEntity entity, BakedGeoModel model, RenderType type, MultiBufferSource bufferIn, VertexConsumer vertexConsumer, float limbSwing, int i, int i2) {

                if (entity.getLifeTicks() < 60 && entity.getLifeTicks() >= 30) {
                    textureLocation = new ResourceLocation(DungeonsMobs.MODID, "textures/entity/constructs/geomancer_bomb_eyes_1.png");
                    super.render(matrixStackIn, entity, model, type, bufferIn, vertexConsumer, limbSwing, i, i2);
                } else if (entity.getLifeTicks() < 30 && entity.getLifeTicks() >= 0) {
                    pulseSpeed = 0.8F;
                    textureLocation = new ResourceLocation(DungeonsMobs.MODID, "textures/entity/constructs/geomancer_bomb_eyes_2.png");
                    super.render(matrixStackIn, entity, model, type, bufferIn, vertexConsumer, limbSwing, i, i2);
                }
            }
        });
    }

    protected void applyRotations(GeomancerBombEntity entityLiving, PoseStack matrixStackIn, float ageInTicks,
                                  float rotationYaw, float partialTicks) {

        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    //FIXME
//    @Override
    public RenderType getRenderType(GeomancerBombEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
