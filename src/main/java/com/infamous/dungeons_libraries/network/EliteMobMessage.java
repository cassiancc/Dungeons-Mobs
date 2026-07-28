package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.integration.curios.client.message.CuriosArtifactStartMessage;
import com.infamous.dungeons_libraries.network.client.ClientHandler;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record EliteMobMessage(int entityId, boolean isElite, ResourceLocation texture) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<EliteMobMessage> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("elite_mob"));
    public static final StreamCodec<ByteBuf, EliteMobMessage> STREAM_CODEC =
            StreamCodec.composite(
                    // Stream codec and getter pair
                    ByteBufCodecs.VAR_INT, EliteMobMessage::entityId,
                    ByteBufCodecs.BOOL, EliteMobMessage::isElite,
                    ResourceLocation.STREAM_CODEC, EliteMobMessage::texture,
                    EliteMobMessage::new
            );

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeBoolean(isElite);
        buffer.writeResourceLocation(texture);
    }

    public static EliteMobMessage decode(FriendlyByteBuf buffer) {
        int entityId = buffer.readInt();
        boolean isElite = buffer.readBoolean();
        ResourceLocation texture = buffer.readResourceLocation();

        return new EliteMobMessage(entityId, isElite, texture);
    }

    public static void handle(EliteMobMessage message, IPayloadContext contextSupplier) {
        ClientHandler.handleEliteMobMessage(message, contextSupplier);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
