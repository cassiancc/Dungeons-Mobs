package com.infamous.dungeons_mobs.compat;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.mobenchants.*;
import net.neoforged.neoforge.eventbus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModProcessEvent;

@EventBusSubscriber(modid = DungeonsMobs.MODID)
public class EnchantWithMobCompat {
    public static final String ENCHANTWITHMOB_MOD_ID = "enchantwithmob";
    private static boolean IS_LOADED = false;

    @SubscribeEvent
    public static void onInterMod(InterModProcessEvent event) {
        if (ModList.get().isLoaded(ENCHANTWITHMOB_MOD_ID)) {
            IS_LOADED = true;
        }
    }

    public static boolean isLoaded() {
        return IS_LOADED;
    }

    public static void initMobEnchants(IEventBus modEventBus) {
        modEventBus.register(BurningMobEnchant.class);
        modEventBus.register(ChillingMobEnchant.class);
        modEventBus.register(DeflectMobEnchant.class);
        modEventBus.register(EchoMobEnchant.class);
        modEventBus.register(GravityPulseMobEnchant.class);
        modEventBus.register(HealsAlliesMobEnchant.class);
        modEventBus.register(RadianceMobEnchant.class);
        modEventBus.register(RegenerationMobEnchant.class);
        modEventBus.register(RushMobEnchant.class);

    }
}
