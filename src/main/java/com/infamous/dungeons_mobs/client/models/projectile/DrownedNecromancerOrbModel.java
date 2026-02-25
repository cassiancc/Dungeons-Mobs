package com.infamous.dungeons_mobs.client.models.projectile;


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.projectiles.DrownedNecromancerOrbEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class DrownedNecromancerOrbModel extends GeoModel<DrownedNecromancerOrbEntity> {

    @Override
    public ResourceLocation getAnimationResource(DrownedNecromancerOrbEntity entity) {
        return GeneralUtil.mobsLoc( "animations/drowned_necromancer_orb.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(DrownedNecromancerOrbEntity entity) {
        return GeneralUtil.mobsLoc( "geo/drowned_necromancer_orb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DrownedNecromancerOrbEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/projectile/drowned_necromancer_orb_" + entity.textureChange % 2 + ".png");
    }

    @Override
    public RenderType getRenderType(DrownedNecromancerOrbEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(DrownedNecromancerOrbEntity entity, long uniqueID, AnimationState<DrownedNecromancerOrbEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        var everything = this.getAnimationProcessor().getBone("everything");

        everything.setRotY(-1.5708F);
    }
}