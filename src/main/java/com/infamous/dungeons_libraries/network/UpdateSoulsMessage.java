package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCaster;
import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCasterHelper;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class UpdateSoulsMessage implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<UpdateSoulsMessage> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("update_souls"));
    public static final StreamCodec<ByteBuf, UpdateSoulsMessage> STREAM_CODEC =
            StreamCodec.composite(
                    // Stream codec and getter pair
                    ByteBufCodecs.FLOAT, UpdateSoulsMessage::newAmount,
                    UpdateSoulsMessage::new
            );

    private Float newAmount() {
        return newAmount;
    }

    private final float newAmount;

    public UpdateSoulsMessage(float souls) {
        this.newAmount = souls;
    }

    public static void encode(UpdateSoulsMessage packet, FriendlyByteBuf buf) {
        buf.writeFloat(packet.newAmount);
    }

    public static UpdateSoulsMessage decode(FriendlyByteBuf buf) {
        return new UpdateSoulsMessage(buf.readFloat());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class UpdateSoulsHandler {
        public static void handle(UpdateSoulsMessage packet, IPayloadContext ctx) {
            if (packet != null) {
                ctx.enqueueWork(() -> {
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        SoulCaster soulCasterCap = SoulCasterHelper.getSoulCasterCapability(player);
                        soulCasterCap.setSouls(packet.newAmount, player);
                    }
                });
            }
        }
    }
}
