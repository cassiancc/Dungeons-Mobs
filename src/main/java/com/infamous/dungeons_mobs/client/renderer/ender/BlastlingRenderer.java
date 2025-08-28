package com.infamous.dungeons_mobs.client.renderer.ender;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.ender.BlastlingModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.entities.ender.AbstractEnderlingEntity;
import com.infamous.dungeons_mobs.entities.ender.BlastlingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BlastlingRenderer extends GeoEntityRenderer<BlastlingEntity> {
    public BlastlingRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BlastlingModel());
        this.addRenderLayer(new GeoEyeLayer<>(this, new ResourceLocation(DungeonsMobs.MODID, "textures/entity/ender/blastling_eyes.png")));
    }
}
