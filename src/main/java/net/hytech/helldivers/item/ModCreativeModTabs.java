package net.hytech.helldivers.item;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Helldivers.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HELLDIVERS_TAB = CREATIVE_MODE_TABS.register("helldivers_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.Frag_Grenade.get()))
            .title(Component.translatable("creativetab.helldivers_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(Moditems.Frag_Grenade.get());
                        pOutput.accept(Moditems.SAPPHIRE.get());
                        pOutput.accept(ModBlocks.TERMINID_WALL.get());
                        pOutput.accept(ModBlocks.TERMINID_HIVE.get());

                    })
                    .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
