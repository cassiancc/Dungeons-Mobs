package com.infamous.dungeons_mobs.tags;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class EntityTags {
    public static final TagKey<EntityType<?>> DONT_SHIELD_AGAINST = tag("dont_shield_against");

    public static final TagKey<EntityType<?>> CONVERTS_IN_WATER = tag("converts_in_water");

    public static final TagKey<EntityType<?>> PLANT_MOBS = tag("plant_mobs");

    public static final TagKey<EntityType<?>> PIGLINS = tag("piglins");

    private static TagKey<EntityType<?>> tag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, GeneralUtil.mobsLoc( name));
    }

    public static void register() {
        // NOOP
    }
}
