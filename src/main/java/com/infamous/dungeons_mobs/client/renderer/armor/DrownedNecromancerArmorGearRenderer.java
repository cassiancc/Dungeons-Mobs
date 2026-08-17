package com.infamous.dungeons_mobs.client.renderer.armor;

import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearRenderer;
import com.infamous.dungeons_mobs.client.models.armor.DrownedNecromancerArmorGearModel;
import com.infamous.dungeons_mobs.items.armor.DrownedNecromancerArmorGear;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class DrownedNecromancerArmorGearRenderer extends ArmorGearRenderer<DrownedNecromancerArmorGear> {

    public String hoodBone = "armorHood";

    public DrownedNecromancerArmorGearRenderer(LivingEntity livingEntity) {
        super(new DrownedNecromancerArmorGearModel<>(livingEntity), livingEntity);
    }

    //FIXME
//    @Override
//    public void fitToBiped() {
//        super.fitToBiped();
//        if (this.hoodBone != null) {
//            IBone hoodBone = this.getGeoModelProvider().getBone(this.hoodBone);
//            GeoUtils.copyRotations(this.head, hoodBone);
//            hoodBone.setPositionX(this.head.x);
//            hoodBone.setPositionY(-this.head.y);
//            hoodBone.setPositionZ(this.head.z);
//        }
//    }
//
//    @SuppressWarnings("incomplete-switch")
//    @Override
//    public GeoArmorRenderer applySlot(EquipmentSlot slot) {
//        super.applySlot(slot);
//
//        this.getGeoModelProvider().getModel(this.getGeoModelProvider().getModelResource(currentArmorItem));
//
//        IBone hoodBone = this.getAndHideBone(this.hoodBone);
//
//        switch (slot) {
//            case HEAD:
//                if (hoodBone != null)
//                    hoodBone.setHidden(false);
//                break;
//            case CHEST:
//                break;
//            case LEGS:
//                break;
//            case FEET:
//                break;
//        }
//        return this;
//    }

    @Override
    public void renderRecursively(PoseStack poseStack, DrownedNecromancerArmorGear animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        GeoModel<DrownedNecromancerArmorGear> geoModelProvider = getGeoModel();
        if (geoModelProvider instanceof DrownedNecromancerArmorGearModel<DrownedNecromancerArmorGear>) {
            ((DrownedNecromancerArmorGearModel<DrownedNecromancerArmorGear>) geoModelProvider).setWearer((LivingEntity) this.currentEntity);
        }
        super.renderRecursively(poseStack, animatable, getBodyBone(), renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
