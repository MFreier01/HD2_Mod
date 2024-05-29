package net.hytech.helldivers.datagen;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.block.ModBlocks;
import net.hytech.helldivers.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Helldivers.MOD_ID, existingFileHelper);
    }



    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.TERMINID_BLOCK)
                .add(ModBlocks.TERMINID_STONE.get(),
                        ModBlocks.REINFORCED_TERMINID_WALL.get(),
                        ModBlocks.TERMINID_WALL.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TERMINID_STONE.get(),
                        ModBlocks.REINFORCED_TERMINID_WALL.get(),
                        ModBlocks.TERMINID_WALL.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.REINFORCED_TERMINID_WALL.get(),
                        ModBlocks.TERMINID_WALL.get());


    }

}
