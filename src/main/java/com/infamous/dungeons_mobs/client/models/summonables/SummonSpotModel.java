package com.infamous.dungeons_mobs.client.models.summonables;


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
            return new ResourceLocation(DungeonsMobs.MODID, "animations/illusioner_summon_spot.animation.json");
        } else if (entity.getSummonType() == 1) {
            return new ResourceLocation(DungeonsMobs.MODID, "animations/wildfire_summon_spot.animation.json");
        } else if (entity.getSummonType() == 2) {
            return new ResourceLocation(DungeonsMobs.MODID, "animations/illusioner_summon_spot.animation.json");
        } else if (entity.getSummonType() == 3) {
            return new ResourceLocation(DungeonsMobs.MODID, "animations/illusioner_summon_spot.animation.json");
        } else {
            return new ResourceLocation(DungeonsMobs.MODID, "animations/illusioner_summon_spot.animation.json");
        }
    }

    @Override
    public ResourceLocation getModelResource(T entity) {
        if (entity.getSummonType() == 0) {
            return new ResourceLocation(DungeonsMobs.MODID, "geo/illusioner_summon_spot.geo.json");
        } else if (entity.getSummonType() == 1) {
            return new ResourceLocation(DungeonsMobs.MODID, "geo/wildfire_summon_spot.geo.json");
        } else if (entity.getSummonType() == 2) {
            return new ResourceLocation(DungeonsMobs.MODID, "geo/illusioner_summon_spot.geo.json");
        } else if (entity.getSummonType() == 3) {
            return new ResourceLocation(DungeonsMobs.MODID, "geo/illusioner_summon_spot.geo.json");
        } else {
            return new ResourceLocation(DungeonsMobs.MODID, "geo/illusioner_summon_spot.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(T entity) {
        if (entity.getSummonType() == 0) {
            return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/illusioner_summon_spot.png");
        } else if (entity.getSummonType() == 1) {
            return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/wildfire_summon_spot.png");
        } else if (entity.getSummonType() == 2) {
            return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/necromancer_summon_spot.png");
        } else if (entity.getSummonType() == 3) {
            return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/mage_summon_spot.png");
        } else {
            return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/illusioner_summon_spot.png");
        }
    }
}