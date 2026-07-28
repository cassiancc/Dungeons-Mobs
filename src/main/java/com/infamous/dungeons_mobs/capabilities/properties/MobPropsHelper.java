package com.infamous.dungeons_mobs.capabilities.properties;

import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.util.Lazy;

import static com.infamous.dungeons_mobs.capabilities.ModCapabilities.MOB_PROPS_CAPABILITY;

public class MobPropsHelper {

    public static MobProps getMobPropsCapability(Entity entity) {
		return entity.getData(MOB_PROPS_CAPABILITY);
    }
}
