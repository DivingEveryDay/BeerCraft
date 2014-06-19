package com.divingeveryday.beercraft.init;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.MinecraftForge;

import com.divingeveryday.beercraft.crafting.BrewingShapelessRecipe;

public class ModRecipies {
    public static final BrewingShapelessRecipe recipe2 = new BrewingShapelessRecipe( 2 );
    public static final BrewingShapelessRecipe recipe3 = new BrewingShapelessRecipe( 3 );
    public static final BrewingShapelessRecipe recipe4 = new BrewingShapelessRecipe( 4 );
    public static final BrewingShapelessRecipe recipe5 = new BrewingShapelessRecipe( 5 );
    public static final BrewingShapelessRecipe recipe6 = new BrewingShapelessRecipe( 6 );
    public static final BrewingShapelessRecipe recipe7 = new BrewingShapelessRecipe( 7 );
    public static final BrewingShapelessRecipe recipe8 = new BrewingShapelessRecipe( 8 );
    public static final BrewingShapelessRecipe recipe9 = new BrewingShapelessRecipe( 9 );

    public static void init() {
        List recipeList = CraftingManager.getInstance().getRecipeList();
        recipeList.add( recipe2 );
        recipeList.add( recipe3 );
        recipeList.add( recipe4 );
        recipeList.add( recipe5 );
        recipeList.add( recipe6 );
        recipeList.add( recipe7 );
        recipeList.add( recipe8 );
        recipeList.add( recipe9 );
    }

}
