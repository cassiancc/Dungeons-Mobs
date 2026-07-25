package com.infamous.dungeons_mobs.client.models.illager;

import com.infamous.dungeons_libraries.entities.SpawnArmoredMob;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.config.DungeonsMobsConfig;
import com.infamous.dungeons_mobs.entities.illagers.MageCloneEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MageCloneModel extends GeoModel<MageCloneEntity> {

    @Override
    public ResourceLocation getAnimationResource(MageCloneEntity entity) {
        return GeneralUtil.mobsLoc( "animations/mage.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(MageCloneEntity entity) {
        return GeneralUtil.mobsLoc( "geo/geo_illager.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MageCloneEntity entity) {
        if(DungeonsMobsConfig.COMMON.ENABLE_3D_SLEEVES.get()){
            return GeneralUtil.mobsLoc( "textures/entity/illager/mage.png");
        }else{
            return GeneralUtil.mobsLoc( "textures/entity/illager/mage_sleeved.png");
        }
    }

    @Override
    public void setCustomAnimations(MageCloneEntity entity, long uniqueID, AnimationState<MageCloneEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        LivingEntity entityIn = (LivingEntity) entity;

        var head = this.getAnimationProcessor().getBone("bipedHead");
        var illagerArms = this.getAnimationProcessor().getBone("illagerArms");

        illagerArms.setHidden(true);

        var cape = this.getAnimationProcessor().getBone("bipedCape");
        if (entity instanceof SpawnArmoredMob && entity instanceof Mob) {
            Mob mobEntity = (Mob) entity;
            cape.setHidden(mobEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() != ((SpawnArmoredMob) entity).getArmorSet().getChest().get());
        }
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MolangParser.INSTANCE.setValue("query.ground_speed", () -> groundSpeed * 15);
    }
}

