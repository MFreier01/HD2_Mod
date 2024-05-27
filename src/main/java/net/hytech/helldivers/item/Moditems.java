package net.hytech.helldivers.item;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.entity.ModEntityTypes;
import net.hytech.helldivers.item.custom.grenadeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class Moditems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Helldivers.MOD_ID);

    //Guns



    //Other Items
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Frag_Grenade = ITEMS.register("frag_grenade",
            () -> new grenadeItem(new Item.Properties()));

    public static final RegistryObject<Item> Raw_E710 = ITEMS.register("raw_e710",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> Scavenger_Spawn_Egg = ITEMS.register("scavenger_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SCAVENGER, 0x057E36, 0x1000000,
                    new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
