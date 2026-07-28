package com.infamous.dungeons_libraries.capabilities;

import com.infamous.dungeons_libraries.capabilities.artifact.ArtifactUsage;
import com.infamous.dungeons_libraries.capabilities.elite.EliteMob;
import com.infamous.dungeons_libraries.capabilities.minionmaster.Leader;
import com.infamous.dungeons_libraries.capabilities.minionmaster.Follower;
import com.infamous.dungeons_libraries.capabilities.playerrewards.PlayerRewards;
import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCaster;
import com.infamous.dungeons_libraries.capabilities.timers.Timers;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.CapabilityManager;
import net.neoforged.neoforge.common.capabilities.CapabilityToken;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;

public class ModCapabilities {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);
    public static final Capability<Timers> TIMERS_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<Follower> FOLLOWER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<Leader> LEADER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<SoulCaster> SOUL_CASTER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<PlayerRewards> PLAYER_REWARDS_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Supplier<AttachmentType<ArtifactUsage>> ARTIFACT_USAGE_CAPABILITY = ATTACHMENT_TYPES.register(
            "artifact_usage", () -> AttachmentType.builder(ArtifactUsage::new).build());
    public static final Supplier<AttachmentType<EliteMob>> ELITE_MOB_CAPABILITY = ATTACHMENT_TYPES.register(
            "elite_mob", () -> AttachmentType.builder(()->new EliteMob(false, false, null)).serialize(EliteMob.CODEC).build());

}
