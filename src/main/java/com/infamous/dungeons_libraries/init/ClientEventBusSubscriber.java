package com.infamous.dungeons_libraries.init;

import com.infamous.dungeons_libraries.client.renderer.SoulOrbRenderer;
import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearRenderer;
import com.infamous.dungeons_libraries.entities.ModEntityTypes;
import com.infamous.dungeons_libraries.items.gearconfig.ArmorGear;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static net.neoforged.neoforge.api.distmarker.Dist.CLIENT;

@EventBusSubscriber(modid = MODID, value = CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.SOUL_ORB.get(), SoulOrbRenderer::new);
    }

}
