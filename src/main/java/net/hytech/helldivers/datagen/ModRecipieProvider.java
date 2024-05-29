package net.hytech.helldivers.datagen;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.block.ModBlocks;
import net.hytech.helldivers.item.Moditems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipieProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> TERMINID_SMELTABLES = List.of(ModBlocks.TERMINID_HIVE.get());

    public ModRecipieProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, TERMINID_SMELTABLES, RecipeCategory.MISC, Moditems.Raw_E710.get(), 0.25f,160, "terminid");
       oreBlasting(consumer, TERMINID_SMELTABLES, RecipeCategory.MISC, Moditems.Raw_E710.get(), 0.25f,80, "terminid");

        // *NOT WORKING. SOMETHING WRONG WITH THE .define
       // ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REINFORCED_TERMINID_WALL.get())
       //         .pattern("SS")
       //         .pattern("SS")
       //         .define("S", ModBlocks.TERMINID_WALL.get())
       //         .unlockedBy(getHasName(ModBlocks.TERMINID_WALL.get()), has(ModBlocks.TERMINID_WALL.get()))
       //                 .save(consumer);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Helldivers.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }


}
