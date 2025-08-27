package com.infamous.dungeons_mobs.client.models.jungle;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.jungle.LeapleafEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.molang.MolangParser;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;

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
    public void setMolangQueries(GeoAnimatable animatable, double currentTick) {
        super.setMolangQueries(animatable, currentTick);

        MolangParser parser = GeckoLibCache.getInstance().parser;
        LivingEntity livingEntity = (LivingEntity) animatable;
        Vec3 velocity = livingEntity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        parser.setValue("query.ground_speed", () -> groundSpeed * 17.5);
    }
}

