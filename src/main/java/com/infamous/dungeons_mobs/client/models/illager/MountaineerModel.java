package com.infamous.dungeons_mobs.client.models.illager;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.illagers.MountaineerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

// Model and animation received from CQR and DerToaster
public class MountaineerModel extends GeoModel<MountaineerEntity> {

    @Override
    public ResourceLocation getAnimationResource(MountaineerEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/vindicator.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(MountaineerEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/geo_illager.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MountaineerEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/illager/mountaineer.png");
    }

    @Override
    public void setCustomAnimations(MountaineerEntity entity, long uniqueID, AnimationState<MountaineerEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("bipedHead");
        var illagerArms = this.getAnimationProcessor().getBone("illagerArms");

        illagerArms.setHidden(true);

        var cape = this.getAnimationProcessor().getBone("bipedCape");
        cape.setHidden(entity.getItemBySlot(EquipmentSlot.CHEST).getItem() != entity.getArmorSet().getChest().get());

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
