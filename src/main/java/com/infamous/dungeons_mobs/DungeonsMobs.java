package com.infamous.dungeons_mobs;

import com.infamous.dungeons_libraries.client.ClientProxy;
import com.infamous.dungeons_libraries.network.CommonProxy;
import com.infamous.dungeons_mobs.capabilities.ModCapabilities;
import com.infamous.dungeons_mobs.client.ModItemModelProperties;
import com.infamous.dungeons_mobs.client.particle.ModParticleTypes;
import com.infamous.dungeons_mobs.compat.EnchantWithMobCompat;
import com.infamous.dungeons_mobs.config.DungeonsMobsConfig;
import com.infamous.dungeons_mobs.mod.*;
import com.infamous.dungeons_mobs.network.NetworkHandler;
import com.infamous.dungeons_mobs.network.datasync.ModDataSerializers;
import com.infamous.dungeons_mobs.tags.BiomeTags;
import com.infamous.dungeons_mobs.tags.EntityTags;
import com.infamous.dungeons_mobs.worldgen.EntitySpawnPlacements;
import com.infamous.dungeons_mobs.worldgen.RaidEntries;
import com.infamous.dungeons_mobs.worldgen.SensorMapModifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;
import static com.infamous.dungeons_mobs.mod.ModEntityTypes.SPAWN_EGGS;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@EventBusSubscriber(modid = MODID)
@Mod("dungeons_mobs")
public class DungeonsMobs {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "dungeons_mobs";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final CreativeModeTab DUNGEONS_MOBS = CreativeModeTab.builder().title(Component.translatable("itemGroup.dungeonsMobs")).icon(()-> SPAWN_EGGS.getEntries().iterator().next().get().getDefaultInstance()).displayItems(((pParameters, pOutput) -> {
        for (DeferredHolder<Item, ? extends Item> entry : SPAWN_EGGS.getEntries()) {
            pOutput.accept(entry.get());
        }
    })).build();
    public static final CreativeModeTab DUNGEONS_MOBS_ITEMS = CreativeModeTab.builder().title(Component.translatable("itemGroup.dungeonsMobsItems")).icon(()-> ModItems.ITEMS.getEntries().iterator().next().get().getDefaultInstance()).displayItems(((pParameters, pOutput) -> {
        for (DeferredHolder<Item, ? extends Item> entry : ModItems.ITEMS.getEntries()) {
            pOutput.accept(entry.get());
        }
    })).build();

    public static CommonProxy PROXY;

    public DungeonsMobs(IEventBus modEventBus, ModContainer container) {
//    	GeckoLib.initialize();
        // Register the setup method for modloading
        container.registerConfig(ModConfig.Type.COMMON, DungeonsMobsConfig.COMMON_SPEC, "dungeons-mobs-common.toml");
        modEventBus.addListener(DungeonsMobs::setup);
        modEventBus.addListener(DungeonsMobs::setupNetworking);
        modEventBus.addListener(DungeonsMobs::setupSpawnPlacements);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(DungeonsMobs::doClientStuff);
        modEventBus.addListener(DungeonsMobs::onLoadComplete);

        // Registering custom tags
        EntityTags.register();
        BiomeTags.register();

        CREATIVE_TABS.register("mobs", ()-> DUNGEONS_MOBS);
        CREATIVE_TABS.register("items", ()->DUNGEONS_MOBS_ITEMS);
        CREATIVE_TABS.register(modEventBus);


        ModSoundEvents.SOUNDS.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        SPAWN_EGGS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModRecipes.RECIPES.register(modEventBus);
        ModParticleTypes.PARTICLES.register(modEventBus);
        ModCapabilities.ATTACHMENT_TYPES.register(modEventBus);
        if (EnchantWithMobCompat.isLoaded()) {
            ModMobEnchants.MOB_ENCHANTS_DEFERRED.register(modEventBus);
            EnchantWithMobCompat.initMobEnchants(modEventBus);
        }
        ModDataSerializers.DATA_SERIALIZERS.register(modEventBus);
        ModStructureModifiers.STRUCTURE_MODIFIER_SERIALIZERS.register(modEventBus);
        PROXY = FMLEnvironment.dist.isClient() ? new ClientProxy() : new CommonProxy();

        //ANCIENT_DATA.subscribeAsSyncable(CHANNEL, AncientDatas::toPacket);
    }

    @SubscribeEvent
    protected static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(EntitySpawnPlacements::createPlacementTypes);
        event.enqueueWork(RaidEntries::initWaveMemberEntries);
        event.enqueueWork(SensorMapModifier::replaceSensorMaps);
    }

    @SubscribeEvent
    protected static void setupNetworking(final RegisterPayloadHandlersEvent event) {
        NetworkHandler.init(event.registrar("1"));
    }

    @SubscribeEvent
    protected static void setupSpawnPlacements(final RegisterSpawnPlacementsEvent event) {
        EntitySpawnPlacements.initSpawnPlacements(event);
    }


    @SubscribeEvent
    protected static void doClientStuff(final FMLClientSetupEvent event) {
        // ITEM MODEL PROPERTIES
        event.enqueueWork(ModItemModelProperties::registerProperties);
    }

    @SubscribeEvent
    protected static void onLoadComplete(final FMLLoadCompleteEvent event) {
        if (DungeonsMobsConfig.COMMON.ENABLE_STRONGER_HUSKS.get()) {
            EntityType.HUSK.dimensions = EntityDimensions.scalable(0.6F * 1.2F, 1.95F * 1.2F);
        }
    }
}
