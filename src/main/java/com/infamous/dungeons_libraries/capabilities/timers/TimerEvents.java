package com.infamous.dungeons_libraries.capabilities.timers;

import com.infamous.dungeons_libraries.DungeonsLibraries;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@EventBusSubscriber(modid = DungeonsLibraries.MODID)
public class TimerEvents {

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        Timers timersCapability = TimersHelper.getTimersCapability(event.getEntity());
        timersCapability.tickTimers();
    }

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        Timers timersCapability = TimersHelper.getTimersCapability(event.player);
        if (event.phase == TickEvent.Phase.START && !event.player.isSpectator() && !event.player.level().isClientSide()) {
            timersCapability.tickTimers();
        }
    }
}
