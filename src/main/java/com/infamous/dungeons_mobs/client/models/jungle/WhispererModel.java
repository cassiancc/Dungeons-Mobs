package com.infamous.dungeons_mobs.client.models.jungle;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.particle.ModParticleTypes;
import com.infamous.dungeons_mobs.entities.jungle.WhispererEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WhispererModel<T extends WhispererEntity> extends GeoModel<T> {

    @Override
    public ResourceLocation getAnimationResource(T entity) {
        return GeneralUtil.mobsLoc( "animations/whisperer.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T entity) {
        return GeneralUtil.mobsLoc( "geo/whisperer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T entity) {
        return GeneralUtil.mobsLoc( "textures/entity/jungle/whisperer.png");
    }

    @Override
    public RenderType getRenderType(WhispererEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("jaw");
        var cape = this.getAnimationProcessor().getBone("bipedCape");

        cape.setHidden(true);

        var leftHand = this.getAnimationProcessor().getBone("bipedHandLeft");
        var rightHand = this.getAnimationProcessor().getBone("bipedHandRight");

        if (entity.tickCount % 1 == 0 && rightHand instanceof GeoBone && leftHand instanceof GeoBone && entity.isSpellcasting()) {
            var leftHandBone = ((GeoBone) leftHand);
            var rightHandBone = ((GeoBone) rightHand);
            entity.level().addParticle(ModParticleTypes.CORRUPTED_MAGIC.get(), leftHandBone.getWorldPosition().x, leftHandBone.getWorldPosition().y, leftHandBone.getWorldPosition().z, 0, 0, 0);
            entity.level().addParticle(ModParticleTypes.CORRUPTED_MAGIC.get(), rightHandBone.getWorldPosition().x, rightHandBone.getWorldPosition().y, rightHandBone.getWorldPosition().z, 0, 0, 0);
        }

        if (entity.tickCount % 2 == 0 && rightHand instanceof GeoBone && leftHand instanceof GeoBone && entity.isSpellcasting()) {
            GeoBone leftHandBone = ((GeoBone) leftHand);
            GeoBone rightHandBone = ((GeoBone) rightHand);
            entity.level().addParticle(ModParticleTypes.CORRUPTED_DUST.get(), leftHandBone.getWorldPosition().x, leftHandBone.getWorldPosition().y, leftHandBone.getWorldPosition().z, entity.getRandom().nextGaussian() * 0.01, entity.getRandom().nextGaussian() * 0.01, entity.getRandom().nextGaussian() * 0.01);
            entity.level().addParticle(ModParticleTypes.CORRUPTED_DUST.get(), rightHandBone.getWorldPosition().x, rightHandBone.getWorldPosition().y, rightHandBone.getWorldPosition().z, entity.getRandom().nextGaussian() * 0.01, entity.getRandom().nextGaussian() * 0.01, entity.getRandom().nextGaussian() * 0.01);
        }

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MolangParser.INSTANCE.setValue("query.ground_speed", () -> groundSpeed * 12.5);
    }
}
