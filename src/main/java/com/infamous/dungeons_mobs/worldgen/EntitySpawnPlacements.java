package com.infamous.dungeons_mobs.worldgen;

import com.infamous.dungeons_mobs.entities.creepers.IcyCreeperEntity;
import com.infamous.dungeons_mobs.entities.piglin.FungusThrowerEntity;
import com.infamous.dungeons_mobs.entities.piglin.ZombifiedFungusThrowerEntity;
import com.infamous.dungeons_mobs.entities.undead.FrozenZombieEntity;
import com.infamous.dungeons_mobs.entities.undead.JungleZombieEntity;
import com.infamous.dungeons_mobs.entities.undead.MossySkeletonEntity;
import com.infamous.dungeons_mobs.entities.undead.WraithEntity;
import com.infamous.dungeons_mobs.interfaces.IAquaticMob;
import com.infamous.dungeons_mobs.mod.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public class EntitySpawnPlacements {

    public static SpawnPlacementType IN_WATER_ON_GROUND = (iWorldReader, blockPos, entityType) -> {
		BlockState blockstate = iWorldReader.getBlockState(blockPos);
		FluidState fluidstate = iWorldReader.getFluidState(blockPos);
		BlockPos above = blockPos.above();
		BlockPos below = blockPos.below();
		BlockState stateAtPos = iWorldReader.getBlockState(blockPos);
		boolean inWater = fluidstate.is(FluidTags.WATER) && iWorldReader.getFluidState(below).is(FluidTags.WATER) && !iWorldReader.getBlockState(above).isRedstoneConductor(iWorldReader, above);
		if (!stateAtPos.isValidSpawn(iWorldReader, below, entityType)) {
			return false;
		} else {
			boolean validEmptySpawn = isValidEmptySpawnBlockNoFluidCheck(iWorldReader, blockPos, blockstate, entityType) && isValidEmptySpawnBlockNoFluidCheck(iWorldReader, above, iWorldReader.getBlockState(above), entityType);
			return validEmptySpawn && inWater;
		}
	};

    public static SpawnPlacementType ON_GROUND_ALLOW_LEAVES = (levelReader, blockPos, entityType) -> {
		BlockState blockstate = levelReader.getBlockState(blockPos);
		FluidState fluidstate = levelReader.getFluidState(blockPos);
		BlockPos above = blockPos.above();
		BlockPos below = blockPos.below();
		BlockState stateBelow = levelReader.getBlockState(below);
		if (!stateBelow.isValidSpawn(levelReader, below, entityType) && !(stateBelow.is(BlockTags.LEAVES))) {
			return false;
		} else {
			return NaturalSpawner.isValidEmptySpawnBlock(levelReader, blockPos, blockstate, fluidstate, entityType)
					&& NaturalSpawner.isValidEmptySpawnBlock(levelReader, above, levelReader.getBlockState(above), levelReader.getFluidState(above), entityType);
		}
	};

    public static void createPlacementTypes() {}

    public static boolean isValidEmptySpawnBlockNoFluidCheck(BlockGetter blockReader, BlockPos blockPos, BlockState blockState, EntityType<?> entityType) {
        if (blockState.isCollisionShapeFullBlock(blockReader, blockPos)) {
            return false;
        } else if (blockState.isSignalSource()) {
            return false;
        } else if (blockState.is(BlockTags.PREVENT_MOB_SPAWNING_INSIDE)) {
            return false;
        } else {
            return !entityType.isBlockDangerous(blockState);
        }
    }

    static RegisterSpawnPlacementsEvent event;

    public static void initSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        EntitySpawnPlacements.event = event;
        register(ModEntityTypes.WRAITH.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        register(ModEntityTypes.FROZEN_ZOMBIE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FrozenZombieEntity::canFrozenZombieSpawn);
        register(ModEntityTypes.ICY_CREEPER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                IcyCreeperEntity::canIcyCreeperSpawn);
        register(ModEntityTypes.JUNGLE_ZOMBIE.get(),
                ON_GROUND_ALLOW_LEAVES,
                Heightmap.Types.MOTION_BLOCKING,
                JungleZombieEntity::canJungleZombieSpawn);
        register(ModEntityTypes.MOSSY_SKELETON.get(),
                ON_GROUND_ALLOW_LEAVES,
                Heightmap.Types.MOTION_BLOCKING,
                MossySkeletonEntity::canMossySkeletonSpawn);


        register(ModEntityTypes.SKELETON_VANGUARD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        register(ModEntityTypes.NECROMANCER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);

        // Illager
        register(ModEntityTypes.ROYAL_GUARD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::canIllagerSpawn);
        register(ModEntityTypes.MOUNTAINEER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::canIllagerSpawn);
        register(ModEntityTypes.GEOMANCER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::canIllagerSpawn);
        register(ModEntityTypes.ICEOLOGER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::canIllagerSpawn);
        register(ModEntityTypes.ILLUSIONER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::canIllagerSpawn);
        register(ModEntityTypes.ILLUSIONER_CLONE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::canIllagerSpawn);
        register(ModEntityTypes.WINDCALLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::canIllagerSpawn);

        register(ModEntityTypes.SQUALL_GOLEM.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::canRaiderSpawn);

        register(ModEntityTypes.REDSTONE_GOLEM.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        register(ModEntityTypes.REDSTONE_CUBE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        register(ModEntityTypes.CONJURED_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules);

        // Jungle
        register(ModEntityTypes.WHISPERER.get(),
                ON_GROUND_ALLOW_LEAVES,
                Heightmap.Types.MOTION_BLOCKING,
                EntitySpawnPlacements::canJungleMobSpawn);
        register(ModEntityTypes.LEAPLEAF.get(),
                ON_GROUND_ALLOW_LEAVES,
                Heightmap.Types.MOTION_BLOCKING,
                EntitySpawnPlacements::canJungleMobSpawn);
        // COMMENTED OUT BECAUSE THEY SHOULDN'T SPAWN WITHOUT DUNGEONS WORLD
        /*EntitySpawnPlacementRegistry.register(ModEntityTypes.QUICK_GROWING_VINE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING,
                AbstractVineEntity::canVineSpawnInLight);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.POISON_QUILL_VINE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING,
                VineEntity::canVineSpawnInLight);*/

        // Piglin
        register(ModEntityTypes.FUNGUS_THROWER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FungusThrowerEntity::checkFungusThrowerSpawnRules);
        register(ModEntityTypes.ZOMBIFIED_FUNGUS_THROWER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ZombifiedFungusThrowerEntity::checkZombifiedFungusThrowerSpawnRules);

        // Blaze
        register(ModEntityTypes.WILDFIRE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkAnyLightMonsterSpawnRules);


        // Ocean
        register(ModEntityTypes.WAVEWHISPERER.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::checkAquaticMobSpawnRules);
        // COMMENTED OUT BECAUSE THEY SHOULDN'T SPAWN WITHOUT DUNGEONS WORLD
        /*EntitySpawnPlacementRegistry.register(ModEntityTypes.QUICK_GROWING_KELP.get(),
                IN_WATER_ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::checkAquaticMobSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.POISON_ANEMONE.get(),
                IN_WATER_ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::checkAquaticMobSpawnRules);*/
        register(ModEntityTypes.DROWNED_NECROMANCER.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::checkAquaticMobSpawnRules);
        register(ModEntityTypes.SUNKEN_SKELETON.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntitySpawnPlacements::checkAquaticMobSpawnRules);

        //Enderlings
        register(ModEntityTypes.BLASTLING.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        register(ModEntityTypes.WATCHLING.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        register(ModEntityTypes.SNARELING.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        register(ModEntityTypes.ENDERSENT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);

    }

    private static <T extends Mob> void register(EntityType<T> entityType, SpawnPlacementType spawnPlacementType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<T> predicate) {
        event.register(entityType, spawnPlacementType, heightmapType, predicate, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

    public static boolean checkAquaticMobSpawnRules(EntityType<? extends Mob> type, ServerLevelAccessor pServerLevel, MobSpawnType pMobSpawnType, BlockPos pPos, RandomSource pRandom) {
        if (!pServerLevel.getFluidState(pPos.below()).is(FluidTags.WATER)) {
            return false;
        } else {
            Holder<Biome> holder = pServerLevel.getBiome(pPos);
            boolean flag = pServerLevel.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(pServerLevel, pPos, pRandom) && (pMobSpawnType == MobSpawnType.SPAWNER || pServerLevel.getFluidState(pPos).is(FluidTags.WATER));
            if (holder.is(BiomeTags.MORE_FREQUENT_DROWNED_SPAWNS)) {
                return pRandom.nextInt(15) == 0 && flag;
            } else {
                return pRandom.nextInt(40) == 0 && IAquaticMob.isDeepEnoughToSpawn(pServerLevel, pPos) && flag;
            }
        }
    }

    public static boolean canJungleMobSpawn(EntityType<? extends Monster> entityType, ServerLevelAccessor world, MobSpawnType spawnReason, BlockPos blockPos, RandomSource rand) {
        return Monster.checkMonsterSpawnRules(entityType, world, spawnReason, blockPos, rand) && world.getSeaLevel() <= blockPos.getY();
    }

    public static boolean canIllagerSpawn(EntityType<? extends AbstractIllager> entityType, ServerLevelAccessor world, MobSpawnType spawnReason, BlockPos blockPos, RandomSource rand) {
        return Monster.checkMonsterSpawnRules(entityType, world, spawnReason, blockPos, rand);
    }

    public static boolean canRaiderSpawn(EntityType<? extends Raider> entityType, ServerLevelAccessor world, MobSpawnType spawnReason, BlockPos blockPos, RandomSource rand) {
        return Monster.checkMonsterSpawnRules(entityType, world, spawnReason, blockPos, rand);
    }

    public static boolean canSeeSkyLight(ServerLevelAccessor world, BlockPos blockPos) {
        return world.getBrightness(LightLayer.SKY, blockPos) > 4;
    }

}
