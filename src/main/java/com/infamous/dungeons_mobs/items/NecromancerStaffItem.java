package com.infamous.dungeons_mobs.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.infamous.dungeons_libraries.capabilities.minionmaster.Leader;
import com.infamous.dungeons_libraries.items.artifacts.ArtifactItem;
import com.infamous.dungeons_libraries.items.artifacts.ArtifactUseContext;
import com.infamous.dungeons_libraries.items.interfaces.ISoulConsumer;
import com.infamous.dungeons_libraries.network.BreakItemMessage;
import com.infamous.dungeons_libraries.summon.SummonHelper;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_libraries.utils.SoundHelper;
import com.infamous.dungeons_mobs.interfaces.IHasInventorySprite;
import com.infamous.dungeons_mobs.mod.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.infamous.dungeons_libraries.attribute.AttributeRegistry.FOLLOWER_COST_LIMIT;
import static com.infamous.dungeons_libraries.capabilities.minionmaster.FollowerLeaderHelper.getLeaderCapability;

public class NecromancerStaffItem extends ArtifactItem implements IHasInventorySprite, ISoulConsumer {
    public NecromancerStaffItem(Properties properties) {
        super(properties);
        procOnItemUse = true;
    }

    public InteractionResultHolder<ItemStack> procArtifact(ArtifactUseContext itemUseContext) {
        Level world = itemUseContext.getLevel();
        if (world.isClientSide || itemUseContext.isHitMiss()) {
            return InteractionResultHolder.success(itemUseContext.getItemStack());
        } else {
            ItemStack itemUseContextItem = itemUseContext.getItemStack();
            Player itemUseContextPlayer = itemUseContext.getPlayer();
            BlockPos itemUseContextPos = itemUseContext.getClickedPos();
            Direction itemUseContextFace = itemUseContext.getClickedFace();
            BlockState blockState = world.getBlockState(itemUseContextPos);

            BlockPos blockPos;
            if (blockState.getCollisionShape(world, itemUseContextPos).isEmpty()) {
                blockPos = itemUseContextPos;
            } else {
                blockPos = itemUseContextPos.relative(itemUseContextFace);
            }

            if (itemUseContextPlayer != null) {
				Leader summonerCap = getLeaderCapability(itemUseContextPlayer);
                if (summonerCap != null) {
                    Entity summoned = SummonHelper.summonEntity(itemUseContextPlayer, itemUseContextPlayer.blockPosition(), EntityType.ZOMBIE);
                    if (summoned != null) {
                        SoundHelper.playCreatureSound(itemUseContextPlayer, ModSoundEvents.NECROMANCER_SUMMON.get());
                        itemUseContextItem.hurtAndBreak(1, (ServerLevel) itemUseContextPlayer.level(), (LivingEntity) summoned, (i) -> PacketDistributor.sendToPlayersTrackingEntityAndSelf(summoned, new BreakItemMessage(summoned.getId(), itemUseContextItem)));
                        ArtifactItem.putArtifactOnCooldown(itemUseContextPlayer, itemUseContextItem.getItem());
                    } else {
                        if (world instanceof ServerLevel) {
                            List<Entity> zombieEntities = summonerCap.getSummonedMobs().stream().filter(entity -> entity.getType() == EntityType.ZOMBIE).collect(Collectors.toList());
                            zombieEntities.forEach(entity -> {
                                entity.teleportTo((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.05D, (double) blockPos.getZ() + 0.5D);
                            });
                        }
                    }
                }
            }
            return InteractionResultHolder.consume(itemUseContextItem);
        }
    }

    @Override
    public int getCooldownInSeconds() {
        return 20;
    }

    @Override
    public int getDurationInSeconds() {
        return 0;
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getDefaultAttributeModifiers(int slotIndex) {
        return getAttributeModifiersForSlot(getUUIDForSlot(slotIndex));
    }

    private ImmutableMultimap<Holder<Attribute>, AttributeModifier> getAttributeModifiersForSlot(UUID slot_uuid) {
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(FOLLOWER_COST_LIMIT, new AttributeModifier(GeneralUtil.librariesLoc( "artifact_modifier"), 3, AttributeModifier.Operation.ADD_VALUE));
        return builder.build();
    }

    @Override
    public float getActivationCost(ItemStack stack) {
        return 50;
    }
}
