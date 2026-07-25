package com.infamous.dungeons_mobs.mod;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.function.Supplier;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT,
            DungeonsMobs.MODID);

    public static final Supplier<SoundEvent> WRAITH_IDLE = SOUNDS.register("entity.wraith.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wraith.idle")));
    public static final Supplier<SoundEvent> WRAITH_HURT = SOUNDS.register("entity.wraith.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wraith.hurt")));
    public static final Supplier<SoundEvent> WRAITH_DEATH = SOUNDS.register("entity.wraith.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wraith.death")));
    public static final Supplier<SoundEvent> WRAITH_ATTACK = SOUNDS.register("entity.wraith.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wraith.attack")));
    public static final Supplier<SoundEvent> WRAITH_FIRE = SOUNDS.register("entity.wraith.fire", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wraith.fire")));
    public static final Supplier<SoundEvent> WRAITH_FLY = SOUNDS.register("entity.wraith.fly", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wraith.fly")));
    public static final Supplier<SoundEvent> WRAITH_TELEPORT = SOUNDS.register("entity.wraith.teleport", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wraith.teleport")));

    public static final Supplier<SoundEvent> ENCHANTER_IDLE = SOUNDS.register("entity.enchanter.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.enchanter.idle")));
    public static final Supplier<SoundEvent> ENCHANTER_HURT = SOUNDS.register("entity.enchanter.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.enchanter.hurt")));
    public static final Supplier<SoundEvent> ENCHANTER_DEATH = SOUNDS.register("entity.enchanter.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.enchanter.death")));
    public static final Supplier<SoundEvent> ENCHANTER_PRE_ATTACK = SOUNDS.register("entity.enchanter.pre_attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.enchanter.pre_attack")));
    public static final Supplier<SoundEvent> ENCHANTER_ATTACK = SOUNDS.register("entity.enchanter.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.enchanter.attack")));
    public static final Supplier<SoundEvent> ENCHANTER_SPELL = SOUNDS.register("entity.enchanter.spell", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.enchanter.spell")));
    public static final Supplier<SoundEvent> ENCHANTER_BEAM = SOUNDS.register("entity.enchanter.beam", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.enchanter.beam")));
    public static final Supplier<SoundEvent> ENCHANTER_BEAM_LOOP = SOUNDS.register("entity.enchanter.beam_loop", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.enchanter.beam_loop")));

    public static final Supplier<SoundEvent> GEOMANCER_IDLE = SOUNDS.register("entity.geomancer.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.geomancer.idle")));
    public static final Supplier<SoundEvent> GEOMANCER_HURT = SOUNDS.register("entity.geomancer.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.geomancer.hurt")));
    public static final Supplier<SoundEvent> GEOMANCER_DEATH = SOUNDS.register("entity.geomancer.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.geomancer.death")));
    public static final Supplier<SoundEvent> GEOMANCER_PRE_ATTACK = SOUNDS.register("entity.geomancer.pre_attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.geomancer.pre_attack")));
    public static final Supplier<SoundEvent> GEOMANCER_ATTACK = SOUNDS.register("entity.geomancer.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.geomancer.attack")));
    public static final Supplier<SoundEvent> GEOMANCER_WALL_SPAWN = SOUNDS.register("entity.geomancer.wall_spawn", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.geomancer.wall_spawn")));
    public static final Supplier<SoundEvent> GEOMANCER_WALL_DESPAWN = SOUNDS.register("entity.geomancer.wall_despawn", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.geomancer.wall_despawn")));
    public static final Supplier<SoundEvent> GEOMANCER_BOMB_SPAWN = SOUNDS.register("entity.geomancer.bomb_spawn", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.geomancer.bomb_spawn")));

    public static final Supplier<SoundEvent> SQUALL_GOLEM_IDLE = SOUNDS.register("entity.squall_golem.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.squall_golem.idle")));
    public static final Supplier<SoundEvent> SQUALL_GOLEM_HURT = SOUNDS.register("entity.squall_golem.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.squall_golem.hurt")));
    public static final Supplier<SoundEvent> SQUALL_GOLEM_DEATH = SOUNDS.register("entity.squall_golem.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.squall_golem.death")));
    public static final Supplier<SoundEvent> SQUALL_GOLEM_ATTACK = SOUNDS.register("entity.squall_golem.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.squall_golem.attack")));
    public static final Supplier<SoundEvent> SQUALL_GOLEM_OPEN = SOUNDS.register("entity.squall_golem.on", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.squall_golem.on")));
    public static final Supplier<SoundEvent> SQUALL_GOLEM_OFF = SOUNDS.register("entity.squall_golem.off", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.squall_golem.off")));
    public static final Supplier<SoundEvent> SQUALL_GOLEM_WALK = SOUNDS.register("entity.squall_golem.walk", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.squall_golem.walk")));

    public static final Supplier<SoundEvent> ROYAL_GUARD_ATTACK = SOUNDS.register("entity.royal_guard.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.royal_guard.attack")));
    public static final Supplier<SoundEvent> ROYAL_GUARD_STEP = SOUNDS.register("entity.royal_guard.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.royal_guard.step")));

    public static final Supplier<SoundEvent> SNARELING_STEP = SOUNDS.register("entity.snareling.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.snareling.step")));
    public static final Supplier<SoundEvent> SNARELING_IDLE = SOUNDS.register("entity.snareling.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.snareling.idle")));
    public static final Supplier<SoundEvent> SNARELING_HURT = SOUNDS.register("entity.snareling.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.snareling.hurt")));
    public static final Supplier<SoundEvent> SNARELING_DEATH = SOUNDS.register("entity.snareling.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.snareling.death")));
    public static final Supplier<SoundEvent> SNARELING_ATTACK = SOUNDS.register("entity.snareling.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.snareling.attack")));
    public static final Supplier<SoundEvent> SNARELING_PREPARE_SHOOT = SOUNDS.register("entity.snareling.prepare_shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.snareling.prepare_shoot")));
    public static final Supplier<SoundEvent> SNARELING_SHOOT = SOUNDS.register("entity.snareling.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.snareling.shoot")));
    public static final Supplier<SoundEvent> SNARELING_GLOB_LAND = SOUNDS.register("entity.snareling.glob_land", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.snareling.glob_land")));

    public static final Supplier<SoundEvent> BLASTLING_IDLE = SOUNDS.register("entity.blastling.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.blastling.idle")));
    public static final Supplier<SoundEvent> BLASTLING_HURT = SOUNDS.register("entity.blastling.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.blastling.hurt")));
    public static final Supplier<SoundEvent> BLASTLING_DEATH = SOUNDS.register("entity.blastling.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.blastling.death")));
    public static final Supplier<SoundEvent> BLASTLING_STEP = SOUNDS.register("entity.blastling.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.blastling.step")));
    public static final Supplier<SoundEvent> BLASTLING_SHOOT = SOUNDS.register("entity.blastling.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.blastling.shoot")));
    public static final Supplier<SoundEvent> BLASTLING_BULLET_LAND = SOUNDS.register("entity.blastling.bullet_land", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.blastling.bullet_land")));

    public static final Supplier<SoundEvent> WATCHLING_IDLE = SOUNDS.register("entity.watchling.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.watchling.idle")));
    public static final Supplier<SoundEvent> WATCHLING_HURT = SOUNDS.register("entity.watchling.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.watchling.hurt")));
    public static final Supplier<SoundEvent> WATCHLING_DEATH = SOUNDS.register("entity.watchling.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.watchling.death")));
    public static final Supplier<SoundEvent> WATCHLING_STEP = SOUNDS.register("entity.watchling.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.watchling.step")));
    public static final Supplier<SoundEvent> WATCHLING_ATTACK = SOUNDS.register("entity.watchling.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.watchling.attack")));

    public static final Supplier<SoundEvent> ENDERSENT_STEP = SOUNDS.register("entity.endersent.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.endersent.step")));
    public static final Supplier<SoundEvent> ENDERSENT_IDLE = SOUNDS.register("entity.endersent.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.endersent.idle")));
    public static final Supplier<SoundEvent> ENDERSENT_HURT = SOUNDS.register("entity.endersent.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.endersent.hurt")));
    public static final Supplier<SoundEvent> ENDERSENT_DEATH = SOUNDS.register("entity.endersent.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.endersent.death")));
    public static final Supplier<SoundEvent> ENDERSENT_ATTACK = SOUNDS.register("entity.endersent.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.endersent.attack")));
    public static final Supplier<SoundEvent> ENDERSENT_IDLE_SMASH = SOUNDS.register("entity.endersent.idle_smash", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.endersent.idle_smash")));
    public static final Supplier<SoundEvent> ENDERSENT_TELEPORT = SOUNDS.register("entity.endersent.teleport", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.endersent.teleport")));

    public static final Supplier<SoundEvent> ICEOLOGER_ATTACK = SOUNDS.register("entity.iceologer.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.iceologer.attack")));
    public static final Supplier<SoundEvent> ICEOLOGER_IDLE = SOUNDS.register("entity.iceologer.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.iceologer.idle")));
    public static final Supplier<SoundEvent> ICEOLOGER_HURT = SOUNDS.register("entity.iceologer.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.iceologer.hurt")));
    public static final Supplier<SoundEvent> ICEOLOGER_DEATH = SOUNDS.register("entity.iceologer.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.iceologer.death")));
    public static final Supplier<SoundEvent> ICE_CHUNK_IDLE_LOOP = SOUNDS.register("entity.ice_chunk.idle_loop", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.ice_chunk.idle_loop")));
    public static final Supplier<SoundEvent> ICE_CHUNK_SUMMONED = SOUNDS.register("entity.ice_chunk.summoned", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.ice_chunk.summoned")));
    public static final Supplier<SoundEvent> ICE_CHUNK_FALL = SOUNDS.register("entity.ice_chunk.fall", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.ice_chunk.fall")));
    public static final Supplier<SoundEvent> ICE_CHUNK_LAND = SOUNDS.register("entity.ice_chunk.land", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.ice_chunk.land")));

    public static final Supplier<SoundEvent> SKELETON_VANGUARD_ATTACK = SOUNDS.register("entity.skeleton_vanguard.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.skeleton_vanguard.attack")));
    public static final Supplier<SoundEvent> SKELETON_VANGUARD_IDLE = SOUNDS.register("entity.skeleton_vanguard.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.skeleton_vanguard.idle")));
    public static final Supplier<SoundEvent> SKELETON_VANGUARD_HURT = SOUNDS.register("entity.skeleton_vanguard.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.skeleton_vanguard.hurt")));
    public static final Supplier<SoundEvent> SKELETON_VANGUARD_DEATH = SOUNDS.register("entity.skeleton_vanguard.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.skeleton_vanguard.death")));
    public static final Supplier<SoundEvent> SKELETON_VANGUARD_STEP = SOUNDS.register("entity.skeleton_vanguard.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.skeleton_vanguard.step")));

    public static final Supplier<SoundEvent> ICY_CREEPER_EXPLODE = SOUNDS.register("entity.icy_creeper.explode", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.icy_creeper.explode")));

    public static final Supplier<SoundEvent> JUNGLE_ZOMBIE_IDLE = SOUNDS.register("entity.jungle_zombie.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.jungle_zombie.idle")));
    public static final Supplier<SoundEvent> JUNGLE_ZOMBIE_HURT = SOUNDS.register("entity.jungle_zombie.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.jungle_zombie.hurt")));
    public static final Supplier<SoundEvent> JUNGLE_ZOMBIE_DEATH = SOUNDS.register("entity.jungle_zombie.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.jungle_zombie.death")));
    public static final Supplier<SoundEvent> JUNGLE_ZOMBIE_STEP = SOUNDS.register("entity.jungle_zombie.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.jungle_zombie.step")));

    public static final Supplier<SoundEvent> FROZEN_ZOMBIE_IDLE = SOUNDS.register("entity.frozen_zombie.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.frozen_zombie.idle")));
    public static final Supplier<SoundEvent> FROZEN_ZOMBIE_HURT = SOUNDS.register("entity.frozen_zombie.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.frozen_zombie.hurt")));
    public static final Supplier<SoundEvent> FROZEN_ZOMBIE_DEATH = SOUNDS.register("entity.frozen_zombie.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.frozen_zombie.death")));
    public static final Supplier<SoundEvent> FROZEN_ZOMBIE_SNOWBALL_LAND = SOUNDS.register("entity.frozen_zombie.snowball_land", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.frozen_zombie.snowball_land")));

    public static final Supplier<SoundEvent> MOSSY_SKELETON_IDLE = SOUNDS.register("entity.mossy_skeleton.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.mossy_skeleton.idle")));
    public static final Supplier<SoundEvent> MOSSY_SKELETON_HURT = SOUNDS.register("entity.mossy_skeleton.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.mossy_skeleton.hurt")));
    public static final Supplier<SoundEvent> MOSSY_SKELETON_DEATH = SOUNDS.register("entity.mossy_skeleton.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.mossy_skeleton.death")));
    public static final Supplier<SoundEvent> MOSSY_SKELETON_SHOOT = SOUNDS.register("entity.mossy_skeleton.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.mossy_skeleton.shoot")));
    public static final Supplier<SoundEvent> MOSSY_SKELETON_STEP = SOUNDS.register("entity.mossy_skeleton.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.mossy_skeleton.step")));

    public static final Supplier<SoundEvent> NECROMANCER_IDLE = SOUNDS.register("entity.necromancer.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.idle")));
    public static final Supplier<SoundEvent> NECROMANCER_LAUGH = SOUNDS.register("entity.necromancer.laugh", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.laugh")));
    public static final Supplier<SoundEvent> NECROMANCER_HURT = SOUNDS.register("entity.necromancer.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.hurt")));
    public static final Supplier<SoundEvent> NECROMANCER_DEATH = SOUNDS.register("entity.necromancer.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.death")));
    public static final Supplier<SoundEvent> NECROMANCER_SHOOT = SOUNDS.register("entity.necromancer.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.shoot")));
    public static final Supplier<SoundEvent> NECROMANCER_ORB_IMPACT = SOUNDS.register("entity.necromancer.orb_impact", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.orb_impact")));
    public static final Supplier<SoundEvent> NECROMANCER_STEP = SOUNDS.register("entity.necromancer.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.step")));
    public static final Supplier<SoundEvent> NECROMANCER_PREPARE_SUMMON = SOUNDS.register("entity.necromancer.prepare_summon", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.prepare_summon")));
    public static final Supplier<SoundEvent> NECROMANCER_SUMMON = SOUNDS.register("entity.necromancer.summon", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.necromancer.summon")));

    public static final Supplier<SoundEvent> WINDCALLER_IDLE = SOUNDS.register("entity.windcaller.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.windcaller.idle")));
    public static final Supplier<SoundEvent> WINDCALLER_HURT = SOUNDS.register("entity.windcaller.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.windcaller.hurt")));
    public static final Supplier<SoundEvent> WINDCALLER_DEATH = SOUNDS.register("entity.windcaller.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.windcaller.death")));
    public static final Supplier<SoundEvent> WINDCALLER_LIFT_WIND = SOUNDS.register("entity.windcaller.lift_wind", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.windcaller.lift_wind")));
    public static final Supplier<SoundEvent> WINDCALLER_BLAST_WIND = SOUNDS.register("entity.windcaller.blast_wind", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.windcaller.blast_wind")));
    public static final Supplier<SoundEvent> WINDCALLER_LIFT_VOCAL = SOUNDS.register("entity.windcaller.lift_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.windcaller.lift_vocal")));
    public static final Supplier<SoundEvent> WINDCALLER_BLAST_VOCAL = SOUNDS.register("entity.windcaller.blast_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.windcaller.blast_vocal")));
    public static final Supplier<SoundEvent> WINDCALLER_FLY_LOOP = SOUNDS.register("entity.windcaller.fly_loop", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.windcaller.fly_loop")));

    public static final Supplier<SoundEvent> ILLUSIONER_DEATH = SOUNDS.register("entity.illusioner.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.illusioner.death")));
    public static final Supplier<SoundEvent> ILLUSIONER_CLONE_ARROW_HIT = SOUNDS.register("entity.illusioner.clone_arrow_hit", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.illusioner.clone_arrow_hit")));

    public static final Supplier<SoundEvent> MOUNTAINEER_IDLE = SOUNDS.register("entity.mountaineer.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.mountaineer.idle")));
    public static final Supplier<SoundEvent> MOUNTAINEER_HURT = SOUNDS.register("entity.mountaineer.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.mountaineer.hurt")));
    public static final Supplier<SoundEvent> MOUNTAINEER_DEATH = SOUNDS.register("entity.mountaineer.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.mountaineer.death")));

    public static final Supplier<SoundEvent> REDSTONE_GOLEM_IDLE = SOUNDS.register("entity.redstone_golem.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.redstone_golem.idle")));
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_HURT = SOUNDS.register("entity.redstone_golem.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.redstone_golem.hurt")));
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_DEATH = SOUNDS.register("entity.redstone_golem.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.redstone_golem.death")));
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_STEP = SOUNDS.register("entity.redstone_golem.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.redstone_golem.step")));
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_ATTACK = SOUNDS.register("entity.redstone_golem.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.redstone_golem.attack")));
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_SUMMON_MINES = SOUNDS.register("entity.redstone_golem.summon_mines", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.redstone_golem.summon_mines")));
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_IDLE_PULSE_LOOP = SOUNDS.register("entity.redstone_golem.idle_pulse_loop", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.redstone_golem.idle_pulse_loop")));
    public static final Supplier<SoundEvent> REDSTONE_GOLEM_SPARK = SOUNDS.register("entity.redstone_golem.spark", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.redstone_golem.spark")));

    public static final Supplier<SoundEvent> WHISPERER_IDLE_VOCAL = SOUNDS.register("entity.whisperer.idle_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.idle_vocal")));
    public static final Supplier<SoundEvent> WHISPERER_HURT_VOCAL = SOUNDS.register("entity.whisperer.hurt_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.hurt_vocal")));
    public static final Supplier<SoundEvent> WHISPERER_STEP_VOCAL = SOUNDS.register("entity.whisperer.step_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.step_vocal")));
    public static final Supplier<SoundEvent> WHISPERER_ATTACK_VOCAL = SOUNDS.register("entity.whisperer.attack_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.attack_vocal")));
    public static final Supplier<SoundEvent> WHISPERER_SUMMON_PQV_VOCAL = SOUNDS.register("entity.whisperer.summon_pqv_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.summon_pqv_vocal")));
    public static final Supplier<SoundEvent> WHISPERER_SUMMON_QGV_VOCAL = SOUNDS.register("entity.whisperer.summon_qgv_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.summon_qgv_vocal")));
    public static final Supplier<SoundEvent> WHISPERER_GRAPPLE_VOCAL = SOUNDS.register("entity.whisperer.grapple_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.grapple_vocal")));
    public static final Supplier<SoundEvent> WHISPERER_IDLE_FOLEY = SOUNDS.register("entity.whisperer.idle_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.idle_foley")));
    public static final Supplier<SoundEvent> WHISPERER_HURT_FOLEY = SOUNDS.register("entity.whisperer.hurt_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.hurt_foley")));
    public static final Supplier<SoundEvent> WHISPERER_STEP_FOLEY = SOUNDS.register("entity.whisperer.step_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.step_foley")));
    public static final Supplier<SoundEvent> WHISPERER_ATTACK_FOLEY = SOUNDS.register("entity.whisperer.attack_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.attack_foley")));
    public static final Supplier<SoundEvent> WHISPERER_SUMMON_PQV_FOLEY = SOUNDS.register("entity.whisperer.summon_pqv_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.summon_pqv_foley")));
    public static final Supplier<SoundEvent> WHISPERER_SUMMON_QGV_FOLEY = SOUNDS.register("entity.whisperer.summon_qgv_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.summon_qgv_foley")));
    public static final Supplier<SoundEvent> WHISPERER_GRAPPLE_FOLEY = SOUNDS.register("entity.whisperer.grapple_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.grapple_foley")));

    public static final Supplier<SoundEvent> WHISPERER_DEATH = SOUNDS.register("entity.whisperer.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.whisperer.death")));

    public static final Supplier<SoundEvent> LEAPLEAF_IDLE_VOCAL = SOUNDS.register("entity.leapleaf.idle_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.idle_vocal")));
    public static final Supplier<SoundEvent> LEAPLEAF_HURT_VOCAL = SOUNDS.register("entity.leapleaf.hurt_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.hurt_vocal")));
    public static final Supplier<SoundEvent> LEAPLEAF_STEP_VOCAL = SOUNDS.register("entity.leapleaf.step_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.step_vocal")));
    public static final Supplier<SoundEvent> LEAPLEAF_ATTACK_VOCAL = SOUNDS.register("entity.leapleaf.attack_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.attack_vocal")));
    public static final Supplier<SoundEvent> LEAPLEAF_PREPARE_LEAP_VOCAL = SOUNDS.register("entity.leapleaf.prepare_leap_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.prepare_leap_vocal")));
    public static final Supplier<SoundEvent> LEAPLEAF_LEAP_VOCAL = SOUNDS.register("entity.leapleaf.leap_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.leap_vocal")));
    public static final Supplier<SoundEvent> LEAPLEAF_REST_VOCAL = SOUNDS.register("entity.leapleaf.rest_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.rest_vocal")));
    public static final Supplier<SoundEvent> LEAPLEAF_IDLE_FOLEY = SOUNDS.register("entity.leapleaf.idle_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.idle_foley")));
    public static final Supplier<SoundEvent> LEAPLEAF_HURT_FOLEY = SOUNDS.register("entity.leapleaf.hurt_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.hurt_foley")));
    public static final Supplier<SoundEvent> LEAPLEAF_STEP_FOLEY = SOUNDS.register("entity.leapleaf.step_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.step_foley")));
    public static final Supplier<SoundEvent> LEAPLEAF_ATTACK_FOLEY = SOUNDS.register("entity.leapleaf.attack_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.attack_foley")));
    public static final Supplier<SoundEvent> LEAPLEAF_PREPARE_LEAP_FOLEY = SOUNDS.register("entity.leapleaf.prepare_leap_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.prepare_leap_foley")));
    public static final Supplier<SoundEvent> LEAPLEAF_LEAP_FOLEY = SOUNDS.register("entity.leapleaf.leap_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.leap_foley")));
    public static final Supplier<SoundEvent> LEAPLEAF_REST_FOLEY = SOUNDS.register("entity.leapleaf.rest_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.rest_foley")));
    public static final Supplier<SoundEvent> LEAPLEAF_DEATH = SOUNDS.register("entity.leapleaf.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.death")));
    public static final Supplier<SoundEvent> LEAPLEAF_LAND = SOUNDS.register("entity.leapleaf.land", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.leapleaf.land")));


    public static final Supplier<SoundEvent> QUICK_GROWING_VINE_BURST = SOUNDS.register("entity.quick_growing_vine.burst", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.quick_growing_vine.burst")));
    public static final Supplier<SoundEvent> QUICK_GROWING_VINE_BURST_DOWN = SOUNDS.register("entity.quick_growing_vine.burst_down", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.quick_growing_vine.burst_down")));
    public static final Supplier<SoundEvent> QUICK_GROWING_VINE_HURT = SOUNDS.register("entity.quick_growing_vine.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.quick_growing_vine.hurt")));
    public static final Supplier<SoundEvent> QUICK_GROWING_VINE_DEATH = SOUNDS.register("entity.quick_growing_vine.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.quick_growing_vine.death")));

    public static final Supplier<SoundEvent> POISON_QUILL_VINE_BURST = SOUNDS.register("entity.poison_quill_vine.burst", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_quill_vine.burst")));
    public static final Supplier<SoundEvent> POISON_QUILL_VINE_IDLE = SOUNDS.register("entity.poison_quill_vine.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_quill_vine.idle")));
    public static final Supplier<SoundEvent> POISON_QUILL_VINE_HURT_VOCAL = SOUNDS.register("entity.poison_quill_vine.hurt_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_quill_vine.hurt_vocal")));
    public static final Supplier<SoundEvent> POISON_QUILL_VINE_HURT_FOLEY = SOUNDS.register("entity.poison_quill_vine.hurt_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_quill_vine.hurt_foley")));
    public static final Supplier<SoundEvent> POISON_QUILL_VINE_DEATH = SOUNDS.register("entity.poison_quill_vine.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_quill_vine.death")));
    public static final Supplier<SoundEvent> POISON_QUILL_VINE_OPEN = SOUNDS.register("entity.poison_quill_vine.open", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_quill_vine.open")));
    public static final Supplier<SoundEvent> POISON_QUILL_VINE_CLOSE = SOUNDS.register("entity.poison_quill_vine.close", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_quill_vine.close")));
    public static final Supplier<SoundEvent> POISON_QUILL_VINE_SHOOT = SOUNDS.register("entity.poison_quill_vine.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_quill_vine.shoot")));

    public static final Supplier<SoundEvent> FUNGUS_THROWER_THROW = SOUNDS.register("entity.fungus_thrower.throw", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.fungus_thrower.throw")));
    public static final Supplier<SoundEvent> FUNGUS_THROWER_FUNGUS_LAND = SOUNDS.register("entity.fungus_thrower.fungus_land", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.fungus_thrower.fungus_land")));

    public static final Supplier<SoundEvent> WAVEWHISPERER_IDLE = SOUNDS.register("entity.wavewhisperer.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.idle")));
    public static final Supplier<SoundEvent> WAVEWHISPERER_HURT = SOUNDS.register("entity.wavewhisperer.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.hurt")));
    public static final Supplier<SoundEvent> WAVEWHISPERER_DEATH = SOUNDS.register("entity.wavewhisperer.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.death")));
    public static final Supplier<SoundEvent> WAVEWHISPERER_STEP = SOUNDS.register("entity.wavewhisperer.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.step")));
    public static final Supplier<SoundEvent> WAVEWHISPERER_ATTACK = SOUNDS.register("entity.wavewhisperer.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.attack")));
    public static final Supplier<SoundEvent> WAVEWHISPERER_SUMMON_PA_FOLEY = SOUNDS.register("entity.wavewhisperer.summon_pa_foley", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.summon_pa_foley")));
    public static final Supplier<SoundEvent> WAVEWHISPERER_SUMMON_PA_VOCAL = SOUNDS.register("entity.wavewhisperer.summon_pa_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.summon_pa_vocal")));
    public static final Supplier<SoundEvent> WAVEWHISPERER_SUMMON_QGK = SOUNDS.register("entity.wavewhisperer.summon_qgk", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.summon_qgk")));
    public static final Supplier<SoundEvent> WAVEWHISPERER_GRAPPLE = SOUNDS.register("entity.wavewhisperer.grapple", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wavewhisperer.grapple")));

    public static final Supplier<SoundEvent> QUICK_GROWING_KELP_BURST = SOUNDS.register("entity.quick_growing_kelp.burst", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.quick_growing_kelp.burst")));
    public static final Supplier<SoundEvent> QUICK_GROWING_KELP_BURST_DOWN = SOUNDS.register("entity.quick_growing_kelp.burst_down", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.quick_growing_kelp.burst_down")));
    public static final Supplier<SoundEvent> QUICK_GROWING_KELP_HURT = SOUNDS.register("entity.quick_growing_kelp.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.quick_growing_kelp.hurt")));
    public static final Supplier<SoundEvent> QUICK_GROWING_KELP_DEATH = SOUNDS.register("entity.quick_growing_kelp.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.quick_growing_kelp.death")));

    public static final Supplier<SoundEvent> POISON_ANEMONE_BURST = SOUNDS.register("entity.poison_anemone.burst", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_anemone.burst")));
    public static final Supplier<SoundEvent> POISON_ANEMONE_IDLE = SOUNDS.register("entity.poison_anemone.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_anemone.idle")));
    public static final Supplier<SoundEvent> POISON_ANEMONE_HURT = SOUNDS.register("entity.poison_anemone.hurt_vocal", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_anemone.hurt")));
    public static final Supplier<SoundEvent> POISON_ANEMONE_DEATH = SOUNDS.register("entity.poison_anemone.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_anemone.death")));
    public static final Supplier<SoundEvent> POISON_ANEMONE_CLOSE = SOUNDS.register("entity.poison_anemone.close", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_anemone.close")));
    public static final Supplier<SoundEvent> POISON_ANEMONE_SHOOT = SOUNDS.register("entity.poison_anemone.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.poison_anemone.shoot")));

    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_IDLE = SOUNDS.register("entity.drowned_necromancer.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.idle")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_HURT = SOUNDS.register("entity.drowned_necromancer.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.hurt")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_DEATH = SOUNDS.register("entity.drowned_necromancer.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.death")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_SWIM = SOUNDS.register("entity.drowned_necromancer.swim", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.swim")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_ATTACK = SOUNDS.register("entity.drowned_necromancer.attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.attack")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_STRONG_ATTACK = SOUNDS.register("entity.drowned_necromancer.strong_attack", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.strong_attack")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_SHOOT = SOUNDS.register("entity.drowned_necromancer.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.shoot")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_SUMMON = SOUNDS.register("entity.drowned_necromancer.summon", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.summon")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_STEAM_MISSILE = SOUNDS.register("entity.drowned_necromancer.steam_missile", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.steam_missile")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_STEAM_MISSILE_IMPACT = SOUNDS.register("entity.drowned_necromancer.steam_missile_impact", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.steam_missile_impact")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_PREPARE_TRIDENT_STORM = SOUNDS.register("entity.drowned_necromancer.prepare_trident_storm", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.prepare_trident_storm")));
    public static final Supplier<SoundEvent> DROWNED_NECROMANCER_TRIDENT_STORM_HIT = SOUNDS.register("entity.drowned_necromancer.trident_storm_hit", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.drowned_necromancer.trident_storm_hit")));

    public static final Supplier<SoundEvent> SUNKEN_SKELETON_IDLE = SOUNDS.register("entity.sunken_skeleton.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.sunken_skeleton.idle")));
    public static final Supplier<SoundEvent> SUNKEN_SKELETON_HURT = SOUNDS.register("entity.sunken_skeleton.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.sunken_skeleton.hurt")));
    public static final Supplier<SoundEvent> SUNKEN_SKELETON_DEATH = SOUNDS.register("entity.sunken_skeleton.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.sunken_skeleton.death")));
    public static final Supplier<SoundEvent> SUNKEN_SKELETON_STEP = SOUNDS.register("entity.sunken_skeleton.step", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.sunken_skeleton.step")));
    public static final Supplier<SoundEvent> SUNKEN_SKELETON_SHOOT = SOUNDS.register("entity.sunken_skeleton.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.sunken_skeleton.shoot")));

    public static final Supplier<SoundEvent> WILDFIRE_IDLE = SOUNDS.register("entity.wildfire.idle", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.idle")));
    public static final Supplier<SoundEvent> WILDFIRE_IDLE_LOOP = SOUNDS.register("entity.wildfire.idle_loop", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.idle_loop")));
    public static final Supplier<SoundEvent> WILDFIRE_HURT = SOUNDS.register("entity.wildfire.hurt", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.hurt")));
    public static final Supplier<SoundEvent> WILDFIRE_DEATH = SOUNDS.register("entity.wildfire.death", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.death")));
    public static final Supplier<SoundEvent> WILDFIRE_MOVE = SOUNDS.register("entity.wildfire.move", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.move")));
    public static final Supplier<SoundEvent> WILDFIRE_SHOOT = SOUNDS.register("entity.wildfire.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.shoot")));
    public static final Supplier<SoundEvent> WILDFIRE_PROJECTILE_HIT = SOUNDS.register("entity.wildfire.projectile_hit", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.projectile_hit")));
    public static final Supplier<SoundEvent> WILDFIRE_SHOCKWAVE = SOUNDS.register("entity.wildfire.shockwave", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.shockwave")));
    public static final Supplier<SoundEvent> WILDFIRE_SHIELD_BREAK = SOUNDS.register("entity.wildfire.shield_break", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.wildfire.shield_break")));

    public static final Supplier<SoundEvent> SPIDER_PREPARE_SHOOT = SOUNDS.register("entity.spider.prepare_shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.spider.prepare_shoot")));
    public static final Supplier<SoundEvent> SPIDER_SHOOT = SOUNDS.register("entity.spider.shoot", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.spider.shoot")));
    public static final Supplier<SoundEvent> SPIDER_WEB_IMPACT = SOUNDS.register("entity.spider.web_impact", () -> SoundEvent.createVariableRangeEvent(GeneralUtil.mobsLoc( "entity.spider.web_impact")));
}
