package com.infamous.dungeons_libraries.capabilities.playerrewards;

import net.minecraft.world.entity.player.Player;

import static com.infamous.dungeons_libraries.capabilities.ModCapabilities.PLAYER_REWARDS_CAPABILITY;


public class PlayerRewardsHelper {

    public static PlayerRewards getPlayerRewardsCapability(Player playerEntity) {
        return playerEntity.getData(PLAYER_REWARDS_CAPABILITY);
    }

}
