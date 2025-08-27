package com.infamous.dungeons_mobs.client.models.ender;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.ender.EndersentEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class EndersentModel extends GeoModel<EndersentEntity> {

    @Override
    public ResourceLocation getAnimationResource(EndersentEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/endersent.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(EndersentEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/endersent.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EndersentEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/ender/endersent.png");
    }

    @Override
    public void setCustomAnimations(EndersentEntity entity, long uniqueID, AnimationState<EndersentEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        var head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
    }
}

