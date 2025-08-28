package com.infamous.dungeons_mobs.client.models.jungle;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.jungle.LeapleafEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.model.GeoModel;

public class LeapleafModel extends GeoModel<LeapleafEntity> {

    @Override
    public ResourceLocation getAnimationResource(LeapleafEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/leapleaf.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(LeapleafEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/leapleaf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LeapleafEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/jungle/leapleaf.png");
    }

    @Override
    public void setCustomAnimations(LeapleafEntity animatable, long instanceId, AnimationState<LeapleafEntity> animationState) {
        Vec3 velocity = animatable.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MolangParser.INSTANCE.setValue("query.ground_speed", () -> groundSpeed * 17.5);
    }
}

