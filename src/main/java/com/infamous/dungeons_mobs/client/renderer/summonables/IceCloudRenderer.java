package com.infamous.dungeons_mobs.client.renderer.summonables;

import com.infamous.dungeons_mobs.client.models.summonables.IceCloudModel;
import com.infamous.dungeons_mobs.entities.summonables.IceCloudEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LightLayer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class IceCloudRenderer extends GeoEntityRenderer<IceCloudEntity> {
    public IceCloudRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new IceCloudModel());
    }

    @Override
    protected int getBlockLightLevel(IceCloudEntity p_114496_, BlockPos p_114497_) {
        return p_114496_.level().getBrightness(LightLayer.BLOCK, p_114497_) > 10
                ? p_114496_.level().getBrightness(LightLayer.BLOCK, p_114497_)
                : 5;
    }
}