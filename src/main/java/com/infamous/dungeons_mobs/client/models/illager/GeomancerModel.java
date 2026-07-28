package com.infamous.dungeons_mobs.client.models.illager;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.illagers.GeomancerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.loading.math.MathParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GeomancerModel extends GeoModel<GeomancerEntity> {

    @Override
    public ResourceLocation getAnimationResource(GeomancerEntity entity) {
        return GeneralUtil.mobsLoc( "animations/geomancer.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(GeomancerEntity entity) {
        return GeneralUtil.mobsLoc( "geo/geomancer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeomancerEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/illager/geomancer.png");
    }

    @Override
    public void setCustomAnimations(GeomancerEntity entity, long uniqueID, AnimationState<GeomancerEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("bipedHead");
        var cape = this.getAnimationProcessor().getBone("bipedCape");
        var illagerArms = this.getAnimationProcessor().getBone("illagerArms");

        cape.setHidden(true);
        illagerArms.setHidden(true);

        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MathParser.setVariable("query.ground_speed", () -> groundSpeed * 20);
    }
}