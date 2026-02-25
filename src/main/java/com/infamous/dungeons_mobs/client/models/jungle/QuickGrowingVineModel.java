package com.infamous.dungeons_mobs.client.models.jungle;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.jungle.AbstractVineEntity;
import net.minecraft.resources.ResourceLocation;

public class QuickGrowingVineModel extends AbstractVineModel {
    @Override
    public ResourceLocation getAnimationResource(AbstractVineEntity entity) {
        return GeneralUtil.mobsLoc( "animations/quick_growing_vine.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(AbstractVineEntity entity) {
        return GeneralUtil.mobsLoc( "geo/quick_growing_vine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AbstractVineEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/jungle/quick_growing_vine.png");
    }
}