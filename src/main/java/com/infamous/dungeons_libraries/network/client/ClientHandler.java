package com.infamous.dungeons_libraries.network.client;

import com.infamous.dungeons_libraries.capabilities.artifact.ArtifactUsage;
import com.infamous.dungeons_libraries.capabilities.artifact.ArtifactUsageHelper;
import com.infamous.dungeons_libraries.capabilities.elite.EliteMob;
import com.infamous.dungeons_libraries.capabilities.elite.EliteMobHelper;
import com.infamous.dungeons_libraries.integration.curios.client.message.CuriosArtifactStopMessage;
import com.infamous.dungeons_libraries.items.artifacts.ArtifactItem;
import com.infamous.dungeons_libraries.network.EliteMobMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientHandler {
    public static void handleCuriosArtifactStopMessage(CuriosArtifactStopMessage packet, IPayloadContext context) {
        if (packet != null) {
            if (context.player().isLocalPlayer()) {
                context.enqueueWork(() -> {
                    Player player = context.player();
                    if (player != null) {
                        ArtifactUsage cap = ArtifactUsageHelper.getArtifactUsageCapability(player);
                        ItemStack artifactStack = cap.getUsingArtifact();
                        if (artifactStack != null && artifactStack.getItem() instanceof ArtifactItem artifactItem) {
                            artifactItem.stopUsingArtifact(player);
                            cap.stopUsingArtifact();
                        }
                    }
                });
            }
        }
    }

    public static void handleEliteMobMessage(EliteMobMessage message, IPayloadContext context) {
        if (context.player().isLocalPlayer()) {
            context.enqueueWork(() -> {
                Entity entity = context.player().level().getEntity(message.entityId());
                if (entity instanceof LivingEntity) {
                    EliteMob cap = EliteMobHelper.getEliteMobCapability(entity);
                    cap.setElite(message.isElite());
                    cap.setTexture(message.texture());
                    if (cap.isElite()) {
                        entity.refreshDimensions();
                    }
                }
            });
        }
    }
}
