package com.infamous.dungeons_libraries.utils;

import com.google.common.collect.Multimap;
import com.infamous.dungeons_libraries.items.artifacts.ArtifactItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;
import java.util.Map;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static net.minecraft.world.item.component.ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class DescriptionHelper {


    public static void addArtifactDescription(List<Component> list, ItemStack itemStack) {
        ResourceLocation registryName = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        if (registryName == null) return;
        addLoreDescription(list, registryName);
        addArtifactInfo(list, itemStack);
        addArtifactAttributeInfo(list, itemStack);
    }

    private static void addArtifactAttributeInfo(List<Component> list, ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof ArtifactItem artifactItem)) return;
        Multimap<Holder<Attribute>, AttributeModifier> multimap = artifactItem.getDefaultAttributeModifiers(0);
        if (!multimap.isEmpty()) {
            list.add(CommonComponents.EMPTY);
            list.add(Component.translatable("item.modifiers.artifact").withStyle(ChatFormatting.GRAY));

            for (Map.Entry<Holder<Attribute>, AttributeModifier> entry : multimap.entries()) {
                AttributeModifier attributemodifier = entry.getValue();
                double d0 = attributemodifier.amount();

                double d1;
                if (attributemodifier.operation() != AttributeModifier.Operation.ADD_MULTIPLIED_BASE && attributemodifier.operation() != AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL) {
                    if (entry.getKey().equals(Attributes.KNOCKBACK_RESISTANCE)) {
                        d1 = d0 * 10.0D;
                    } else {
                        d1 = d0;
                    }
                } else {
                    d1 = d0 * 100.0D;
                }

                if (d0 > 0.0D) {
                    list.add(Component.translatable("attribute.modifier.plus." + attributemodifier.operation().id(), ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(entry.getKey().value().getDescriptionId())).withStyle(ChatFormatting.BLUE));
                } else if (d0 < 0.0D) {
                    d1 *= -1.0D;
                    list.add(Component.translatable("attribute.modifier.take." + attributemodifier.operation().id(), ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(entry.getKey().value().getDescriptionId())).withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    public static void addArtifactInfo(List<Component> list, ItemStack itemStack) {
        if (itemStack.getItem() instanceof ArtifactItem) {

            list.add(Component.translatable(
                            "artifact.dungeons_libraries.base")
                    .withStyle(ChatFormatting.DARK_AQUA));

            ResourceLocation registryName = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
            list.add(Component.translatable(
                            "ability." + registryName.getNamespace() + "." + registryName.getPath())
                    .withStyle(ChatFormatting.GREEN));

            ArtifactItem artifactItem = (ArtifactItem) itemStack.getItem();
            int durationInSeconds = artifactItem.getDurationInSeconds();
            int cooldownInSeconds = artifactItem.getCooldownInSeconds();

            if (durationInSeconds > 0) {
                list.add(Component.translatable(
                                "artifact.dungeons_libraries.duration", durationInSeconds)
                        .withStyle(ChatFormatting.BLUE));
            }
            if (cooldownInSeconds > 0) {
                list.add(Component.translatable(
                                "artifact.dungeons_libraries.cooldown", cooldownInSeconds)
                        .withStyle(ChatFormatting.BLUE));
            }
        }
    }

    public static void addFullDescription(List<Component> list, ItemStack itemStack) {
        ResourceLocation registryName = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        addLoreDescription(list, registryName);
    }

    public static void addLoreDescription(List<Component> list, ResourceLocation registryName) {
        list.add(Component.translatable(
                        "lore." + registryName.getNamespace() + "." + registryName.getPath())
                .withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));
    }

    public static void addChargeableDescription(List<Component> list, ItemStack itemStack) {
//        if(itemStack.getItem() instanceof IChargeableItem){
//            IChargeableItem chargeableItem = (IChargeableItem) itemStack.getItem();
//            int chargeTimeInSeconds = chargeableItem.getChargeTimeInSeconds();
//            if(chargeTimeInSeconds > 0) {
//                list.add(Component.translatable(
//                        "artifact.dungeons_gear.charge_time", chargeTimeInSeconds)
//                        .withStyle(ChatFormatting.BLUE));
//            }
//        }
    }
}
