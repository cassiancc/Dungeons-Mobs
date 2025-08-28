package com.infamous.dungeons_mobs.client.models.illager;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.config.DungeonsMobsConfig;
import com.infamous.dungeons_mobs.entities.illagers.WindcallerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WindcallerModel extends GeoModel<WindcallerEntity> {

    @Override
    public ResourceLocation getAnimationResource(WindcallerEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/windcaller.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(WindcallerEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/windcaller.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WindcallerEntity entity) {
        if(DungeonsMobsConfig.COMMON.ENABLE_3D_SLEEVES.get()){
            return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/illager/windcaller.png");
        }else{
            return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/illager/windcaller_sleeved.png");
        }
    }

    @Override
    public void setCustomAnimations(WindcallerEntity entity, long uniqueID, AnimationState<WindcallerEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        LivingEntity entityIn = entity;

        var head = this.getAnimationProcessor().getBone("bipedHead");

        var cape = this.getAnimationProcessor().getBone("bipedCape");
        cape.setHidden(entity.getItemBySlot(EquipmentSlot.CHEST).getItem() != entity.getArmorSet().getChest().get());

        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
    }
}
