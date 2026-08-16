package com.infamous.dungeons_mobs.capabilities.animatedprops;

import com.infamous.dungeons_mobs.network.NetworkHandler;
import com.infamous.dungeons_mobs.network.message.AnimatedPropsMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;

@EventBusSubscriber(modid = MODID)
public class AnimatedPropsEvents {

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        if (player instanceof ServerPlayer serverPlayer && target instanceof Vindicator) {
            AnimatedProps cap = AnimatedPropsHelper.getAnimatedPropsCapability((Vindicator) target);
            PacketDistributor.sendToPlayer(serverPlayer, new AnimatedPropsMessage(target.getId(), cap));
        }
    }
}
