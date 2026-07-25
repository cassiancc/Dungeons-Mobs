package com.infamous.dungeons_mobs.mod;

import com.infamous.dungeons_mobs.items.shield.CustomShieldRecipes;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.function.Supplier;

import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZERS, MODID);

    public static final Supplier<RecipeSerializer<?>> SHIELD_RECIPE = RECIPES.register("shield_decoration",
            () -> CustomShieldRecipes.SERIALIZER);
}
