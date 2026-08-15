package com.infamous.dungeons_libraries.items.materials.armor;

import com.infamous.dungeons_libraries.data.util.DefaultsCodecJsonDataManager;
import com.infamous.dungeons_libraries.network.materials.ArmorMaterialSyncPacket;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.infamous.dungeons_libraries.items.materials.armor.ArmorMaterialBaseType.UNKNOWN;
import static net.minecraft.world.item.ArmorMaterials.*;

public class DungeonsArmorMaterials {

    public static final DefaultsCodecJsonDataManager<DungeonsArmorMaterial> ARMOR_MATERIALS = new DefaultsCodecJsonDataManager<>("material/armor", DungeonsArmorMaterial.CODEC);
    public static final Map<Holder<ArmorMaterial>, ArmorMaterialBaseType> baseArmorMaterials = new HashMap<>();

    public static void setupVanillaMaterials() {
        addDefaultArmorMaterial(LEATHER, ArmorMaterialBaseType.LEATHER, GeneralUtil.parse("minecraft:leather"));
        addDefaultArmorMaterial(CHAIN, ArmorMaterialBaseType.METAL, GeneralUtil.parse("minecraft:chainmail"));
        addDefaultArmorMaterial(IRON, ArmorMaterialBaseType.METAL, GeneralUtil.parse("minecraft:iron"));
        addDefaultArmorMaterial(GOLD, ArmorMaterialBaseType.METAL, GeneralUtil.parse("minecraft:gold"));
        addDefaultArmorMaterial(DIAMOND, ArmorMaterialBaseType.GEM, GeneralUtil.parse("minecraft:diamond"));
        addDefaultArmorMaterial(TURTLE, ArmorMaterialBaseType.LEATHER, GeneralUtil.parse("minecraft:turtle"));
        addDefaultArmorMaterial(NETHERITE, ArmorMaterialBaseType.METAL, GeneralUtil.parse("minecraft:netherite"));
    }

    public static void addDefaultArmorMaterial(Holder<ArmorMaterial> material, ArmorMaterialBaseType baseType, ResourceLocation resourceLocation) {
        ARMOR_MATERIALS.addDefault(resourceLocation, DungeonsArmorMaterial.of(material));
        baseArmorMaterials.put(material, baseType);
    }

    public static DungeonsArmorMaterial getArmorMaterial(ResourceLocation resourceLocation) {
        return ARMOR_MATERIALS.getData().getOrDefault(resourceLocation, DungeonsArmorMaterial.of(IRON));
    }

    public static boolean ArmorMaterialExists(ResourceLocation resourceLocation) {
        return ARMOR_MATERIALS.getData().containsKey(resourceLocation);
    }

    public static Collection<ResourceLocation> armorMaterialsKeys() {
        return ARMOR_MATERIALS.getData().keySet();
    }

    public static Collection<DungeonsArmorMaterial> getArmorMaterials(ArmorMaterialBaseType baseType) {
        return ARMOR_MATERIALS.getData().values().stream().filter(iArmorMaterial -> {
            if (iArmorMaterial instanceof DungeonsArmorMaterial) {
                return iArmorMaterial.getBaseType() == baseType;
            } else if (baseArmorMaterials.containsKey(iArmorMaterial)) {
                return baseArmorMaterials.get(iArmorMaterial) == baseType;
            } else {
                return UNKNOWN == baseType;
            }
        }).collect(Collectors.toList());
    }

    public static ArmorMaterialSyncPacket toPacket(Map<ResourceLocation, DungeonsArmorMaterial> map) {
        return new ArmorMaterialSyncPacket(map);
    }
}