package com.infamous.dungeons_mobs.client.renderer.projectiles;

import com.infamous.dungeons_mobs.client.models.projectile.OrbProjectileModel;
import com.infamous.dungeons_mobs.entities.projectiles.NecromancerOrbEntity;
import com.infamous.dungeons_mobs.entities.summonables.SummonSpotEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrbProjectileRenderer extends GeoEntityRenderer<NecromancerOrbEntity> {

    private Color color;

    public OrbProjectileRenderer(EntityRendererProvider.Context renderManager) {
        this(renderManager, Color.WHITE.getColor(), true);
    }

    public OrbProjectileRenderer(EntityRendererProvider.Context renderManager, int color, boolean renderTrail) {
        super(renderManager, new OrbProjectileModel(renderTrail));
        this.color = new Color(color);
    }

    @Override
    public void preRender(PoseStack stackIn, NecromancerOrbEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue,
                          float alpha) {
        float scaleFactor = 1.0F;
        if (animatable.lifeTime <= 3) {
            scaleFactor = 0.0F;
        } else {
            scaleFactor = 1.0F;
        }
        stackIn.scale(scaleFactor, scaleFactor, scaleFactor);
    }

    @Override
    protected int getBlockLightLevel(NecromancerOrbEntity p_225624_1_, BlockPos p_225624_2_) {
        return 15;
    }


    @Override
    public void render(NecromancerOrbEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        float scaleFactor = 1.0F;
        matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    //FIXME
//    @Override
    public Color getRenderColor(NecromancerOrbEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight) {
        return color;
    }
}
