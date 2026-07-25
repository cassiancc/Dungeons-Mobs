package com.infamous.dungeons_mobs.datagen;

import com.infamous.dungeons_mobs.mod.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static com.infamous.dungeons_libraries.items.ItemTagWrappers.CURIOS_ARTIFACTS;
import static com.infamous.dungeons_mobs.DungeonsMobs.MODID;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        //FIXME
        super(dataGenerator.getPackOutput(), null, blockTagProvider.contentsGetter(), MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        curiosArtifactTags();
    }

    private void curiosArtifactTags() {
        ModItems.ARTIFACTS.forEach((resourceLocation, itemSupplier) -> this.tag(CURIOS_ARTIFACTS).add(itemSupplier.get()));
    }

}
