package com.infamous.dungeons_mobs.interfaces;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.GeoBone;

@OnlyIn(Dist.CLIENT)
public interface IHasGeoArm {

    GeoBone getArm(HumanoidArm p_191216_1_);

    void translateToHand(HumanoidArm p_225599_1_, PoseStack p_225599_2_);

    void translateAndRotate(GeoBone bone, PoseStack p_228307_1_);
}
