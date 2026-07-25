package com.infamous.dungeons_mobs.client.renderer.jungle;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.jungle.PoisonQuillVineModel;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.api.distmarker.Dist;
import net.neoforged.neoforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PoisonQuillVineRenderer extends AbstractVineRenderer<PoisonQuillVineModel> {
    @SuppressWarnings("unchecked")
    public PoisonQuillVineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PoisonQuillVineModel());
        this.addRenderLayer(new GeoEyeLayer(this, GeneralUtil.mobsLoc( "textures/entity/jungle/poison_quill_vine_glow.png")));
    }
}