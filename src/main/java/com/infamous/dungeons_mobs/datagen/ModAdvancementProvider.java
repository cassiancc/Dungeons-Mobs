package com.infamous.dungeons_mobs.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        //FIXME
        super(generatorIn.getPackOutput(), null, null);
    }

//    @Override
//    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
//        new ModNetherAdvancements().accept(consumer);
//    }
}
