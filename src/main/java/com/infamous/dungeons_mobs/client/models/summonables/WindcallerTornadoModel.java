package com.infamous.dungeons_mobs.client.models.summonables;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.summonables.WindcallerTornadoEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WindcallerTornadoModel extends GeoModel<WindcallerTornadoEntity> {

    @Override
    public ResourceLocation getAnimationResource(WindcallerTornadoEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/windcaller_tornado.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(WindcallerTornadoEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/windcaller_tornado.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WindcallerTornadoEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/windcaller_tornado.png");
    }

    @Override
    public RenderType getRenderType(WindcallerTornadoEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(WindcallerTornadoEntity entity, long uniqueID, AnimationState<WindcallerTornadoEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var everything = this.getAnimationProcessor().getBone("everything");

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (((WindcallerTornadoEntity) entity).isBlast() && (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0)) {
            //everything.setRotationX(((Entity)entity).getXRot());
            //everything.setRotationY(((Entity)entity).yRot);
        }
    }
}