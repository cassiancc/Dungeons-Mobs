package com.infamous.dungeons_mobs.client.models.blaze;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.blaze.WildfireEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.loading.math.MathParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WildfireModel extends GeoModel<WildfireEntity> {

    @Override
    public ResourceLocation getAnimationResource(WildfireEntity entity) {
        return GeneralUtil.mobsLoc( "animations/wildfire.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(WildfireEntity entity) {
        return GeneralUtil.mobsLoc( "geo/wildfire.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WildfireEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/blaze/wildfire.png");
    }

    @Override
    public RenderType getRenderType(WildfireEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(WildfireEntity entity, long uniqueID, AnimationState<WildfireEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        LivingEntity entityIn = (LivingEntity) entity;

        var head = this.getAnimationProcessor().getBone("head");

        var shield1 = this.getAnimationProcessor().getBone("shield1");
        var shield2 = this.getAnimationProcessor().getBone("shield2");
        var shield3 = this.getAnimationProcessor().getBone("shield3");
        var shield4 = this.getAnimationProcessor().getBone("shield4");

        WildfireEntity wildfire = ((WildfireEntity) entity);

        if (wildfire.getShields() >= 4) {
            shield1.setHidden(false);
            shield2.setHidden(false);
            shield3.setHidden(false);
            shield4.setHidden(false);
        } else if (wildfire.getShields() == 3) {
            shield1.setHidden(true);
            shield2.setHidden(false);
            shield3.setHidden(false);
            shield4.setHidden(false);
        } else if (wildfire.getShields() == 2) {
            shield1.setHidden(true);
            shield2.setHidden(true);
            shield3.setHidden(false);
            shield4.setHidden(false);
        } else if (wildfire.getShields() == 1) {
            shield1.setHidden(true);
            shield2.setHidden(true);
            shield3.setHidden(true);
            shield4.setHidden(false);
        } else if (wildfire.getShields() <= 0) {
            shield1.setHidden(true);
            shield2.setHidden(true);
            shield3.setHidden(true);
            shield4.setHidden(true);
        }

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MathParser.setVariable("query.ground_speed", () -> groundSpeed * 30);
    }

}
