package com.infamous.dungeons_libraries.integration.curios.client;

import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.bus.api.SubscribeEvent;

import static com.infamous.dungeons_libraries.integration.curios.client.CuriosKeyBindings.*;

public class CuriosClientIntegration {

    @SubscribeEvent
    public static void setupCuriosKeybindings(RegisterKeyMappingsEvent event) {
        activateArtifact1.setKeyConflictContext(KeyConflictContext.IN_GAME);
        event.register(activateArtifact1);
        activateArtifact2.setKeyConflictContext(KeyConflictContext.IN_GAME);
        event.register(activateArtifact2);
        activateArtifact3.setKeyConflictContext(KeyConflictContext.IN_GAME);
        event.register(activateArtifact3);
    }
}
