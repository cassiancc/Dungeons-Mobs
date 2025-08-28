package com.infamous.dungeons_mobs.client.models.projectile;


import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.projectiles.MageMissileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class MageMissileModel extends GeoModel<MageMissileEntity> {

    @Override
    public RenderType getRenderType(MageMissileEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(MageMissileEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/mage_missile.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(MageMissileEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/mage_missile.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MageMissileEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/projectile/mage_missile.png");
    }

    @Override
    public void setCustomAnimations(MageMissileEntity entity, long uniqueID, AnimationState<MageMissileEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone everything = this.getAnimationProcessor().getBone("everything");

        everything.setRotY(-1.5708F);
    }
}