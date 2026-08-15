package com.infamous.dungeons_libraries.attribute;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.function.Supplier;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;

public class AttributeRegistry {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, MODID);

    public static final Holder<Attribute> FOLLOWER_COST_LIMIT = ATTRIBUTES.register("follower_cost_limit", () -> new RangedAttribute(
            "attribute.name.generic.dungeons_libraries.follower_cost_limit",
            24.0D,
            0.0D,
            1024.0D)
            .setSyncable(true));

    public static final Holder<Attribute> SOUL_GATHERING = ATTRIBUTES.register("soul_gathering", () -> new RangedAttribute(
            "attribute.name.generic.dungeons_libraries.soul_gathering",
            0.0D,
            0.0D,
            1024.0D)
            .setSyncable(true));

    public static final Holder<Attribute> SOUL_CAP = ATTRIBUTES.register("soul_cap", () -> new RangedAttribute(
            "attribute.name.generic.dungeons_libraries.soul_cap",
            300.0D,
            0.0D,
            1024.0D)
            .setSyncable(true));

    public static final Holder<Attribute> LIFE_STEAL = ATTRIBUTES.register("life_steal", () -> new RangedAttribute(
            "attribute.name.generic.dungeons_libraries.life_steal",
            1.0D,
            0.0D,
            1024.0D)
            .setSyncable(true));

    public static final Holder<Attribute> RANGED_DAMAGE_MULTIPLIER = ATTRIBUTES.register("ranged_damage_multiplier", () -> new RangedAttribute(
            "attribute.name.generic.dungeons_libraries.ranged_damage_multiplier",
            1.0D,
            0.0D,
            1024.0D)
            .setSyncable(true));

    public static final Holder<Attribute> MAGIC_DAMAGE_MULTIPLIER = ATTRIBUTES.register("magic_damage_multiplier", () -> new RangedAttribute(
            "attribute.name.generic.dungeons_libraries.magic_damage_multiplier",
            0.0D,
            0.0D,
            1024.0D)
            .setSyncable(true));

    public static final Holder<Attribute> ARTIFACT_COOLDOWN_MULTIPLIER = ATTRIBUTES.register("artifact_cooldown_multiplier", () -> new RangedAttribute(
            "attribute.name.generic.dungeons_libraries.artifact_cooldown_multiplier",
            1.0D,
            0.0D,
            1024.0D)
            .setSyncable(true));
}
