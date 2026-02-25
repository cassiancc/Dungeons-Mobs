package com.infamous.dungeons_mobs.client.renderer.redstone;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.redstone.RedstoneGolemModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.client.renderer.layers.PulsatingGlowLayer;
import com.infamous.dungeons_mobs.entities.redstone.RedstoneGolemEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RedstoneGolemRenderer extends GeoEntityRenderer<RedstoneGolemEntity> {
    @SuppressWarnings("unchecked")
    public RedstoneGolemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RedstoneGolemModel());
        this.addRenderLayer(new GeoEyeLayer<>(this, GeneralUtil.mobsLoc( "textures/entity/redstone/redstone_golem_light.png")) {
            @Override
            public void render(PoseStack matrixStackIn, RedstoneGolemEntity entitylivingbaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

                if (!entitylivingbaseIn.isSummoningMines()) {
                    super.render(matrixStackIn, entitylivingbaseIn, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);

                }
            }
        });
        this.addRenderLayer(new PulsatingGlowLayer<>(this, GeneralUtil.mobsLoc( "textures/entity/redstone/redstone_golem_yellow_light.png"), 0.1F, 0.5F, 0.0F) {
            @Override
            public void render(PoseStack matrixStackIn, RedstoneGolemEntity entitylivingbaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {


                if (!entitylivingbaseIn.isSummoningMines()) {
                    super.render(matrixStackIn, entitylivingbaseIn, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
                }
            }
        });
        this.addRenderLayer(new GeoEyeLayer<>(this, GeneralUtil.mobsLoc( "textures/entity/redstone/redstone_golem_yellow_light.png")) {
            @Override
            public void render(PoseStack matrixStackIn, RedstoneGolemEntity entitylivingbaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

                if (entitylivingbaseIn.isSummoningMines()) {
                    super.render(matrixStackIn, entitylivingbaseIn, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);

                }
            }
        });
        this.addRenderLayer(new PulsatingGlowLayer<>(this, GeneralUtil.mobsLoc( "textures/entity/redstone/redstone_golem_white_light.png"), 0.2F, 0.75F, 0.0F) {
            @Override
            public void render(PoseStack poseStack, RedstoneGolemEntity animatable, BakedGeoModel bakedModel, RenderType renderType,
                               MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
                               int packedLight, int packedOverlay) {

                if (animatable.isSummoningMines()) {
                    super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight,
                            packedOverlay);
                }
            }
        });
    }

    protected void applyRotations(RedstoneGolemEntity entityLiving, PoseStack matrixStackIn, float ageInTicks,
                                  float rotationYaw, float partialTicks) {
        float scaleFactor = 1.0f;
        matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }
}