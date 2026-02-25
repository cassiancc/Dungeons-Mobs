package com.infamous.dungeons_mobs.client.models.illager;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.entities.illagers.EnchanterEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class EnchanterModel extends GeoModel<EnchanterEntity> {

    @Override
    public ResourceLocation getAnimationResource(EnchanterEntity entity) {
        return GeneralUtil.mobsLoc( "animations/enchanter.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(EnchanterEntity entity) {
        return GeneralUtil.mobsLoc( "geo/enchanter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EnchanterEntity entity) {
        //ChorusGormandizerEntity entityIn = (ChorusGormandizerEntity) entity;
        return GeneralUtil.mobsLoc( "textures/entity/enchanter/enchanter.png");
    }

    @Override
    public RenderType getRenderType(EnchanterEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public void setCustomAnimations(EnchanterEntity entity, long uniqueID, AnimationState<EnchanterEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        EnchanterEntity entityIn = (EnchanterEntity) entity;

        var head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        //if (extraData.headPitch != 0 || extraData.netHeadYaw != 0) {
        //head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        //head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        //}
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

