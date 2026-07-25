package com.infamous.dungeons_mobs.client.models.ender;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.ender.AbstractEnderlingEntity;
import com.infamous.dungeons_mobs.entities.ender.BlastlingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BlastlingModel extends GeoModel<BlastlingEntity> {

    @Override
    public ResourceLocation getAnimationResource(BlastlingEntity entity) {
        return GeneralUtil.mobsLoc( "animations/blastling.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(BlastlingEntity entity) {
        return GeneralUtil.mobsLoc( "geo/blastling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlastlingEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/ender/blastling" + (1 + ((int) ((BlastlingEntity) entity).flameTicks) % 3) + ".png");
    }

    @Override
    public RenderType getRenderType(BlastlingEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(BlastlingEntity entity, long uniqueID, AnimationState<BlastlingEntity> customPredicate) {
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

