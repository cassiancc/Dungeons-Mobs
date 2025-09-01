package com.infamous.dungeons_mobs.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class AbstractHorseEntityMixin  {

    @Shadow
    public abstract boolean isAlive();

    @Inject(at = @At("HEAD"), method = "getFlyingSpeed", cancellable = true)
    private void travel(CallbackInfoReturnable<Float> cir) {
        var e = (LivingEntity) (Object) this;
        if (this.isAlive() && e instanceof AbstractHorse horse) {
            if (horse.isVehicle() && horse.hasControllingPassenger() && horse.isSaddled() && horse.getControllingPassenger() instanceof Mob) {
                //DungeonsMobs.LOGGER.info("Forcing saddled horse to follow rider's AI!");
                cir.setReturnValue(0.02F);
                cir.cancel();
            }
        }
    }
}
