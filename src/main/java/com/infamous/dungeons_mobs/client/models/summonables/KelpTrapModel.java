package com.infamous.dungeons_mobs.client.models.summonables;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.summonables.KelpTrapEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KelpTrapModel<T extends KelpTrapEntity> extends GeoModel<T> {

    @Override
    public ResourceLocation getAnimationResource(T entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/trap.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/trap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/kelp_trap.png");
    }

    @Override
    public RenderType getRenderType(KelpTrapEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}