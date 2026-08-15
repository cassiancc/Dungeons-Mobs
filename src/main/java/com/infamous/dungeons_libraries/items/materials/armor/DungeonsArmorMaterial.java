package com.infamous.dungeons_libraries.items.materials.armor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.core.registries.BuiltInRegistries.ITEM;

public class DungeonsArmorMaterial {

    public static final Codec<DungeonsArmorMaterial> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(iArmorMaterial -> iArmorMaterial.getName()),
            Codec.INT.fieldOf("durability").forGetter(iArmorMaterial -> iArmorMaterial.durability),
            Codec.INT.listOf().fieldOf("damage_reduction_amounts").forGetter(iArmorMaterial -> iArmorMaterial.damageReductionAmounts),
            Codec.INT.fieldOf("enchantability").forGetter(iArmorMaterial -> iArmorMaterial.enchantmentValue()),
            Ingredient.CODEC.fieldOf("repair_item").forGetter(iArmorMaterial -> iArmorMaterial.repairItem.get()),
            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("equip_sound").forGetter(iArmorMaterial -> iArmorMaterial.getEquipSound()),
            Codec.FLOAT.fieldOf("toughness").forGetter(iArmorMaterial -> iArmorMaterial.getToughness()),
            Codec.FLOAT.fieldOf("knockback_resistance").forGetter(iArmorMaterial -> iArmorMaterial.getKnockbackResistance()),
            ArmorMaterialBaseType.CODEC.fieldOf("base_type").forGetter(iArmorMaterial -> iArmorMaterial.baseType)
    ).apply(instance, DungeonsArmorMaterial::new));

	private Integer enchantmentValue() {
		return enchantability;
	}

    // Armor order: boots, leggings, chestplate, helmet
    private static final int[] BASE_DURABILITY_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final SoundEvent equipSound;
    private final int durability;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairItem;
    private final List<Integer> damageReductionAmounts;
    private final float toughness;
    private final float knockbackResistance;
    private final ArmorMaterialBaseType baseType;

    private DungeonsArmorMaterial(String name, int durability, List<Integer> damageReductionAmounts, int enchantability, Ingredient repairItemResourceLocation, SoundEvent equipSound, float toughness, float knockbackResistance, ArmorMaterialBaseType baseType) {
        this.name = name;
        this.equipSound = equipSound;
        this.durability = durability;
        this.enchantability = enchantability;
        this.repairItem = new LazyLoadedValue<>(()-> repairItemResourceLocation);
        this.damageReductionAmounts = damageReductionAmounts;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.baseType = baseType;
    }

    private DungeonsArmorMaterial(String name, int durability, List<Integer> damageReductionAmounts, int enchantability, Supplier<Ingredient> repairItemResourceLocation, SoundEvent equipSound, float toughness, float knockbackResistance, ArmorMaterialBaseType baseType) {
        this.name = name;
        this.equipSound = equipSound;
        this.durability = durability;
        this.enchantability = enchantability;
        this.repairItem = new LazyLoadedValue<>(repairItemResourceLocation);
        this.damageReductionAmounts = damageReductionAmounts;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.baseType = baseType;
    }

    public static DungeonsArmorMaterial of(Holder<ArmorMaterial> materialHolder) {
        var material = materialHolder.value();
        return new DungeonsArmorMaterial(materialHolder.getRegisteredName(), 0, material.defense().values().stream().toList(), material.enchantmentValue(), material.repairIngredient(), material.equipSound().value(), material.toughness(), material.knockbackResistance(), ArmorMaterialBaseType.METAL);
    }

    public int getDefenseForType(ArmorItem.Type slot) {
        return this.damageReductionAmounts.get(slot.getSlot().getIndex());
    }

    public int getDurabilityForType(ArmorItem.Type slot) {
        return BASE_DURABILITY_ARRAY[slot.getSlot().getIndex()] * this.durability;
    }

    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public String getName() {
        return this.name;
    }

    public Ingredient getRepairIngredient() {
        return this.repairItem.get();
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public float getToughness() {
        return this.toughness;
    }

    //getKnockbackResistance
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public ArmorMaterialBaseType getBaseType() {
        return baseType;
    }
}
