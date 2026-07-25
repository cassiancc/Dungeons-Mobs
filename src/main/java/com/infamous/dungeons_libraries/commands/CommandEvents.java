package com.infamous.dungeons_libraries.commands;

import com.infamous.dungeons_libraries.DungeonsLibraries;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@EventBusSubscriber(modid = DungeonsLibraries.MODID)
public class CommandEvents {
    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> commandDispatcher = event.getDispatcher();
        SoulsCommand.register(commandDispatcher);
        SummonEliteCommand.register(commandDispatcher, event.getBuildContext());
    }
}
