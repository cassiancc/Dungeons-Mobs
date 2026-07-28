package com.infamous.dungeons_mobs.capabilities.ancient;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;

import java.util.List;

import com.infamous.dungeons_mobs.network.NetworkHandler;
import com.infamous.dungeons_mobs.network.message.AncientMessage;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = MODID)
public class AncientEvents {

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        if (player instanceof ServerPlayer) {
            Ancient cap = AncientHelper.getAncientCapability(target);
            if (cap.isAncient()) {
                NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new AncientMessage(target.getId(), cap.isAncient()));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(EntityTickEvent event) {
        Entity entity = event.getEntity();
        if (!entity.level().isClientSide() && entity instanceof LivingEntity entityLiving) {
            Ancient cap = AncientHelper.getAncientCapability(entityLiving);
            if (cap.isAncient() && cap.getBossInfo() != null) {
                List<ServerPlayer> nearbyEntities = entityLiving.level().getNearbyEntities(ServerPlayer.class, TargetingConditions.forNonCombat().range(20.0D).ignoreInvisibilityTesting(), entityLiving, entityLiving.getBoundingBox().inflate(20D, 10D, 20D));
                nearbyEntities.forEach(playerEntity ->
                        cap.getBossInfo().addPlayer(playerEntity)
                );
                List<ServerPlayer> trackingPlayers = new ObjectArrayList<>(cap.getBossInfo().getPlayers());
                List<ServerPlayer> furtherEntities = entityLiving.level().getNearbyEntities(ServerPlayer.class, TargetingConditions.forNonCombat().range(50.0D).ignoreInvisibilityTesting(), entityLiving, entityLiving.getBoundingBox().inflate(50D, 20D, 50D));
                trackingPlayers.forEach(playerEntity -> {
                    if (!furtherEntities.contains(playerEntity)) {
                        cap.getBossInfo().removePlayer(playerEntity);
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdateEvent(EntityTickEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            Ancient cap = AncientHelper.getAncientCapability(livingEntity);
            if (cap.isAncient() && cap.getBossInfo() != null) {
                cap.getBossInfo().setProgress(livingEntity.getHealth() / livingEntity.getMaxHealth());
            }
        }

    }
}
