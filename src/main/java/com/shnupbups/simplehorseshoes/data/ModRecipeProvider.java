package com.shnupbups.simplehorseshoes.data;

import com.shnupbups.simplehorseshoes.register.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerHorseshoeRecipe(exporter, Items.IRON_INGOT, Items.IRON_NUGGET, ModItems.IRON_HORSESHOE);
        offerHorseshoeRecipe(exporter, Items.GOLD_INGOT, Items.GOLD_NUGGET, ModItems.GOLDEN_HORSESHOE);
        offerHorseshoeRecipe(exporter, Items.DIAMOND, Items.DIAMOND, ModItems.DIAMOND_HORSESHOE);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_HORSESHOE, RecipeCategory.COMBAT, ModItems.NETHERITE_HORSESHOE);
        offerSalvageHorseshoeRecipe(exporter, ModItems.IRON_HORSESHOE, Items.IRON_NUGGET);
        offerSalvageHorseshoeRecipe(exporter, ModItems.GOLDEN_HORSESHOE, Items.GOLD_NUGGET);
    }

    public static void offerHorseshoeRecipe(RecipeExporter exporter, ItemConvertible base, ItemConvertible tip, Item result) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
            .pattern("t t")
            .pattern("b b")
            .pattern("bbb")
            .input('t', tip)
            .input('b', base)
            .criterion("has_base", conditionsFromItem(base))
            .offerTo(exporter);
    }

    public static void offerSalvageHorseshoeRecipe(RecipeExporter exporter, ItemConvertible horseshoe, Item result) {
        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(horseshoe),
                RecipeCategory.MISC,
                result,
                0.1F,
                200
            )
            .criterion("has_horseshoe", conditionsFromItem(horseshoe))
            .offerTo(exporter, getSmeltingItemPath(result)+"_horseshoe");
    }
}
