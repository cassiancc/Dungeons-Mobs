package com.infamous.dungeons_mobs;

import com.infamous.dungeons_libraries.client.ClientProxy;
import com.infamous.dungeons_libraries.network.CommonProxy;
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
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.eventbus.api.IEventBus;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

import static com.infamous.dungeons_mobs.mod.ModEntityTypes.SPAWN_EGGS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("dungeons_mobs")
public class DungeonsMobs {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "dungeons_mobs";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final CreativeModeTab DUNGEONS_MOBS = CreativeModeTab.builder().title(Component.translatable("itemGroup.dungeonsMobs")).icon(()-> SPAWN_EGGS.getEntries().iterator().next().get().getDefaultInstance()).displayItems(((pParameters, pOutput) -> {
        for (Supplier<Item> entry : SPAWN_EGGS.getEntries()) {
            pOutput.accept(entry.get());
        }
    })).build();
    public static final CreativeModeTab DUNGEONS_MOBS_ITEMS = CreativeModeTab.builder().title(Component.translatable("itemGroup.dungeonsMobsItems")).icon(()-> ModItems.ITEMS.getEntries().iterator().next().get().getDefaultInstance()).displayItems(((pParameters, pOutput) -> {
        for (Supplier<Item> entry : ModItems.ITEMS.getEntries()) {
            pOutput.accept(entry.get());
        }
    })).build();

    public static CommonProxy PROXY;

    public DungeonsMobs() {
    	GeckoLib.initialize();
        // Register the setup method for modloading
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
        context.registerConfig(ModConfig.Type.COMMON, DungeonsMobsConfig.COMMON_SPEC, "dungeons-mobs-common.toml");
        final IEventBus modEventBus = context.getModEventBus();
        context.getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        context.getModEventBus().addListener(this::doClientStuff);
        context.getModEventBus().addListener(this::onLoadComplete);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(this);

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
        if (EnchantWithMobCompat.isLoaded()) {
            ModMobEnchants.MOB_ENCHANTS_DEFERRED.register(modEventBus);
            EnchantWithMobCompat.initMobEnchants(modEventBus);
        }
        ModDataSerializers.DATA_SERIALIZERS.register(modEventBus);
        ModStructureModifiers.STRUCTURE_MODIFIER_SERIALIZERS.register(modEventBus);
        PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

        //ANCIENT_DATA.subscribeAsSyncable(CHANNEL, AncientDatas::toPacket);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(EntitySpawnPlacements::createPlacementTypes);
        event.enqueueWork(EntitySpawnPlacements::initSpawnPlacements);
        event.enqueueWork(RaidEntries::initWaveMemberEntries);
        event.enqueueWork(SensorMapModifier::replaceSensorMaps);
        event.enqueueWork(NetworkHandler::init);
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
        // ITEM MODEL PROPERTIES
        event.enqueueWork(ModItemModelProperties::registerProperties);
    }

    private void onLoadComplete(final FMLLoadCompleteEvent event) {
        if (DungeonsMobsConfig.COMMON.ENABLE_STRONGER_HUSKS.get()) {
            EntityType.HUSK.dimensions = EntityDimensions.scalable(0.6F * 1.2F, 1.95F * 1.2F);
        }
    }
}
