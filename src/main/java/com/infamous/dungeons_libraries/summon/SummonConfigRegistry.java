package com.infamous.dungeons_libraries.summon;

import com.infamous.dungeons_libraries.data.util.CodecJsonDataManager;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;

@EventBusSubscriber(modid = MODID)
public class SummonConfigRegistry {
    public static final ResourceLocation SUMMON_RESOURCELOCATION = GeneralUtil.loc(MODID, "summon");

    public static final CodecJsonDataManager<SummonConfig> SUMMON_CONFIGS = new CodecJsonDataManager<>("summon", SummonConfig.CODEC);


    public static SummonConfig getConfig(ResourceLocation resourceLocation) {
        return SUMMON_CONFIGS.getData().getOrDefault(resourceLocation, SummonConfig.DEFAULT);
    }

    public static boolean gearConfigExists(ResourceLocation resourceLocation) {
        return SUMMON_CONFIGS.getData().containsKey(resourceLocation);
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(SUMMON_CONFIGS);
    }
}