package com.infamous.dungeons_mobs.interfaces;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface IHasItemStackData {

    ItemStack getDataItem();

    void setDataItem(ItemStack dataItem);

    default void writeDataItem(CompoundTag tag, String key, RegistryAccess registryAccess) {
        ItemStack itemStack = this.getDataItem();
        if (!itemStack.isEmpty()) {
            tag.put(key, itemStack.save(registryAccess));
        }
    }

    default void readDataItem(CompoundTag tag, String key, RegistryAccess registryAccess) {
        ItemStack itemstack = ItemStack.parse(registryAccess, tag.getCompound(key)).orElse(ItemStack.EMPTY);
        this.setDataItem(itemstack);
    }
}
