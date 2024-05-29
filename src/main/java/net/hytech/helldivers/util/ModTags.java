package net.hytech.helldivers.util;

import net.hytech.helldivers.Helldivers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {



    public static class Items {
        public static final TagKey<Item> FUSE_GRENADE = tag("fuse_grenade");


        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(Helldivers.MOD_ID, name));
        }


    }


    public static class Blocks {
        public static final TagKey<Block> TERMINID_BLOCK = tag("terminid_block");

        private static TagKey<Block>tag(String name){
            return BlockTags.create(new ResourceLocation(Helldivers.MOD_ID, name));
        }
    }



}
