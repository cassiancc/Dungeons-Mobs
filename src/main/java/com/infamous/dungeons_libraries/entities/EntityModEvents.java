package com.infamous.dungeons_libraries.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK;

@EventBusSubscriber(modid = MODID)
public class EntityModEvents {

    @SubscribeEvent
    public static void onEntityAttributeModificationEvent(EntityAttributeModificationEvent event) {
        List<EntityType<? extends LivingEntity>> entitiesWithoutAttack = event.getTypes().stream().filter(entityType -> !event.has(entityType, ATTACK_DAMAGE)).collect(Collectors.toList());
        entitiesWithoutAttack.forEach(entityType -> event.add(entityType, ATTACK_DAMAGE, 0));
        List<EntityType<? extends LivingEntity>> entitiesWithoutKnockback = event.getTypes().stream().filter(entityType -> !event.has(entityType, ATTACK_KNOCKBACK)).collect(Collectors.toList());
        entitiesWithoutKnockback.forEach(entityType -> event.add(entityType, ATTACK_KNOCKBACK, 0));
    }
}
