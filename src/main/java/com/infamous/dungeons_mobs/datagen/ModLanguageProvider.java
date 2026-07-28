package com.infamous.dungeons_mobs.datagen;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.mod.ModEffects;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.infamous.dungeons_mobs.mod.ModMobEnchants.MOB_ENCHANTS_DEFERRED;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), DungeonsMobs.MODID, locale);
    }

    //TODO Completely improve, getting onto it shortly -- Meme Man
    @Override
    protected void addTranslations() {
        addConfigOptions();
        //addMobEnchantments();
        addTips();
        this.add(ModEffects.ENSNARED.get(), "Ensnared");
        this.add(ModEffects.WARPED.get(), "Warped");
    }

    private void addMobEnchantments() {
        MOB_ENCHANTS_DEFERRED.getEntries().forEach(mobEnchantmentSupplier -> {
            ResourceLocation id = mobEnchantmentSupplier.getId();
            MobEnchant mobEnchant = mobEnchantmentSupplier.get();
            add("mobenchant.enchantwithmob.name.dungeons_mobs." + id.getPath(), getNameFromId(id.getPath()));
        });
    }

    private void addTips() {
    }

    private void addConfigOptions() {
    }

    private String getNameFromId(String idString) {
        StringBuilder sb = new StringBuilder();
        for (String word : idString.toLowerCase().split("_")) {
            sb.append(word.substring(0, 1).toUpperCase());
            sb.append(word.substring(1));
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
