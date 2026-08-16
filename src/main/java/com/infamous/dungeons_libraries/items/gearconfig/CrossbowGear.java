package com.infamous.dungeons_libraries.items.gearconfig;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.infamous.dungeons_libraries.event.CrossbowEvent;
import com.infamous.dungeons_libraries.items.interfaces.IRangedWeapon;
import com.infamous.dungeons_libraries.items.interfaces.IReloadableGear;
import com.infamous.dungeons_libraries.items.interfaces.IUniqueGear;
import com.infamous.dungeons_libraries.mixin.CrossbowItemInvoker;
import com.infamous.dungeons_libraries.utils.DescriptionHelper;
import com.infamous.dungeons_libraries.utils.EnchantmentUtil;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.core.registries.BuiltInRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED;
import static net.minecraft.core.registries.BuiltInRegistries.ATTRIBUTE;

public class CrossbowGear extends CrossbowItem implements IRangedWeapon, IReloadableGear, IUniqueGear {
    private ItemAttributeModifiers defaultModifiers;
    private BowGearConfig crossbowGearConfig;
    private int maxDamage;

    public CrossbowGear(Properties builder) {
        super(builder.durability(384));
        reload();
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return maxDamage;
    }

    @Override
    public void reload() {
        crossbowGearConfig = CrossbowGearConfigRegistry.getConfig(BuiltInRegistries.ITEM.getKey(this));
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        crossbowGearConfig.getAttributes().forEach(attributeModifier -> {
			ATTRIBUTE.getHolder(attributeModifier.getAttributeResourceLocation()).ifPresent(attribute -> builder.add(attribute, new AttributeModifier(GeneralUtil.librariesLoc("weapon_modifier"), attributeModifier.getAmount(), attributeModifier.getOperation()), EquipmentSlotGroup.HAND));
		});
        this.defaultModifiers = builder.build();
        this.maxDamage = crossbowGearConfig.getDurability();
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return defaultModifiers;
    }

    public float getDefaultChargeTime() {
        return this.crossbowGearConfig.getDefaultChargeTime();
    }

    @Override
    public void onUseTick(Level world, LivingEntity livingEntity, ItemStack stack, int timeLeft) {
        if (!world.isClientSide) {
            int quickChargeLevel = EnchantmentUtil.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack, world);

            CrossbowItemInvoker crossbowItemInvoker = (CrossbowItemInvoker) this;
            List<ChargingSounds> quickChargeSoundEvent = stack.get(EnchantmentEffectComponents.CROSSBOW_CHARGING_SOUNDS);
            SoundEvent loadingMiddleSoundEvent = quickChargeLevel == 0 ? SoundEvents.CROSSBOW_LOADING_MIDDLE.value() : null;
            float chargeTime = (float) (stack.getUseDuration(livingEntity) - timeLeft) / this.getCrossbowChargeTime(livingEntity, stack);
            if (chargeTime < 0.2F) {
                crossbowItemInvoker.setStartSoundPlayed(false);
                crossbowItemInvoker.setMidLoadSoundPlayed(false);
            }

            if (chargeTime >= 0.2F && !crossbowItemInvoker.getStartSoundPlayed() && chargeTime < 1.0F) {
                crossbowItemInvoker.setStartSoundPlayed(true);
                world.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), quickChargeSoundEvent.getFirst().start().get(), SoundSource.PLAYERS, 0.5F, 1.0F);
            }

            if (chargeTime >= 0.5F && loadingMiddleSoundEvent != null && !crossbowItemInvoker.getMidLoadSoundPlayed() && chargeTime < 1.0F) {
                crossbowItemInvoker.setMidLoadSoundPlayed(true);
                world.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), loadingMiddleSoundEvent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }

    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity livingEntity, int timeLeft) {
        float chargeTime = getCrossbowChargeTime(livingEntity, stack) + 3 - timeLeft;
        float getCharge = this.getCrossbowCharge(livingEntity, chargeTime, stack);
        // Call to CrossbowItem.tryLoadProjectiles must be in-line as it modifies NBT without the previous checks
        // Do not refactor as a variable preceding this if statement
        if (getCharge >= 1.0F && !isCharged(stack) && CrossbowItemInvoker.callTryLoadProjectiles(livingEntity, stack)) {
            SoundSource soundSource = livingEntity instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            worldIn.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundSource, 1.0F, 1.0F / (livingEntity.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }

    }

    public float getCrossbowCharge(LivingEntity livingEntity, float useTime, ItemStack stack) {
        float crossbowChargeTime = this.getCrossbowChargeTime(livingEntity, stack);
        float charge = useTime / crossbowChargeTime;
        if (charge > 1.0F) {
            charge = 1.0F;
        }

        return charge;
    }

    public float getCrossbowChargeTime(@Nullable LivingEntity livingEntity, ItemStack stack) {
        int quickChargeLevel = EnchantmentUtil.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack, livingEntity.level());
        float minTime = 1;
        CrossbowEvent.ChargeTime event = new CrossbowEvent.ChargeTime(livingEntity, stack, this.getDefaultChargeTime());
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(event);
        return Math.max(event.getChargeTime() - 5 * quickChargeLevel, minTime);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return (int) getCrossbowChargeTime(entity, stack) + 3;
    }

    @Override
    public boolean useOnRelease(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isUnique() {
        return this.crossbowGearConfig.isUnique();
    }

    public BowGearConfig getGearConfig() {
        return crossbowGearConfig;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);
        DescriptionHelper.addFullDescription(list, stack);
    }
}
