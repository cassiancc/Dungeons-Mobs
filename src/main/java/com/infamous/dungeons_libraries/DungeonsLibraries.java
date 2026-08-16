package com.infamous.dungeons_libraries;

import com.infamous.dungeons_libraries.attribute.AttributeRegistry;
import com.infamous.dungeons_libraries.capabilities.ModCapabilities;
import com.infamous.dungeons_libraries.client.artifactBar.ArtifactsBarRender;
import com.infamous.dungeons_libraries.config.DungeonsLibrariesConfig;
import com.infamous.dungeons_libraries.integration.curios.CuriosIntegration;
import com.infamous.dungeons_libraries.integration.curios.client.CuriosClientIntegration;
import com.infamous.dungeons_libraries.integration.curios.client.CuriosKeyBindings;
import com.infamous.dungeons_libraries.items.ItemTagWrappers;
import com.infamous.dungeons_libraries.items.RangedItemModelProperties;
import com.infamous.dungeons_libraries.items.artifacts.ArtifactEvents;
import com.infamous.dungeons_libraries.items.artifacts.config.ArtifactGearConfigRegistry;
import com.infamous.dungeons_libraries.items.gearconfig.ArmorGearConfigRegistry;
import com.infamous.dungeons_libraries.items.gearconfig.BowGearConfigRegistry;
import com.infamous.dungeons_libraries.items.gearconfig.CrossbowGearConfigRegistry;
import com.infamous.dungeons_libraries.items.gearconfig.MeleeGearConfigRegistry;
import com.infamous.dungeons_libraries.items.materials.armor.DungeonsArmorMaterials;
import com.infamous.dungeons_libraries.items.materials.weapon.WeaponMaterials;
import com.infamous.dungeons_libraries.network.NetworkHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static com.infamous.dungeons_libraries.capabilities.ModCapabilities.ATTACHMENT_TYPES;
import static com.infamous.dungeons_libraries.entities.ModEntityTypes.ENTITY_TYPES;
import static com.infamous.dungeons_libraries.items.artifacts.config.ArtifactGearConfigRegistry.ARTIFACT_GEAR_CONFIGS;
import static com.infamous.dungeons_libraries.items.gearconfig.ArmorGearConfigRegistry.ARMOR_GEAR_CONFIGS;
import static com.infamous.dungeons_libraries.items.gearconfig.BowGearConfigRegistry.BOW_GEAR_CONFIGS;
import static com.infamous.dungeons_libraries.items.gearconfig.CrossbowGearConfigRegistry.CROSSBOW_GEAR_CONFIGS;
import static com.infamous.dungeons_libraries.items.gearconfig.MeleeGearConfigRegistry.MELEE_GEAR_CONFIGS;
import static com.infamous.dungeons_libraries.items.materials.armor.DungeonsArmorMaterials.ARMOR_MATERIALS;
import static com.infamous.dungeons_libraries.items.materials.weapon.WeaponMaterials.WEAPON_MATERIALS;


@EventBusSubscriber(modid = MODID)
@Mod("dungeons_libraries")
public class DungeonsLibraries {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "dungeons_libraries";

    public DungeonsLibraries(IEventBus modEventBus, ModContainer container) {
        // Register the setup method for modloading
        container.registerConfig(ModConfig.Type.COMMON, DungeonsLibrariesConfig.COMMON_SPEC);
        modEventBus.addListener(DungeonsLibraries::setup);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(DungeonsLibraries::doClientStuff);
        ItemTagWrappers.init();
        AttributeRegistry.ATTRIBUTES.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        DungeonsArmorMaterials.setupVanillaMaterials();
        WeaponMaterials.setupVanillaMaterials();

        ARMOR_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, ArmorGearConfigRegistry::toPacket);
        MELEE_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, MeleeGearConfigRegistry::toPacket);
        BOW_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, BowGearConfigRegistry::toPacket);
        CROSSBOW_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, CrossbowGearConfigRegistry::toPacket);
        WEAPON_MATERIALS.subscribeAsSyncable(NetworkHandler.INSTANCE, WeaponMaterials::toPacket);
        ARMOR_MATERIALS.subscribeAsSyncable(NetworkHandler.INSTANCE, DungeonsArmorMaterials::toPacket);
        ARTIFACT_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, ArtifactGearConfigRegistry::toPacket);

        if (ModList.get().isLoaded("curios")) {
            NeoForge.EVENT_BUS.register(ArtifactEvents.class);
            NeoForge.EVENT_BUS.register(CuriosKeyBindings.class);
            modEventBus.register(CuriosIntegration.class);
            modEventBus.register(CuriosClientIntegration.class);
            modEventBus.register(ArtifactsBarRender.class);
        }
    }

    @SubscribeEvent
    private static void setup(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("0");
        NetworkHandler.init(registrar);
    }

    @SubscribeEvent
    private static void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(RangedItemModelProperties::init);
    }

}
