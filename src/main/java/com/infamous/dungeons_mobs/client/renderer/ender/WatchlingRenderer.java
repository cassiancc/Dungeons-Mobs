package com.infamous.dungeons_mobs.client.renderer.ender;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.ender.WatchlingModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.entities.ender.AbstractEnderlingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WatchlingRenderer extends GeoEntityRenderer<AbstractEnderlingEntity> {
    public WatchlingRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WatchlingModel());
        this.addRenderLayer(new GeoEyeLayer<>(this, new ResourceLocation(DungeonsMobs.MODID, "textures/entity/ender/watchling_eyes.png")));
    }

}
