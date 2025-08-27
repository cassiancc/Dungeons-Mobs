package com.infamous.dungeons_mobs.client.models.jungle;

import com.infamous.dungeons_mobs.entities.jungle.AbstractVineEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.molang.MolangParser;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;

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

    }

    @Override
    public void setMolangQueries(GeoAnimatable animatable, double currentTick) {
        super.setMolangQueries(animatable, currentTick);

        MolangParser parser = GeckoLibCache.getInstance().parser;
        AbstractVineEntity vine = (AbstractVineEntity) animatable;
        parser.setValue("query.vine_length", vine::getLengthInSegments);
    }
}