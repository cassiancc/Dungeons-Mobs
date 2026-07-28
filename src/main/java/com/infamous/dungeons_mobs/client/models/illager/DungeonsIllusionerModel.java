package com.infamous.dungeons_mobs.client.models.illager;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.config.DungeonsMobsConfig;
import com.infamous.dungeons_mobs.entities.illagers.DungeonsIllusionerEntity;
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

public class DungeonsIllusionerModel extends GeoModel<DungeonsIllusionerEntity> {

    @Override
    public ResourceLocation getAnimationResource(DungeonsIllusionerEntity entity) {
        return GeneralUtil.mobsLoc( "animations/illusioner.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(DungeonsIllusionerEntity entity) {
        return GeneralUtil.mobsLoc( "geo/geo_illager.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DungeonsIllusionerEntity entity) {
        if(DungeonsMobsConfig.COMMON.ENABLE_3D_SLEEVES.get()){
            return GeneralUtil.mobsLoc( "textures/entity/illager/illusioner.png");
        }else{
            return GeneralUtil.mobsLoc( "textures/entity/illager/illusioner_sleeved.png");
        }
    }

    @Override
    public void setCustomAnimations(DungeonsIllusionerEntity entity, long uniqueID, AnimationState<DungeonsIllusionerEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("bipedHead");

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MathParser.setVariable("query.ground_speed", () -> groundSpeed * 20);
    }

}
