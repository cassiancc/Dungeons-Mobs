package com.infamous.dungeons_mobs.client.models.redstone;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.redstone.RedstoneGolemEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RedstoneGolemModel extends GeoModel<RedstoneGolemEntity> {

    @Override
    public ResourceLocation getAnimationResource(RedstoneGolemEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/redstone_golem.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(RedstoneGolemEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/redstone_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RedstoneGolemEntity entity) {
        //ChorusGormandizerEntity entityIn = (ChorusGormandizerEntity) entity;
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/redstone/redstone_golem.png");
    }

    @Override
    public void setCustomAnimations(RedstoneGolemEntity entity, long uniqueID, AnimationState<RedstoneGolemEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        RedstoneGolemEntity entityIn = (RedstoneGolemEntity) entity;

        var head = this.getAnimationProcessor().getBone("head");

        // FIXME
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraData().get(0);
        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
    }
}