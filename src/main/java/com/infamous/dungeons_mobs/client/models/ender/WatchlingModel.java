package com.infamous.dungeons_mobs.client.models.ender;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.ender.WatchlingEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WatchlingModel extends GeoModel<WatchlingEntity> {

    @Override
    public ResourceLocation getAnimationResource(WatchlingEntity entity) {
        return GeneralUtil.mobsLoc( "animations/watchling.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(WatchlingEntity entity) {
        return GeneralUtil.mobsLoc( "geo/watchling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WatchlingEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/ender/watchling.png");
    }

    @Override
    public void setCustomAnimations(WatchlingEntity entity, long uniqueID, AnimationState<WatchlingEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        GeoBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
    }

    @Override
    public RenderType getRenderType(WatchlingEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}

