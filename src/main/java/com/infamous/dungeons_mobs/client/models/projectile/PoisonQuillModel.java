package com.infamous.dungeons_mobs.client.models.projectile;


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.projectiles.PoisonQuillEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class PoisonQuillModel extends GeoModel<PoisonQuillEntity> {

    @Override
    public ResourceLocation getAnimationResource(PoisonQuillEntity entity) {
        return GeneralUtil.mobsLoc( "animations/poison_quill.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(PoisonQuillEntity entity) {
        return GeneralUtil.mobsLoc( "geo/poison_quill.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PoisonQuillEntity entity) {
        return GeneralUtil.mobsLoc( entity.isKelp() ? "textures/entity/projectile/water_poison_quill.png" : "textures/entity/projectile/poison_quill.png");
    }

    @Override
    public RenderType getRenderType(PoisonQuillEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(PoisonQuillEntity entity, long uniqueID, AnimationState<PoisonQuillEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        var everything = this.getAnimationProcessor().getBone("everything");

        everything.setRotY(-1.5708F);
    }
}