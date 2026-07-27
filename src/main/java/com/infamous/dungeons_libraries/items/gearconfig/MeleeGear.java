package com.infamous.dungeons_libraries.items.gearconfig;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.infamous.dungeons_libraries.items.interfaces.IComboWeapon;
import com.infamous.dungeons_libraries.items.interfaces.IMeleeWeapon;
import com.infamous.dungeons_libraries.items.interfaces.IReloadableGear;
import com.infamous.dungeons_libraries.items.interfaces.IUniqueGear;
import com.infamous.dungeons_libraries.mixin.ItemAccessor;
import com.infamous.dungeons_libraries.mixin.TieredItemAccessor;
import com.infamous.dungeons_libraries.utils.DescriptionHelper;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_libraries.utils.MojankHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

import static java.util.UUID.randomUUID;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED;
import static net.minecraft.core.registries.BuiltInRegistries.ATTRIBUTE;

public class MeleeGear extends TieredItem implements IMeleeWeapon, IComboWeapon, IReloadableGear, IUniqueGear {

    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    private MeleeGearConfig meleeGearConfig;
    private float attackDamage;

    public MeleeGear(Item.Properties properties) {
        super(Tiers.WOOD, properties);
        reload();
    }

    @Override
    public void reload() {
        meleeGearConfig = MeleeGearConfigRegistry.getConfig(BuiltInRegistries.ITEM.getKey(this));
        ((TieredItemAccessor) this).setTier(meleeGearConfig.getWeaponMaterial());
        ((ItemAccessor) this).setMaxDamage(this.getTier().getUses());
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        meleeGearConfig.getAttributes().forEach(attributeModifier -> {
            Attribute attribute = ATTRIBUTE.get(attributeModifier.getAttributeResourceLocation());
            if (attribute != null) {
                ResourceLocation uuid = GeneralUtil.librariesLoc("weapon_modifier");
                if (ATTACK_DAMAGE.equals(attribute)) {
                    uuid = BASE_ATTACK_DAMAGE_ID;
                    this.attackDamage = (float) attributeModifier.getAmount() + this.getTier().getAttackDamageBonus();
                } else if (ATTACK_SPEED.equals(attribute)) {
                    uuid = BASE_ATTACK_SPEED_ID;
                }
                builder.put(attribute, new AttributeModifier(uuid, attributeModifier.getAmount(), attributeModifier.getOperation()));
            }
        });
        this.defaultModifiers = builder.build();
    }

    public MeleeGearConfig getGearConfig() {
        return meleeGearConfig;
    }

    @Override
    public int getComboLength(ItemStack stack, LivingEntity attacker) {
        return this.getGearConfig().getComboLength();
    }

    @Override
    public boolean isUnique() {
        return meleeGearConfig.isUnique();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);
        DescriptionHelper.addFullDescription(list, stack);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return this.getGearConfig().isDisablesShield();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, MojankHelper.hurtEnemyBroadcastBreakEvent());
        return true;
    }

    public float getDamage() {
        return this.attackDamage;
    }

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        if (blockState.getDestroySpeed(level, blockPos) != 0.0F) {
            itemStack.hurtAndBreak(1, livingEntity, MojankHelper.hurtEnemyBroadcastBreakEvent());
        }

        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(Blocks.COBWEB) || state.is(BlockTags.LEAVES);
    }

    @Override
    public float getDestroySpeed(ItemStack p_150893_1_, BlockState p_150893_2_) {
        if (p_150893_2_.is(Blocks.COBWEB) || p_150893_2_.is(BlockTags.LEAVES)) {
            return 15.0F;
        } else {
            return p_150893_2_.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
        }
    }
}
