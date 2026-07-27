package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record BreakItemMessage(int entityId, ItemStack stack) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<BreakItemMessage> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("break_item"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BreakItemMessage> STREAM_CODEC =
            StreamCodec.composite(
                    // Stream codec and getter pair
                    ByteBufCodecs.VAR_INT, BreakItemMessage::entityId,
                    ItemStack.STREAM_CODEC, BreakItemMessage::stack,
                    BreakItemMessage::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }


    public static class BreakItemHandler {
        public static void handle(BreakItemMessage packet, IPayloadContext ctx) {
            if (packet != null) {
                ctx.enqueueWork(()->{
                    ClientLevel world = Minecraft.getInstance().level;
                    Entity target = null;
                    if (world != null)
                        target = world.getEntity(packet.entityId);
                    if (target instanceof LivingEntity livingEntity) {
                        livingEntity.breakItem(packet.stack);
                    }
                });
            }
        }
    }
}
