package com.infamous.dungeons_mobs.entities.illagers;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.level.Level;

@SuppressWarnings("EntityConstructor")
public class ArchIllagerEntity extends AbstractIllager {

    protected ArchIllagerEntity(EntityType<? extends ArchIllagerEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public boolean canBeLeader() {
        return false;
    }

    @Override
    public void applyRaidBuffs(ServerLevel level, int wave, boolean unused) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }
}
