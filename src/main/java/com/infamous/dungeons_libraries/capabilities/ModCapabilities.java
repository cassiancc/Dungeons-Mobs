package com.infamous.dungeons_libraries.capabilities;

import com.infamous.dungeons_libraries.capabilities.artifact.ArtifactUsage;
import com.infamous.dungeons_libraries.capabilities.elite.EliteMob;
import com.infamous.dungeons_libraries.capabilities.minionmaster.Leader;
import com.infamous.dungeons_libraries.capabilities.minionmaster.Follower;
import com.infamous.dungeons_libraries.capabilities.playerrewards.PlayerRewards;
import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCaster;
import com.infamous.dungeons_libraries.capabilities.timers.Timers;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;

public class ModCapabilities {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);
    public static final Supplier<AttachmentType<Timers>> TIMERS_CAPABILITY = ATTACHMENT_TYPES.register(
            "timers", () -> AttachmentType.builder(Timers::new).build());
    public static final Supplier<AttachmentType<Follower>> FOLLOWER_CAPABILITY = ATTACHMENT_TYPES.register(
            "follower", () -> AttachmentType.builder(Follower::new).build());
    public static final Supplier<AttachmentType<Leader>> LEADER_CAPABILITY = ATTACHMENT_TYPES.register(
            "leader", () -> AttachmentType.builder(Leader::new).build());
    public static final Supplier<AttachmentType<SoulCaster>> SOUL_CASTER_CAPABILITY = ATTACHMENT_TYPES.register(
            "soul_caster", () -> AttachmentType.builder(SoulCaster::new).build());
    public static final Supplier<AttachmentType<PlayerRewards>> PLAYER_REWARDS_CAPABILITY = ATTACHMENT_TYPES.register(
            "player_rewards", () -> AttachmentType.builder(PlayerRewards::new).build());
    public static final Supplier<AttachmentType<ArtifactUsage>> ARTIFACT_USAGE_CAPABILITY = ATTACHMENT_TYPES.register(
            "artifact_usage", () -> AttachmentType.builder(ArtifactUsage::new).build());
    public static final Supplier<AttachmentType<EliteMob>> ELITE_MOB_CAPABILITY = ATTACHMENT_TYPES.register(
            "elite_mob", () -> AttachmentType.builder(()->new EliteMob(false, false, null)).serialize(EliteMob.CODEC).build());

}
