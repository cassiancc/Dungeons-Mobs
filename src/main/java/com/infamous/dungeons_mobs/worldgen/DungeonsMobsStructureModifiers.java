package com.infamous.dungeons_mobs.worldgen;

import com.infamous.dungeons_mobs.mod.ModStructureModifiers;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.world.ModifiableStructureInfo;
import net.neoforged.neoforge.common.world.StructureModifier;
import net.neoforged.neoforge.common.world.StructureSettingsBuilder;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;

public class DungeonsMobsStructureModifiers {

    public record AddSpawnsStructureModifier(HolderSet<Structure> structures, List<MobSpawnSettings.SpawnerData> spawners) implements StructureModifier{
        @Override
        public void modify(Holder<Structure> structure, Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
            if(phase == Phase.ADD && this.structures.contains(structure)){
                StructureSettingsBuilder structureSettings = builder.getStructureSettings();
                for (MobSpawnSettings.SpawnerData spawner : this.spawners)
                {
                    EntityType<?> type = spawner.type;
                    structureSettings.getOrAddSpawnOverrides(type.getCategory()).addSpawn(spawner);
                }
            }
        }

        @Override
        public Codec<? extends StructureModifier> codec() {
            return ModStructureModifiers.ADD_SPAWNS_STRUCTURE_MODIFIER_TYPE.get();
        }
    }

    public record RemoveSpawnsStructureModifier(HolderSet<Structure> structures, HolderSet<EntityType<?>> entityTypes) implements StructureModifier{
        @Override
        public void modify(Holder<Structure> structure, Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
            if(phase == Phase.REMOVE && this.structures.contains(structure)){
                StructureSettingsBuilder structureSettings = builder.getStructureSettings();
                for(MobCategory mobCategory : MobCategory.values()){
                    StructureSettingsBuilder.StructureSpawnOverrideBuilder spawnOverrides = structureSettings.getSpawnOverrides(mobCategory);
                    if(spawnOverrides != null){
                        List<MobSpawnSettings.SpawnerData> spawns = spawnOverrides.getSpawns();
                        spawns.forEach(spawnerData -> {
                            if (this.entityTypes.contains(BuiltInRegistries.ENTITY_TYPE.getHolder(spawnerData.type).get())) {
                                spawnOverrides.removeSpawn(spawnerData);
                            }
                        });
                    }
                }
            }
        }

        @Override
        public Codec<? extends StructureModifier> codec() {
            return ModStructureModifiers.REMOVE_SPAWNS_STRUCTURE_MODIFIER_TYPE.get();
        }
    }

}
