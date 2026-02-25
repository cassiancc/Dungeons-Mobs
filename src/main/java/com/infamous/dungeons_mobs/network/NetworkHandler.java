package com.infamous.dungeons_mobs.network;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.network.message.AncientMessage;
import com.infamous.dungeons_mobs.network.message.AnimatedPropsMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
                    GeneralUtil.mobsLoc("network"))
            .clientAcceptedVersions("1"::equals)
            .serverAcceptedVersions("1"::equals)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();

    protected static int PACKET_COUNTER = 0;

    public NetworkHandler() {
    }

    public static void init() {
        INSTANCE.messageBuilder(AncientMessage.class, 0)
                .encoder(AncientMessage::encode).decoder(AncientMessage::decode)
                .consumerNetworkThread(AncientMessage::onPacketReceived)
                .add();
        INSTANCE.messageBuilder(AnimatedPropsMessage.class, 0)
                .encoder(AnimatedPropsMessage::encode).decoder(AnimatedPropsMessage::decode)
                .consumerNetworkThread(AnimatedPropsMessage::onPacketReceived)
                .add();
    }

    public static int incrementAndGetPacketCounter() {
        return PACKET_COUNTER++;
    }
}
