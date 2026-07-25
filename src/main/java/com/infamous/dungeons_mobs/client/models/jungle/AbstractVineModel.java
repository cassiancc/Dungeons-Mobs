package com.infamous.dungeons_mobs.client.models.jungle;

import com.infamous.dungeons_mobs.entities.jungle.AbstractVineEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.model.GeoModel;

public abstract class AbstractVineModel extends GeoModel<AbstractVineEntity> {

    @Override
    public void setCustomAnimations(AbstractVineEntity entity, long uniqueID, AnimationState<AbstractVineEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var everything = this.getAnimationProcessor().getBone("everything");

        everything.setHidden(entity.tickCount <= entity.getAnimationTransitionTime());

        for (int i = 1; i < 26; i++) {
            var part = this.getAnimationProcessor().getBone("part" + i);
            int partsToShow = 26 - entity.getLengthInSegments();
            if (part != null) {
                part.setHidden(i < partsToShow);
            }
        }
        MolangParser.INSTANCE.setValue("query.vine_length", entity::getLengthInSegments);
    }

    @Override
    public RenderType getRenderType(AbstractVineEntity animatable, ResourceLocation resourceLocation) {
        return RenderType.entityTranslucent(resourceLocation);
    }
}