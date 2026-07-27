package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCaster;
import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCasterHelper;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.DistExecutor.SafeRunnable;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.function.Supplier;

public class UpdateSoulsMessage implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<UpdateSoulsMessage> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("update_souls"));
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
                ctx.get().setPacketHandled(true);
                ctx.get().enqueueWork(() ->
                        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> new SafeRunnable() {
                            private static final long serialVersionUID = 1;

                            @Override
                            public void run() {
                                LocalPlayer player = Minecraft.getInstance().player;
                                if (player != null) {
                                    SoulCaster soulCasterCap = SoulCasterHelper.getSoulCasterCapability(player);
                                    soulCasterCap.setSouls(packet.newAmount, player);
                                }
                            }
                        }));
            }
        }
    }
}
