package com.infamous.dungeons_mobs.client.renderer.layers;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.ender.BlastlingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class GeoEyeLayer<T extends LivingEntity & GeoAnimatable> extends GeoRenderLayer<T> {

    public ResourceLocation textureLocation;

    public GeoEyeLayer(GeoRenderer<T> endermanReplacementRenderer, ResourceLocation textureLocation) {
        super(endermanReplacementRenderer);
        this.textureLocation = textureLocation;
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType,
                       MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
                       int packedLight, int packedOverlay) {

        GeoModel<T> geomodel = this.getGeoModel();
//        if (animatable instanceof BlastlingEntity) {
//            renderModel(geomodel, GeneralUtil.mobsLoc( "textures/entity/ender/blastling" + (1 + ((int) ((BlastlingEntity) entitylivingbaseIn).flameTicks) % 3) + "_eyes.png"), matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0F, 1.0F, 1.0F, 1.0F);
//        } else {
//            renderModel(geomodel, textureLocation, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0F, 0.8F, 0.8F, 0.8F);
//        }
    }

//    @Override
//    public RenderType getRenderType(ResourceLocation textureLocation) {
//        return RenderType.eyes(textureLocation);
//    }

}