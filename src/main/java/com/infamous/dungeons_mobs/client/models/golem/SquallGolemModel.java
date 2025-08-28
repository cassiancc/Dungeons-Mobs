package com.infamous.dungeons_mobs.client.models.golem;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.golem.SquallGolemEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SquallGolemModel extends GeoModel<SquallGolemEntity> {

    @Override
    public ResourceLocation getAnimationResource(SquallGolemEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/squall_golem.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(SquallGolemEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/squall_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SquallGolemEntity entity) {
        //ChorusGormandizerEntity entityIn = (ChorusGormandizerEntity) entity;
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/golem/squall_golem.png");
    }

    @Override
    public RenderType getRenderType(SquallGolemEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(SquallGolemEntity entity, long uniqueID, AnimationState<SquallGolemEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        SquallGolemEntity entityIn = (SquallGolemEntity) entity;

        var head = this.getAnimationProcessor().getBone("head");
        var eye = this.getAnimationProcessor().getBone("head2");
        var eyeBrow = this.getAnimationProcessor().getBone("head3");

        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            eye.setPosX((float) Math.max(Math.min((extraData.netHeadYaw() / 80) + Math.sin(eye.getPosX() * Math.PI / 180F), 1), -1));
            head.setPosX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setPosZ(head.getRotZ() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
            eye.setPosY(Math.max(Math.min(extraData.headPitch() / 80, 0.15F), -0.2F));
            eyeBrow.setPosY(Math.max(Math.min(extraData.headPitch() / 80, 0.15F), -0.2F));
        }
    }

}
