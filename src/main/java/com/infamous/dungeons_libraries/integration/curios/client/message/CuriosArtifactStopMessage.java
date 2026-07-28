package com.infamous.dungeons_libraries.integration.curios.client.message;

import com.infamous.dungeons_libraries.network.client.ClientHandler;
import com.infamous.dungeons_libraries.network.gearconfig.ArmorGearConfigSyncPacket;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.function.Supplier;

public class CuriosArtifactStopMessage implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CuriosArtifactStopMessage> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("curios_artifact_stop"));
    public static final StreamCodec<ByteBuf, CuriosArtifactStopMessage> STREAM_CODEC =
            StreamCodec.unit(new CuriosArtifactStopMessage());

    public CuriosArtifactStopMessage() {
    }

    public void encode(FriendlyByteBuf buf) {

    }

    public static CuriosArtifactStopMessage decode(FriendlyByteBuf buf) {
        return new CuriosArtifactStopMessage();
    }

    public static void handle(CuriosArtifactStopMessage packet, IPayloadContext ctx) {
        ClientHandler.handleCuriosArtifactStopMessage(packet, ctx);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}