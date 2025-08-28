package com.infamous.dungeons_mobs.client.renderer.armor;

import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearRenderer;
import com.infamous.dungeons_mobs.client.models.armor.IceologerArmorGearModel;
import com.infamous.dungeons_mobs.items.armor.IceologerArmorGear;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class IceologerArmorGearRenderer extends ArmorGearRenderer<IceologerArmorGear> {

    private final LivingEntity livingEntity;

    public IceologerArmorGearRenderer(LivingEntity livingEntity) {
        super(new IceologerArmorGearModel<>().getWearer());
        this.livingEntity = livingEntity;
    }

    @Override
    public void renderRecursively(PoseStack poseStack, IceologerArmorGear animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        GeoModel<IceologerArmorGear> geoModelProvider = getGeoModel();
        if (geoModelProvider instanceof IceologerArmorGearModel) {
            ((IceologerArmorGearModel<IceologerArmorGear>) geoModelProvider).setWearer(this.livingEntity);
        }
        super.renderRecursively(poseStack, animatable, getBodyBone(), renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
