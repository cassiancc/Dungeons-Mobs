package com.infamous.dungeons_mobs.compat;

import com.infamous.dungeons_mobs.DungeonsMobs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModProcessEvent;

@EventBusSubscriber(modid = DungeonsMobs.MODID)
public class EnderlingsCompat {
    public static final String ENDERLINGS_MOD_ID = "enderlings";
    private static boolean IS_LOADED = false;

    @SubscribeEvent
    public static void onInterMod(InterModProcessEvent event) {
        if (ModList.get().isLoaded(ENDERLINGS_MOD_ID)) {
            IS_LOADED = true;
        }
    }


    public static boolean isLoaded() {
        return IS_LOADED;
    }

}
