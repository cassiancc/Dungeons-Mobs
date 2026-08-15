package com.infamous.dungeons_libraries.summon;

import com.infamous.dungeons_libraries.capabilities.minionmaster.Follower;
import com.infamous.dungeons_libraries.capabilities.minionmaster.FollowerLeaderHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static com.infamous.dungeons_libraries.capabilities.minionmaster.FollowerLeaderHelper.getFollowerCapability;

@EventBusSubscriber(modid = MODID)
public class SummonEvents {

    @SubscribeEvent
    public static void onSummonedMobAttemptsToAttack(LivingChangeTargetEvent event) {
        if (event.getNewAboutToBeSetTarget() == null) return;
        if (FollowerLeaderHelper.isFollower(event.getEntity())) {
            LivingEntity followerAttacker = event.getEntity();
            Follower attackerFollowerCapability = getFollowerCapability(followerAttacker);
            if (attackerFollowerCapability.getLeader() != null) {
                LivingEntity attackersOwner = attackerFollowerCapability.getLeader();
                if (FollowerLeaderHelper.isFollower(event.getNewAboutToBeSetTarget())) {
                    LivingEntity summonableTarget = event.getNewAboutToBeSetTarget();
                    Follower targetFollowerCapability = getFollowerCapability(summonableTarget);
                    if (targetFollowerCapability.getLeader() != null) {
                        LivingEntity targetsOwner = targetFollowerCapability.getLeader();
                        if (targetsOwner.equals(attackersOwner)) {
                            event.setCanceled(true);
                            preventAttackForSummonableMob(followerAttacker);
                        }
                    }
                }
            }
            if (attackerFollowerCapability.getLeader() == event.getNewAboutToBeSetTarget()) {
                event.setCanceled(true);
                preventAttackForSummonableMob(followerAttacker);
            }
        }
    }

    private static void preventAttackForSummonableMob(LivingEntity followerAttacker) {
        if (followerAttacker instanceof NeutralMob) {
            ((NeutralMob) followerAttacker).stopBeingAngry();
        }
    }
}
