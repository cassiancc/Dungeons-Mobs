package com.infamous.dungeons_mobs.client.models.summonables;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.summonables.IceCloudEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class IceCloudModel extends GeoModel<IceCloudEntity> {

    @Override
    public ResourceLocation getAnimationResource(IceCloudEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/ice_chunk.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(IceCloudEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/ice_chunk.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IceCloudEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/ice_chunk.png");
    }

    @Override
    public RenderType getRenderType(IceCloudEntity animatable, ResourceLocation resourceLocation) {
        return RenderType.entityTranslucent(resourceLocation);
    }
}