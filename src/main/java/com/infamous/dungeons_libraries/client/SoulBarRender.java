package com.infamous.dungeons_libraries.client;

import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCaster;
import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCasterHelper;
import com.infamous.dungeons_libraries.client.gui.elementconfig.GuiElementConfig;
import com.infamous.dungeons_libraries.client.gui.elementconfig.GuiElementConfigRegistry;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.bus.api.SubscribeEvent;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static com.infamous.dungeons_libraries.attribute.AttributeRegistry.SOUL_CAP;

@EventBusSubscriber(value = Dist.CLIENT, modid = MODID)
public class SoulBarRender {
    private static final ResourceLocation SOUL_BAR_RESOURCE = GeneralUtil.loc(MODID, "textures/misc/soul_bar.png");
    public static final int SOUL_LEVEL_COLOR = 0x10B0E4;

    @SubscribeEvent
    public static void displaySoulBar(RenderGuiLayerEvent.Post event) {
        GuiGraphics guiGraphics = event.getGuiGraphics();
        Window sr = Minecraft.getInstance().getWindow();
        int scaledWidth = sr.getGuiScaledWidth();
        int scaledHeight = sr.getGuiScaledHeight();
        final Minecraft mc = Minecraft.getInstance();

        if (event.getName().equals(VanillaGuiLayers.HOTBAR) && mc.getCameraEntity() instanceof Player) {
            GuiElementConfig guiElementConfig = GuiElementConfigRegistry.getConfig(GeneralUtil.loc(MODID, "soul_bar"));
            if (guiElementConfig.isHidden()) return;
            //draw souls
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            Player renderPlayer = (Player) mc.getCameraEntity();
            if (renderPlayer == null) return;
            SoulCaster soulCasterCapability = SoulCasterHelper.getSoulCasterCapability(renderPlayer);

            float souls = soulCasterCapability.getSouls();
            double maxSouls = renderPlayer.getAttributeValue(SOUL_CAP);

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            mc.getProfiler().push("soulBar");

            int xPos = guiElementConfig.getXPosition(scaledWidth);
            int yPos = guiElementConfig.getYPosition(scaledHeight);

            if (souls > 0) {
                int backgroundBarWidth = guiElementConfig.getSizeX();
                int foregroundBarWidth = (int) (souls / maxSouls * guiElementConfig.getSizeX());
                guiGraphics.blit(SOUL_BAR_RESOURCE, xPos, yPos, 0, 0, backgroundBarWidth, 5, 121, 10);
                guiGraphics.blit(SOUL_BAR_RESOURCE, xPos, yPos, 0, 5, foregroundBarWidth, 5, 121, 10);
            }
            mc.getProfiler().pop();

            if (souls > 0) {
                mc.getProfiler().push("soulLevel");
                String soulLevel = "" + souls;
                int baseXPos = xPos + (guiElementConfig.getSizeX() / 2) - (mc.font.width(soulLevel) / 2);
                int baseYPos = scaledHeight - guiElementConfig.getSizeY() - mc.font.lineHeight;
                guiGraphics.drawString(mc.font, soulLevel, baseXPos, baseYPos, SOUL_LEVEL_COLOR);
                mc.getProfiler().pop();
            }

            RenderSystem.disableBlend();
        }


    }

}
