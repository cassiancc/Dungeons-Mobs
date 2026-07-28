package com.infamous.dungeons_mobs.entities;

import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;
import static com.infamous.dungeons_mobs.mod.ModEffects.ENSNARED;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEntityEvents {

    @SubscribeEvent
    public static void preventExtraMovement(MovementInputUpdateEvent event) {
        Player owner = event.getEntity();

        if (owner.hasEffect(ENSNARED)) {
            event.getInput().getMoveVector().scale(0);
            if (event.getInput().jumping) event.getInput().jumping = false;
        }
    }
}
