package com.infamous.dungeons_libraries.utils;

import com.infamous.dungeons_mobs.DungeonsMobs;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;

public class GeneralUtil {
    public static ResourceLocation mcLoc(String path) {
        return new ResourceLocation(path);
    }

    public static ResourceLocation librariesLoc(String path) {
        return loc(MODID, path);
    }

    public static ResourceLocation mobsLoc(String path) {
        return loc(DungeonsMobs.MODID, path);
    }

	@SuppressWarnings("all")
    public static ResourceLocation loc(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

	public static ResourceLocation loc(String path) {
		return GeneralUtil.parse(path);
	}

	public static ResourceLocation gearLoc(String path) {
		return loc("dungeons_gear", path);
	}

	public static ResourceLocation parse(String string) {
		return mcLoc(string);
	}

    public static ResourceLocation forgeLoc(String path) {
        return loc("forge", path);
    }
}
