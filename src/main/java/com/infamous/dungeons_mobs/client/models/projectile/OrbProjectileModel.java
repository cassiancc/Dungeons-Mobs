package com.infamous.dungeons_mobs.client.models.projectile;


import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.projectiles.NecromancerOrbEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class OrbProjectileModel extends GeoModel<NecromancerOrbEntity> {

    private final boolean renderTrail;

    public OrbProjectileModel(boolean renderTrail) {
        this.renderTrail = renderTrail;
    }

    @Override
    public ResourceLocation getAnimationResource(NecromancerOrbEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/necromancer_orb.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NecromancerOrbEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/necromancer_orb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NecromancerOrbEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/projectile/orb_projectile_" + entity.textureChange % 3 + ".png");
    }

    @Override
    public RenderType getRenderType(NecromancerOrbEntity animatable, ResourceLocation textureLocation) {
//        return RenderType.entityTranslucent(getTextureLocation(animatable));
        return RenderType.eyes(textureLocation);
    }

    @Override
    public void setCustomAnimations(NecromancerOrbEntity entity, long uniqueID, AnimationState<NecromancerOrbEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        var everything = this.getAnimationProcessor().getBone("everything");
        if(!renderTrail) {
            var trail = this.getAnimationProcessor().getBone("trail1");
            trail.setHidden(true);
        }

        everything.setRotY(-1.5708F);
    }
}