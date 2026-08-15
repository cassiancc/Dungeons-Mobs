package com.infamous.dungeons_libraries.attribute;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static com.infamous.dungeons_libraries.attribute.AttributeRegistry.*;

@EventBusSubscriber(modid = MODID)
public class AttributeEvents {

    @SubscribeEvent
    public static void onEntityAttributeModificationEvent(EntityAttributeModificationEvent event) {
        addAttributeToAll(event, FOLLOWER_COST_LIMIT);
        addAttributeToAll(event, SOUL_GATHERING);
        addAttributeToAll(event, SOUL_CAP);
        addAttributeToAll(event, LIFE_STEAL);
        addAttributeToAll(event, RANGED_DAMAGE_MULTIPLIER);
        addAttributeToAll(event, ARTIFACT_COOLDOWN_MULTIPLIER);
        addAttributeToAll(event, MAGIC_DAMAGE_MULTIPLIER);
    }

    private static void addAttributeToAll(EntityAttributeModificationEvent event, Holder<Attribute> attribute) {
        List<EntityType<? extends LivingEntity>> entitiesWithoutAttribute = event.getTypes().stream().filter(entityType -> !event.has(entityType, attribute)).toList();
        entitiesWithoutAttribute.forEach(entityType -> event.add(entityType, attribute, attribute.value().getDefaultValue()));
    }
}
