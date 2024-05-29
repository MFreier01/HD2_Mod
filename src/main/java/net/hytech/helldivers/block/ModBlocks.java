package net.hytech.helldivers.block;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.block.custom.automatoncontactmineBlock;
import net.hytech.helldivers.item.Moditems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraft.world.item.Items.registerBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Helldivers.MOD_ID);


   // public static final RegistryObject<Block> TERMINID_WALL =  registerBlock("terminid_wall",
    //        () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.GRAVEL)));
   public static final RegistryObject<Block> TERMINID_WALL = registerBlock("terminid_wall",
           () -> new automatoncontactmineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> TERMINID_HIVE = registerBlock("terminid_hive",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AZALEA)));
    public static final RegistryObject<Block> TERMINID_STONE = registerBlock("terminid_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.STONE)));
    public static final RegistryObject<Block> REINFORCED_TERMINID_WALL = registerBlock("reinforced_terminid_wall",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.STONE)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return Moditems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
