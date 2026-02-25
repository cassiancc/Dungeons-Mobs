package com.infamous.dungeons_mobs.client.models.summonables;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.illagers.GeomancerEntity;
import com.infamous.dungeons_mobs.entities.summonables.ConstructEntity;
import com.infamous.dungeons_mobs.entities.summonables.GeomancerBombEntity;
import com.infamous.dungeons_mobs.entities.summonables.GeomancerWallEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class GeomancerConstructModel extends GeoModel {

    @Override
    public ResourceLocation getAnimationResource(GeoAnimatable entity) {
        return GeneralUtil.mobsLoc( "animations/geomancer_pillar.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(GeoAnimatable entity) {
        return GeneralUtil.mobsLoc( "geo/geomancer_pillar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoAnimatable entity) {
        //ChorusGormandizerEntity entityIn = (ChorusGormandizerEntity) entity;
        return entity instanceof GeomancerBombEntity ? GeneralUtil.mobsLoc( "textures/entity/constructs/geomancer_bomb.png") : GeneralUtil.mobsLoc( "textures/entity/constructs/geomancer_wall.png");
    }

    @Override
    public RenderType getRenderType(GeoAnimatable animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(GeoAnimatable entity, long uniqueID, AnimationState customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

//        ConstructEntity entityIn = (ConstructEntity) entity;

    }

	/*public IBone getArm(HandSide p_191216_1_) {
		return this.getAnimationProcessor().getBone("rightArm");
	}


	public void translateToHand(HandSide p_225599_1_, MatrixStack p_225599_2_) {
		this.translateAndRotate(this.getAnimationProcessor().getBone("body"), p_225599_2_, 0.0, 0.0, 0.0);
		this.translateAndRotate(this.getAnimationProcessor().getBone("rightArm"), p_225599_2_, 0.0, 0.0, 0.0);
	}

	public void translateAndRotate(IBone bone, MatrixStack p_228307_1_, double moveX, double moveY, double moveZ) {

		if (bone.getRotationZ() != 0.0F) {
			p_228307_1_.mulPose(Vector3f.ZP.rotation(bone.getRotationZ()));
		}

		if (bone.getRotationY() != 0.0F) {
			p_228307_1_.mulPose(Vector3f.YP.rotation(bone.getRotationY()));
		}

		if (bone.getRotationX() != 0.0F) {
			p_228307_1_.mulPose(Vector3f.XP.rotation(bone.getRotationX()));
		}
		
		p_228307_1_.translate((double)((bone.getPivotX() + moveX) / 16.0F), (double)((bone.getPivotY() + moveY) / 16.0F), (double)((bone.getPivotZ() + moveZ) / 16.0F));
   
      
	}*/
}