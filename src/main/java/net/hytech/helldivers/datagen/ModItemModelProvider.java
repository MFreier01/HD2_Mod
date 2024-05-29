package net.hytech.helldivers.datagen;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.item.Moditems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Helldivers.MOD_ID, existingFileHelper);
    }




    @Override
    protected void registerModels() {
        simpleItem(Moditems.Raw_E710);
        simpleItem(Moditems.Frag_Grenade);
        simpleItem(Moditems.SAPPHIRE);



    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Helldivers.MOD_ID, "item/" + item.getId().getPath()));
    }
}
