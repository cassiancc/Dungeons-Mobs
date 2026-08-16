package com.infamous.dungeons_libraries.network.gearconfig;

import com.infamous.dungeons_libraries.items.gearconfig.MeleeGearConfig;
import com.infamous.dungeons_libraries.items.gearconfig.MeleeGearConfigRegistry;
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

public record MeleeGearConfigSyncPacket(Map<ResourceLocation, MeleeGearConfig> data) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<MeleeGearConfigSyncPacket> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("melee_gear_config_sync"));
    private static final Codec<Map<ResourceLocation, MeleeGearConfig>> MAPPER =
            Codec.unboundedMap(ResourceLocation.CODEC, MeleeGearConfig.CODEC);
    public static final StreamCodec<ByteBuf, MeleeGearConfigSyncPacket> STREAM_CODEC =
            StreamCodec.composite(
                    // Stream codec and getter pair
                    ByteBufCodecs.fromCodec(MAPPER), MeleeGearConfigSyncPacket::data,
                    MeleeGearConfigSyncPacket::new
            );

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeNbt((CompoundTag) (MAPPER.encodeStart(NbtOps.INSTANCE, this.data).result().orElse(new CompoundTag())));
    }

    public static MeleeGearConfigSyncPacket decode(FriendlyByteBuf buffer) {
        return new MeleeGearConfigSyncPacket(MAPPER.parse(NbtOps.INSTANCE, buffer.readNbt()).result().orElse(new HashMap<>()));
    }

    public void onPacketReceived(IPayloadContext context) {
        context.enqueueWork(this::handlePacketOnMainThread);
    }

    private void handlePacketOnMainThread() {
        MeleeGearConfigRegistry.MELEE_GEAR_CONFIGS.setData(this.data);
        reloadAllItems();
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
