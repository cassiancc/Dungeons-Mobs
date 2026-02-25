package com.infamous.dungeons_mobs.client.renderer.jungle;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.jungle.QuickGrowingVineModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.entities.jungle.AbstractVineEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class QuickGrowingVineRenderer extends AbstractVineRenderer<QuickGrowingVineModel> {
    @SuppressWarnings("unchecked")
    public QuickGrowingVineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new QuickGrowingVineModel());
        this.addRenderLayer(new GeoEyeLayer(this, GeneralUtil.mobsLoc( "textures/entity/jungle/quick_growing_vine_glow.png")));
    }
}