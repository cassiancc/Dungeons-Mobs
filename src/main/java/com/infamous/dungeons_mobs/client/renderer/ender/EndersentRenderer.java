package com.infamous.dungeons_mobs.client.renderer.ender;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.ender.EndersentModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.entities.ender.AbstractEnderlingEntity;
import com.infamous.dungeons_mobs.entities.ender.EndersentEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EndersentRenderer extends GeoEntityRenderer<EndersentEntity> {
    public EndersentRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EndersentModel());
        this.addRenderLayer(new GeoEyeLayer<>(this, GeneralUtil.mobsLoc( "textures/entity/ender/endersent_eyes.png")));
    }

    protected float getDeathMaxRotation(AbstractEnderlingEntity p_77037_1_) {
        return 0.0F;
    }
}
