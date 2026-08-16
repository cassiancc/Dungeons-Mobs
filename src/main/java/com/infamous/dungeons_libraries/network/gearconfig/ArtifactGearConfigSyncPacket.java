package com.infamous.dungeons_libraries.network.gearconfig;

import com.infamous.dungeons_libraries.items.artifacts.config.ArtifactGearConfig;
import com.infamous.dungeons_libraries.items.artifacts.config.ArtifactGearConfigRegistry;
import com.infamous.dungeons_libraries.network.EliteMobMessage;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;
import java.util.Map;

import static com.infamous.dungeons_libraries.items.GearConfigReloadListener.reloadAllItems;

public record ArtifactGearConfigSyncPacket(Map<ResourceLocation, ArtifactGearConfig> data) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ArtifactGearConfigSyncPacket> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("artifact_gear"));


    private static final Codec<Map<ResourceLocation, ArtifactGearConfig>> MAPPER =
            Codec.unboundedMap(ResourceLocation.CODEC, ArtifactGearConfig.CODEC);

    public static final StreamCodec<ByteBuf, ArtifactGearConfigSyncPacket> STREAM_CODEC =
            StreamCodec.composite(
                    // Stream codec and getter pair
                    ByteBufCodecs.fromCodec(MAPPER), ArtifactGearConfigSyncPacket::data,
                    ArtifactGearConfigSyncPacket::new
            );

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeNbt((CompoundTag) (MAPPER.encodeStart(NbtOps.INSTANCE, this.data).result().orElse(new CompoundTag())));
    }

    public static ArtifactGearConfigSyncPacket decode(FriendlyByteBuf buffer) {
        return new ArtifactGearConfigSyncPacket(MAPPER.parse(NbtOps.INSTANCE, buffer.readNbt()).result().orElse(new HashMap<>()));
    }

    public void onPacketReceived(IPayloadContext context) {
        context.enqueueWork(this::handlePacketOnMainThread);
    }

    private void handlePacketOnMainThread() {
        ArtifactGearConfigRegistry.ARTIFACT_GEAR_CONFIGS.setData(this.data);
        reloadAllItems();
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
