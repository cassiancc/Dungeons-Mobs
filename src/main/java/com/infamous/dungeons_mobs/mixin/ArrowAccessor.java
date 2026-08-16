package com.infamous.dungeons_mobs.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(Arrow.class)
public interface ArrowAccessor {

    @Invoker()
    PotionContents callGetPotionContents();
}
