package com.infamous.dungeons_mobs.client.models.undead;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.undead.WraithEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WraithModel extends GeoModel<WraithEntity> {

    @Override
    public ResourceLocation getAnimationResource(WraithEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/wraith.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(WraithEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/wraith.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WraithEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/wraith/wraith.png");
    }

    @Override
    public RenderType getRenderType(WraithEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(WraithEntity entity, long uniqueID, AnimationState<WraithEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("bipedHead");
        var cape = this.getAnimationProcessor().getBone("bipedCape");

        cape.setHidden(true);

        var leftHand = this.getAnimationProcessor().getBone("bipedHandLeft");
        var rightHand = this.getAnimationProcessor().getBone("bipedHandRight");

        if (entity.tickCount % 2 == 0 && rightHand instanceof GeoBone && leftHand instanceof GeoBone && entity.isSpellcasting()) {
            GeoBone leftHandBone = ((GeoBone) leftHand);
            GeoBone rightHandBone = ((GeoBone) rightHand);
            entity.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, leftHandBone.getWorldPosition().x, leftHandBone.getWorldPosition().y, leftHandBone.getWorldPosition().z, entity.getRandom().nextGaussian() * 0.01, entity.getRandom().nextGaussian() * 0.01, entity.getRandom().nextGaussian() * 0.01);
            entity.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, rightHandBone.getWorldPosition().x, rightHandBone.getWorldPosition().y, rightHandBone.getWorldPosition().z, entity.getRandom().nextGaussian() * 0.01, entity.getRandom().nextGaussian() * 0.01, entity.getRandom().nextGaussian() * 0.01);
        }

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
    }
}
