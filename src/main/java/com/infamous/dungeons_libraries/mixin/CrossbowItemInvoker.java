package com.infamous.dungeons_libraries.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(CrossbowItem.class)
public interface CrossbowItemInvoker {

    @Accessor
    boolean getStartSoundPlayed();

    @Accessor
    boolean getMidLoadSoundPlayed();

    @Accessor
    void setStartSoundPlayed(boolean b);

    @Accessor
    void setMidLoadSoundPlayed(boolean b);

    @Invoker
    static boolean callTryLoadProjectiles(LivingEntity livingEntity, ItemStack stack){
        throw new RuntimeException("Invoker failed to mixin");
    }
}
