package com.infamous.dungeons_mobs.client.models.illager;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.illagers.RoyalGuardEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RoyalGuardModel extends GeoModel<RoyalGuardEntity> {

    @Override
    public ResourceLocation getAnimationResource(RoyalGuardEntity entity) {
        return GeneralUtil.mobsLoc( "animations/royal_guard.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(RoyalGuardEntity entity) {
        return GeneralUtil.mobsLoc( "geo/geo_illager.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RoyalGuardEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/illager/royal_guard.png");
    }

    @Override
    public void setCustomAnimations(RoyalGuardEntity entity, long uniqueID, AnimationState<RoyalGuardEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        LivingEntity entityIn = entity;

        var head = this.getAnimationProcessor().getBone("bipedHeadBaseRotator");
        var armorHead = this.getAnimationProcessor().getBone("armorBipedHead");
        var illagerArms = this.getAnimationProcessor().getBone("illagerArms");
        var cape = this.getAnimationProcessor().getBone("bipedCape");

        illagerArms.setHidden(true);
        cape.setHidden(true);

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entityIn.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MolangParser.INSTANCE.setValue("query.ground_speed", () -> groundSpeed * 20);
    }
}
