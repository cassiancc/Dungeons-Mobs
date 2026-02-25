package com.infamous.dungeons_mobs.client.models.summonables;


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.summonables.SummonSpotEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SummonSpotModel<T extends SummonSpotEntity> extends GeoModel<T> {

    @Override
    public RenderType getRenderType(SummonSpotEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(T entity) {
        if (entity.getSummonType() == 0) {
            return GeneralUtil.mobsLoc( "animations/illusioner_summon_spot.animation.json");
        } else if (entity.getSummonType() == 1) {
            return GeneralUtil.mobsLoc( "animations/wildfire_summon_spot.animation.json");
        } else if (entity.getSummonType() == 2) {
            return GeneralUtil.mobsLoc( "animations/illusioner_summon_spot.animation.json");
        } else if (entity.getSummonType() == 3) {
            return GeneralUtil.mobsLoc( "animations/illusioner_summon_spot.animation.json");
        } else {
            return GeneralUtil.mobsLoc( "animations/illusioner_summon_spot.animation.json");
        }
    }

    @Override
    public ResourceLocation getModelResource(T entity) {
        if (entity.getSummonType() == 0) {
            return GeneralUtil.mobsLoc( "geo/illusioner_summon_spot.geo.json");
        } else if (entity.getSummonType() == 1) {
            return GeneralUtil.mobsLoc( "geo/wildfire_summon_spot.geo.json");
        } else if (entity.getSummonType() == 2) {
            return GeneralUtil.mobsLoc( "geo/illusioner_summon_spot.geo.json");
        } else if (entity.getSummonType() == 3) {
            return GeneralUtil.mobsLoc( "geo/illusioner_summon_spot.geo.json");
        } else {
            return GeneralUtil.mobsLoc( "geo/illusioner_summon_spot.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(T entity) {
        if (entity.getSummonType() == 0) {
            return GeneralUtil.mobsLoc( "textures/entity/illusioner_summon_spot.png");
        } else if (entity.getSummonType() == 1) {
            return GeneralUtil.mobsLoc( "textures/entity/wildfire_summon_spot.png");
        } else if (entity.getSummonType() == 2) {
            return GeneralUtil.mobsLoc( "textures/entity/necromancer_summon_spot.png");
        } else if (entity.getSummonType() == 3) {
            return GeneralUtil.mobsLoc( "textures/entity/mage_summon_spot.png");
        } else {
            return GeneralUtil.mobsLoc( "textures/entity/illusioner_summon_spot.png");
        }
    }
}