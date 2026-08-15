package com.infamous.dungeons_libraries.items.gearconfig;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.infamous.dungeons_libraries.items.interfaces.IRangedWeapon;
import com.infamous.dungeons_libraries.items.interfaces.IReloadableGear;
import com.infamous.dungeons_libraries.items.interfaces.IUniqueGear;
import com.infamous.dungeons_libraries.mixin.ItemAccessor;
import com.infamous.dungeons_libraries.utils.DescriptionHelper;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED;
import static net.minecraft.core.registries.BuiltInRegistries.ATTRIBUTE;

public class BowGear extends BowItem implements IRangedWeapon, IReloadableGear, IUniqueGear {

    private ItemAttributeModifiers defaultModifiers;
    private BowGearConfig bowGearConfig;

    public BowGear(Properties builder) {
        super(builder.durability(384));
        reload();
    }

    @Override
    public void reload() {
        bowGearConfig = BowGearConfigRegistry.getConfig(BuiltInRegistries.ITEM.getKey(this));
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        bowGearConfig.getAttributes().forEach(attributeModifier -> {
            Holder.Reference<Attribute> attribute = ATTRIBUTE.getHolder(attributeModifier.getAttributeResourceLocation()).orElse(null);
            if (attribute != null) {
                builder.add(attribute, new AttributeModifier(GeneralUtil.librariesLoc("Weapon_modifier"), attributeModifier.getAmount(), attributeModifier.getOperation()), EquipmentSlotGroup.HAND);
            }
        });
        this.defaultModifiers = builder.build();
        ((ItemAccessor) this).setMaxDamage(bowGearConfig.getDurability());
    }

    public float getDefaultChargeTime() {
        return this.bowGearConfig.getDefaultChargeTime();
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return defaultModifiers;
    }

    @Override
    public boolean isUnique() {
        return bowGearConfig.isUnique();
    }

    public BowGearConfig getGearConfig() {
        return bowGearConfig;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);
        DescriptionHelper.addFullDescription(list, stack);
    }
}
