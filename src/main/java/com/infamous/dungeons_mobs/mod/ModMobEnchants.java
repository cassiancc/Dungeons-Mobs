package com.infamous.dungeons_mobs.mod;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import baguchi.enchantwithmob.registry.MobEnchants;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.mobenchants.*;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.checkerframework.checker.units.qual.A;

import java.util.function.Supplier;

public class ModMobEnchants {
    public static final DeferredRegister<MobEnchant> MOB_ENCHANTS_DEFERRED = DeferredRegister.create(MobEnchants.MOB_ENCHANT.getRegistryName(), DungeonsMobs.MODID);

    public static final int ANVIL_COST = 1;

    public static final Supplier<RushMobEnchant> RUSH = MOB_ENCHANTS_DEFERRED.register("rush", () -> new RushMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.COMMON, 3, ANVIL_COST)));
    public static final Supplier<RegenerationMobEnchant> REGENERATION = MOB_ENCHANTS_DEFERRED.register("regeneration", () -> new RegenerationMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.COMMON, 3, ANVIL_COST)));
    public static final Supplier<RadianceMobEnchant> RADIANCE = MOB_ENCHANTS_DEFERRED.register("radiance", () -> new RadianceMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.RARE, 3, ANVIL_COST)));

    public static final Supplier<GravityPulseMobEnchant> GRAVITY_PULSE = MOB_ENCHANTS_DEFERRED.register("gravity_pulse", () -> new GravityPulseMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.RARE, 3,  ANVIL_COST)));
    public static final Supplier<BurningMobEnchant> BURNING = MOB_ENCHANTS_DEFERRED.register("burning", () -> new BurningMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.UNCOMMON, 3, ANVIL_COST)));
    public static final Supplier<ChillingMobEnchant> CHILLING = MOB_ENCHANTS_DEFERRED.register("chilling", () -> new ChillingMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.UNCOMMON, 3, ANVIL_COST)));
    public static final Supplier<HealsAlliesMobEnchant> HEALS_ALLIES = MOB_ENCHANTS_DEFERRED.register("heals_allies", () -> new HealsAlliesMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.RARE, 3, ANVIL_COST)));
    public static final Supplier<DeflectMobEnchant> DEFLECT = MOB_ENCHANTS_DEFERRED.register("deflect", () -> new DeflectMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.UNCOMMON, 1, ANVIL_COST)));
    public static final Supplier<EchoMobEnchant> ECHO = MOB_ENCHANTS_DEFERRED.register("echo", () -> new EchoMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.VERY_RARE, 3, ANVIL_COST)));
}
