package com.infamous.dungeons_libraries.items.gearconfig;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class PickaxeGear extends ToolGear {

    public PickaxeGear(Properties properties) {
        super(BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility ItemAbility) {
        return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(ItemAbility);
    }
}
