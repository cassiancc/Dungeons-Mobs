package com.infamous.dungeons_mobs.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class EnsnaredEffect extends MobEffect {

    public EnsnaredEffect(MobEffectCategory typeIn, int liquidColourIn) {
        super(typeIn, liquidColourIn);
    }

    @Override
    public boolean applyEffectTick(LivingEntity owner, int amplifier) {
        if (!owner.level().isClientSide()) {
            owner.setDeltaMovement(0, owner.getDeltaMovement().y, 0);
            owner.setSpeed(0);
        }
        return super.applyEffectTick(owner, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
