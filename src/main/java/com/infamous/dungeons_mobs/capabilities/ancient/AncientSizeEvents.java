package com.infamous.dungeons_mobs.capabilities.ancient;


import com.infamous.dungeons_libraries.DungeonsLibraries;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@EventBusSubscriber(modid = DungeonsLibraries.MODID)
public class AncientSizeEvents {
    @SubscribeEvent
    public static void onEntityEventSize(EntityEvent.Size event) {
        Entity entity = event.getEntity();

        Ancient cap = AncientHelper.getAncientCapability(entity);
        if (cap.isAncient()) {
            float totalWidth = event.getNewSize().width * 1.2F;
            float totalHeight = event.getNewSize().height * 1.2F;
//            event.setNewEyeHeight(event.getNewEyeHeight() * 1.2F);
            event.setNewSize(EntityDimensions.fixed(totalWidth, totalHeight));
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRenderLivingEventPre(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
        final LivingEntity entity = event.getEntity();
        Ancient cap = AncientHelper.getAncientCapability(entity);
        if (cap.isAncient()) {
            event.getPoseStack().pushPose();
            event.getPoseStack().scale(1.1F, 1.1F, 1.1F);

        }

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRenderLivingEventPost(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> event) {
        final LivingEntity entity = event.getEntity();
        Ancient cap = AncientHelper.getAncientCapability(entity);
        if (cap.isAncient()) {
            event.getPoseStack().popPose();
        }

    }
}