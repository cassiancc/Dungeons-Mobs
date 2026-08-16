package com.infamous.dungeons_mobs.network;

import com.infamous.dungeons_mobs.network.message.AncientMessage;
import com.infamous.dungeons_mobs.network.message.AnimatedPropsMessage;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NetworkHandler {
    public static PayloadRegistrar INSTANCE;
    static boolean registered = false;

    public NetworkHandler() {
    }

    public static void init(PayloadRegistrar registrar) {
        if (registered) return; // fixme 1.21
        INSTANCE = registrar;
        INSTANCE.playBidirectional(AncientMessage.TYPE, AncientMessage.STREAM_CODEC, AncientMessage::onPacketReceived);
        INSTANCE.playBidirectional(AnimatedPropsMessage.TYPE, AnimatedPropsMessage.STREAM_CODEC, AnimatedPropsMessage::onPacketReceived);
        registered = true;
    }
}
