package com.infamous.dungeons_mobs.client.models.redstone;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.redstone.RedstoneGolemEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RedstoneGolemModel extends GeoModel<RedstoneGolemEntity> {

    @Override
    public ResourceLocation getAnimationResource(RedstoneGolemEntity entity) {
        return GeneralUtil.mobsLoc( "animations/redstone_golem.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(RedstoneGolemEntity entity) {
        return GeneralUtil.mobsLoc( "geo/redstone_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RedstoneGolemEntity entity) {
        //ChorusGormandizerEntity entityIn = (ChorusGormandizerEntity) entity;
        return GeneralUtil.mobsLoc( "textures/entity/redstone/redstone_golem.png");
    }

    @Override
    public RenderType getRenderType(RedstoneGolemEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(RedstoneGolemEntity entity, long uniqueID, AnimationState<RedstoneGolemEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        RedstoneGolemEntity entityIn = (RedstoneGolemEntity) entity;

        var head = this.getAnimationProcessor().getBone("head");

        // FIXME
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
    }
}