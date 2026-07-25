package com.infamous.dungeons_mobs.client.models.illager;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.config.DungeonsMobsConfig;
import com.infamous.dungeons_mobs.entities.illagers.DungeonsIllusionerEntity;
import com.infamous.dungeons_mobs.entities.illagers.IllusionerCloneEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class IllusionerCloneModel extends GeoModel<IllusionerCloneEntity> {

    @Override
    public ResourceLocation getAnimationResource(IllusionerCloneEntity entity) {
        return GeneralUtil.mobsLoc( "animations/illusioner.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(IllusionerCloneEntity entity) {
        return GeneralUtil.mobsLoc( "geo/geo_illager.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IllusionerCloneEntity entity) {
        if(DungeonsMobsConfig.COMMON.ENABLE_3D_SLEEVES.get()){
            return GeneralUtil.mobsLoc( "textures/entity/illager/illusioner.png");
        }else{
            return GeneralUtil.mobsLoc( "textures/entity/illager/illusioner_sleeved.png");
        }
    }

    @Override
    public void setCustomAnimations(IllusionerCloneEntity entity, long uniqueID, AnimationState<IllusionerCloneEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("bipedHead");

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MolangParser.INSTANCE.setValue("query.ground_speed", () -> groundSpeed * 20);
    }

}
