package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.combat.DualWieldHandler;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.function.Supplier;

public class SwitchHandMessage implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SwitchHandMessage> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("switch_hand"));
    public static final StreamCodec<ByteBuf, SwitchHandMessage> STREAM_CODEC =
            StreamCodec.unit(new SwitchHandMessage());

    public SwitchHandMessage() {
    }

    public static void encode(SwitchHandMessage packet, FriendlyByteBuf buf) {

    }

    public static SwitchHandMessage decode(FriendlyByteBuf buf) {
        return new SwitchHandMessage();
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class SwitchHandHandler {
        public static void handle(SwitchHandMessage packet, IPayloadContext ctx) {
            if (packet != null) {
                ctx.enqueueWork(() -> {
                    Player player = ctx.player();
                    if (player instanceof ServerPlayer serverPlayer) {
                        DualWieldHandler.switchHand(serverPlayer);
                    }
                });
            }
        }
    }
}
