package com.infamous.dungeons_mobs.capabilities;

import com.infamous.dungeons_mobs.capabilities.ancient.Ancient;
import com.infamous.dungeons_mobs.capabilities.animatedprops.AnimatedProps;
import com.infamous.dungeons_mobs.capabilities.convertible.Convertible;
import com.infamous.dungeons_mobs.capabilities.properties.MobProps;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;

public class ModCapabilities {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);

    public static final Supplier<AttachmentType<Ancient>> ANCIENT_CAPABILITY = ATTACHMENT_TYPES.register(
            "ancient", () -> AttachmentType.builder(Ancient::new).build());;
    public static final Supplier<AttachmentType<AnimatedProps>> ANIMATED_PROPS_CAPABILITY = ATTACHMENT_TYPES.register(
            "animated_props", () -> AttachmentType.builder(AnimatedProps::new).build());;
    public static final Supplier<AttachmentType<Convertible>> CONVERTIBLE_CAPABILITY = ATTACHMENT_TYPES.register(
            "convertible", () -> AttachmentType.builder(Convertible::new).build());;
    public static final Supplier<AttachmentType<MobProps>> MOB_PROPS_CAPABILITY = ATTACHMENT_TYPES.register(
            "mob_props", () -> AttachmentType.builder(MobProps::new).build());;
}
