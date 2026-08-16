package com.infamous.dungeons_libraries.items.gearconfig;

import com.infamous.dungeons_libraries.items.interfaces.IRangedWeapon;
import com.infamous.dungeons_libraries.items.interfaces.IReloadableGear;
import com.infamous.dungeons_libraries.items.interfaces.IUniqueGear;
import com.infamous.dungeons_libraries.utils.DescriptionHelper;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;

import static net.minecraft.core.registries.BuiltInRegistries.ATTRIBUTE;

public class BowGear extends BowItem implements IRangedWeapon, IReloadableGear, IUniqueGear {

    private ItemAttributeModifiers defaultModifiers;
    private BowGearConfig bowGearConfig;
    private int maxDamage;

    public BowGear(Properties builder) {
        super(builder.durability(384));
        reload();
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return maxDamage;
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
        this.maxDamage = bowGearConfig.getDurability();
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
