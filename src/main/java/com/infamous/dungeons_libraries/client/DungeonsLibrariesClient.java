package com.infamous.dungeons_libraries.client;

import com.infamous.dungeons_libraries.DungeonsLibraries;
import com.infamous.dungeons_libraries.client.gui.elementconfig.GuiElementConfigRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = DungeonsLibraries.MODID, dist = Dist.CLIENT)
public class DungeonsLibrariesClient {
	public DungeonsLibrariesClient(IEventBus modEventBus, ModContainer container) {
		GuiElementConfigRegistry.initGuiElementConfigs();
	}
}
