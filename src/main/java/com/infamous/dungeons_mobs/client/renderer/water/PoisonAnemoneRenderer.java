package com.infamous.dungeons_mobs.client.renderer.water;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.models.jungle.PoisonQuillVineModel;
import com.infamous.dungeons_mobs.client.renderer.jungle.AbstractVineRenderer;
import com.infamous.dungeons_mobs.client.renderer.layers.GeoEyeLayer;
import com.infamous.dungeons_mobs.entities.jungle.AbstractVineEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;

@OnlyIn(Dist.CLIENT)
public class PoisonAnemoneRenderer extends AbstractVineRenderer<PoisonQuillVineModel> {

    private static final ResourceLocation POISON_ANEMONE_TEXTURE = GeneralUtil.loc(MODID, "textures/entity/ocean/poison_anemone.png");

    @SuppressWarnings("unchecked")
    public PoisonAnemoneRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PoisonQuillVineModel());
        this.addRenderLayer(new GeoEyeLayer(this, GeneralUtil.mobsLoc( "textures/entity/ocean/quick_growing_kelp_glow.png")));
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractVineEntity entity) {
        return POISON_ANEMONE_TEXTURE;
    }
}