package com.infamous.dungeons_mobs.client.models.summonables;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.summonables.TridentStormEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TridentStormModel extends GeoModel<TridentStormEntity> {

    @Override
    public ResourceLocation getAnimationResource(TridentStormEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/trident_storm.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(TridentStormEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/trident_storm.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TridentStormEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/trident_storm.png");
    }

    @Override
    public RenderType getRenderType(TridentStormEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}