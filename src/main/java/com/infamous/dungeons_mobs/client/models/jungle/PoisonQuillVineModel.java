package com.infamous.dungeons_mobs.client.models.jungle;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.jungle.AbstractVineEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;

public class PoisonQuillVineModel extends AbstractVineModel {
    @Override
    public ResourceLocation getAnimationResource(AbstractVineEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/poison_quill_vine.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(AbstractVineEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/poison_quill_vine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AbstractVineEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/jungle/poison_quill_vine.png");
    }

    @Override
    public void setCustomAnimations(AbstractVineEntity entity, long uniqueID, AnimationState<AbstractVineEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("head");
        var headRotator = this.getAnimationProcessor().getBone("headRotator");

        // FIXME
        var extraData = customPredicate.getExtraData().get(0);
        if (extraData.headPitch != 0 || extraData.netHeadYaw != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch * ((float) Math.PI / 180F)));

            headRotator.setRotY(headRotator.getRotY() + (extraData.netHeadYaw * ((float) Math.PI / 180F)));
        }
    }
}