package com.infamous.dungeons_mobs.client.models.projectile;


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.projectiles.CobwebProjectileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class CobwebProjectileModel extends GeoModel<CobwebProjectileEntity> {

    @Override
    public ResourceLocation getAnimationResource(CobwebProjectileEntity entity) {
        return GeneralUtil.mobsLoc( "animations/web_projectile.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(CobwebProjectileEntity entity) {
        return GeneralUtil.mobsLoc( "geo/web_projectile.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CobwebProjectileEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/projectile/web_projectile.png");
    }

    @Override
    public RenderType getRenderType(CobwebProjectileEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(CobwebProjectileEntity entity, long uniqueID, AnimationState<CobwebProjectileEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        var everything = this.getAnimationProcessor().getBone("everything");

        everything.setRotY(-1.5708F);
    }
}