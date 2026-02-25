package com.infamous.dungeons_libraries.items.gearconfig;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.infamous.dungeons_libraries.DungeonsLibraries;
import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearRenderer;
import com.infamous.dungeons_libraries.items.interfaces.IArmor;
import com.infamous.dungeons_libraries.items.interfaces.IReloadableGear;
import com.infamous.dungeons_libraries.items.interfaces.IUniqueGear;
import com.infamous.dungeons_libraries.mixin.ArmorItemAccessor;
import com.infamous.dungeons_libraries.mixin.ItemAccessor;
import com.infamous.dungeons_libraries.utils.DescriptionHelper;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static java.util.UUID.randomUUID;
import static net.minecraft.world.item.ArmorMaterials.CHAIN;
import static net.minecraftforge.registries.ForgeRegistries.ATTRIBUTES;

public class ArmorGear extends ArmorItem implements GeoItem, IReloadableGear, IArmor, IUniqueGear, GeoAnimatable {
    private static final ResourceLocation DEFAULT_ARMOR_ANIMATIONS = GeneralUtil.loc(DungeonsLibraries.MODID, "animations/armor/armor_default.animation.json");
    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    private ArmorGearConfig armorGearConfig;
    private final ResourceLocation armorSet;
    private final ResourceLocation modelLocation;
    private final ResourceLocation textureLocation;
    private final ResourceLocation animationFileLocation;

    public ArmorGear(ArmorItem.Type slotType, Properties properties, ResourceLocation armorSet, ResourceLocation modelLocation, ResourceLocation textureLocation, ResourceLocation animationFileLocation) {
        super(CHAIN, slotType, properties);
        this.armorSet = armorSet;
        this.modelLocation = modelLocation;
        this.textureLocation = textureLocation;
        this.animationFileLocation = animationFileLocation;
        reload();
    }

    @Override
    public void reload() {
        armorGearConfig = ArmorGearConfigRegistry.getConfig(this.armorSet);
        if (armorGearConfig == ArmorGearConfig.DEFAULT) {
            armorGearConfig = ArmorGearConfigRegistry.getConfig(ForgeRegistries.ITEMS.getKey(this));
        }
        ArmorMaterial material = armorGearConfig.getArmorMaterial();
        ((ArmorItemAccessor) this).setMaterial(material);
        ((ArmorItemAccessor) this).setDefense(material.getDefenseForType(this.type));
        ((ArmorItemAccessor) this).setToughness(material.getToughness());
        ((ArmorItemAccessor) this).setKnockbackResistance(material.getKnockbackResistance());
        ((ItemAccessor) this).setMaxDamage(material.getDefenseForType(this.type));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID primaryUuid = ARMOR_MODIFIER_UUID_PER_SLOT[this.type.getSlot().getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(primaryUuid, "Armor modifier", material.getDefenseForType(this.type), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(primaryUuid, "Armor toughness", material.getToughness(), AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(primaryUuid, "Armor knockback resistance", this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        armorGearConfig.getAttributes().forEach(attributeModifier -> {
            Attribute attribute = ATTRIBUTES.getValue(attributeModifier.getAttributeResourceLocation());
            if (attribute != null) {
                UUID uuid = randomUUID();
                builder.put(attribute, new AttributeModifier(uuid, "Armor modifier", attributeModifier.getAmount(), attributeModifier.getOperation()));
            }
        });
        this.defaultModifiers = builder.build();
    }

    public ArmorGearConfig getGearConfig() {
        return armorGearConfig;
    }

    @Override
    public boolean isUnique() {
        return armorGearConfig.isUnique();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }


    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        if (armorSet != null) {
            DescriptionHelper.addLoreDescription(list, armorSet);
        } else {
            DescriptionHelper.addLoreDescription(list, ForgeRegistries.ITEMS.getKey(this));
        }
    }


    @Override
    public Rarity getRarity(ItemStack pStack) {
        return getGearConfig().getRarity();
    }

    public ResourceLocation getArmorSet() {
        return armorSet;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private <P extends GeoAnimatable> PlayState predicate(AnimationState<P> event) {
        event.getController().setAnimation(RawAnimation.begin().thenPlay("idle"));
        return PlayState.CONTINUE;
    }

    public ResourceLocation getModelLocation() {
        return modelLocation;
    }

    public ResourceLocation getTextureLocation() {
        return textureLocation;
    }

    public ResourceLocation getAnimationFileLocation() {
        return animationFileLocation;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new ArmorGearRenderer<>(livingEntity);

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller",
                20, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}