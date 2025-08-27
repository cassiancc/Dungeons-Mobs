package com.infamous.dungeons_mobs.mod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;

public class ModDamageSources {

    public static final ResourceKey<DamageType> BLASTLING = register("blastling");
    public static final ResourceKey<DamageType> ICE_CHUNK = register("ice_chunk");
    public static final ResourceKey<DamageType> ECHO = register("echo");
    public static final ResourceKey<DamageType> SUMMONED_TRIDENT_STORM = register("summoned_trident_storm");
    public static final ResourceKey<DamageType> TRIDENT_STORM = register("trident_storm");
    public static final ResourceKey<DamageType> POISON_QUILL = register("poison_quill");

    private static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID, name));
    }

}
