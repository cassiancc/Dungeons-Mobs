package com.infamous.dungeons_mobs.client.models.summonables;


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.summonables.SimpleTrapEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SimpleTrapModel<T extends SimpleTrapEntity> extends GeoModel<T> {

    @Override
    public ResourceLocation getAnimationResource(T entity) {
        return GeneralUtil.mobsLoc( "animations/trap.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T entity) {
        return GeneralUtil.mobsLoc( "geo/trap.geo.json");
    }

    @Override
    public RenderType getRenderType(SimpleTrapEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public ResourceLocation getTextureResource(T entity) {
        if (entity.getTrapType() == 0) {
            return GeneralUtil.mobsLoc( "textures/entity/web_trap.png");
        } else if (entity.getTrapType() == 1) {
            return GeneralUtil.mobsLoc( "textures/entity/vine_trap.png");
        } else {
            return GeneralUtil.mobsLoc( "textures/entity/web_trap.png");
        }
    }
}