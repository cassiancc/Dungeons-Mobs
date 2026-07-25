package com.infamous.dungeons_libraries.integration.curios;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CuriosIntegration {

    public static final ResourceLocation CURIOS_ICON_TEXTURE = GeneralUtil.librariesLoc("icon/empty_artifact_slot");

    public static final String ARTIFACT_IDENTIFIER = "artifact";

    public static List<ItemStack> getArtifacts(LivingEntity livingEntity) {
        var curiosHandler = CuriosApi.getCuriosInventory(livingEntity);
        if (curiosHandler.isPresent()) {
            Optional<ICurioStacksHandler> artifactStackHandler = curiosHandler.get().getStacksHandler(ARTIFACT_IDENTIFIER);
            if (artifactStackHandler.isPresent()) {
                IDynamicStackHandler stacks = artifactStackHandler.get().getStacks();
                List<ItemStack> artifacts = new ArrayList<>();
                for (int i = 0; i < stacks.getSlots(); i++) {
                    artifacts.add(stacks.getStackInSlot(i));
                }
                return artifacts;
            }
        }
        return Collections.emptyList();
    }

}