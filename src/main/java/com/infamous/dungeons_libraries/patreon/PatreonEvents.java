package com.infamous.dungeons_libraries.patreon;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static com.infamous.dungeons_libraries.patreon.PatreonHelper.loadPatreons;

@EventBusSubscriber(modid = MODID)
public class PatreonEvents {
    private static final List<Consumer<UUID>> PATREON_JOIN_CONSUMERS = new ArrayList<>();

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        loadPatreons(() -> PATREON_JOIN_CONSUMERS.forEach(consumer -> consumer.accept(event.getEntity().getUUID())));
    }

    public static void onServerStart(ServerStartedEvent event) {
        loadPatreons(() -> {
        });
    }

    private static void addConsumer(Consumer<UUID> consumer) {
        PATREON_JOIN_CONSUMERS.add(consumer);
    }

}
