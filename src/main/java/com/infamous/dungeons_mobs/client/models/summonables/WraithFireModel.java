package com.infamous.dungeons_mobs.client.models.summonables;


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.summonables.WraithFireEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class WraithFireModel extends GeoModel<WraithFireEntity> {

    @Override
    public ResourceLocation getAnimationResource(WraithFireEntity entity) {
        return GeneralUtil.mobsLoc( "animations/wraith_fire.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(WraithFireEntity entity) {
        return GeneralUtil.mobsLoc( "geo/wraith_fire.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WraithFireEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/wraith_fire/wraith_fire_" + entity.textureChange % 31 + ".png");
    }

    @Override
    public RenderType getRenderType(WraithFireEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(WraithFireEntity entity, long uniqueID, AnimationState<WraithFireEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

    }
}