package com.infamous.dungeons_mobs.client.particle;

import com.infamous.dungeons_mobs.DungeonsMobs;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.function.Supplier;

public class ModParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, DungeonsMobs.MODID);

    public static final Supplier<SimpleParticleType> SNOWFLAKE = PARTICLES.register("snowflake", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> REDSTONE_SPARK = PARTICLES.register("redstone_spark", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> WIND = PARTICLES.register("wind", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> DUST = PARTICLES.register("dust", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> NECROMANCY = PARTICLES.register("necromancy", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> CORRUPTED_DUST = PARTICLES.register("corrupted_dust", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> CORRUPTED_MAGIC = PARTICLES.register("corrupted_magic", () -> new SimpleParticleType(true));
}
