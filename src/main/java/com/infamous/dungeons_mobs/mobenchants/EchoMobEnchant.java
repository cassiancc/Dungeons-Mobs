package com.infamous.dungeons_mobs.mobenchants;

import baguchan.enchantwithmob.mobenchant.MobEnchant;
import com.infamous.dungeons_mobs.mod.ModDamageSources;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.infamous.dungeons_mobs.mobenchants.NewMobEnchantUtils.executeIfPresentWithLevel;
import static com.infamous.dungeons_mobs.mod.ModMobEnchants.ECHO;

public class EchoMobEnchant extends MobEnchant {

    private static final float ECHO_CHANCE = 0.25f;

    public EchoMobEnchant(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        LivingEntity defender = event.getEntity();
        Entity entity = event.getSource().getEntity();
        if (entity instanceof LivingEntity attacker && isMelee(event.getSource()) && !(event.getSource().is(ModDamageSources.ECHO))) {
            executeIfPresentWithLevel(attacker, ECHO.get(), (level) -> {
                if (attacker.getRandom().nextFloat() <= ECHO_CHANCE * level) {
                    defender.hurt(defender.damageSources().source(ModDamageSources.ECHO, attacker), event.getAmount());
                    defender.invulnerableTime = 0;
                }
            });
        }
    }

    private static boolean isMelee(DamageSource source) {
        return !source.is(DamageTypes.MOB_PROJECTILE) && !source.is(DamageTypes.EXPLOSION) && !source.is(DamageTypes.MAGIC) && !source.is(DamageTypes.ON_FIRE);
    }
}