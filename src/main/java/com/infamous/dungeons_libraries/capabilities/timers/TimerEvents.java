package com.infamous.dungeons_libraries.capabilities.timers;

import com.infamous.dungeons_libraries.DungeonsLibraries;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = DungeonsLibraries.MODID)
public class TimerEvents {

    @SubscribeEvent
    public static void onLivingUpdate(EntityTickEvent.Post event) {
        Timers timersCapability = TimersHelper.getTimersCapability(event.getEntity());
        timersCapability.tickTimers();
    }

    @SubscribeEvent
    public static void onPlayerUpdate(PlayerTickEvent.Pre event) {
        Timers timersCapability = TimersHelper.getTimersCapability(event.getEntity());
        if (!event.getEntity().isSpectator() && !event.getEntity().level().isClientSide()) {
            timersCapability.tickTimers();
        }
    }
}
