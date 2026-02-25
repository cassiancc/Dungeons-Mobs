package com.infamous.dungeons_libraries.items;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.infamous.dungeons_libraries.utils.GeneralUtil.*;

public class ItemTagWrappers {

    public static final TagKey<Item> CURIOS_ARTIFACTS = tag(loc("curios", "artifact"));
    public static final TagKey<Item> ARTIFACT_REPAIR_ITEMS = tag(librariesLoc("artifact_repair_items"));

    private static TagKey<Item> tag(String name) {
        return TagKey.create(Registries.ITEM, librariesLoc(name));
    }

    private static TagKey<Item> tag(ResourceLocation resourceLocation) {
        return TagKey.create(Registries.ITEM, resourceLocation);
    }

    public static void init() {
    }
}
