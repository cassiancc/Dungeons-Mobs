package com.infamous.dungeons_libraries.integration.curios.client.message;

import com.infamous.dungeons_libraries.items.artifacts.ArtifactItem;
import com.infamous.dungeons_libraries.items.artifacts.ArtifactUseContext;
import com.infamous.dungeons_libraries.network.gearconfig.ArmorGearConfigSyncPacket;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Optional;
import java.util.function.Supplier;

public record CuriosArtifactStartMessage(int slot, BlockHitResult hitResult) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CuriosArtifactStartMessage> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("curios_artifact_start"));
    public static final StreamCodec<FriendlyByteBuf, CuriosArtifactStartMessage> STREAM_CODEC = Packet.codec(
            CuriosArtifactStartMessage::decode, CuriosArtifactStartMessage::new
    );

    public static void encode(CuriosArtifactStartMessage packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.slot);
        buf.writeBlockHitResult(packet.hitResult);
    }

    public static CuriosArtifactStartMessage decode(FriendlyByteBuf buf) {
        return new CuriosArtifactStartMessage(buf.readInt(), buf.readBlockHitResult());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class CuriosArtifactHandler {
        public static void handle(CuriosArtifactStartMessage packet, IPayloadContext ctx) {
            if (packet != null) {
                ctx.enqueueWork(() -> {
                    Player player = ctx.player();
                    if (player instanceof ServerPlayer) {
                        CuriosApi.getCuriosInventory(player).ifPresent(iCuriosItemHandler -> {
                            Optional<ICurioStacksHandler> artifactStackHandler = iCuriosItemHandler.getStacksHandler("artifact");
                            if (artifactStackHandler.isPresent()) {
                                ItemStack artifact = artifactStackHandler.get().getStacks().getStackInSlot(packet.slot);
                                if (!artifact.isEmpty() && artifact.getItem() instanceof ArtifactItem) {
                                    ArtifactUseContext iuc = new ArtifactUseContext(player.level(), player, artifact, packet.hitResult);
                                    ((ArtifactItem) artifact.getItem()).activateArtifact(iuc);
                                }
                            }
                        });
                    }

                });
            }
        }
    }
}