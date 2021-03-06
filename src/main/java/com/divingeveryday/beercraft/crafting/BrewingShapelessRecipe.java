package com.divingeveryday.beercraft.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.divingeveryday.beercraft.init.ModItems;

public class BrewingShapelessRecipe implements IRecipe {
    private int ingredientCount;
    public BrewingShapelessRecipe( int ingredientCount ) {
        this.ingredientCount = ingredientCount;
    }

    @Override
    public boolean matches( InventoryCrafting inventoryCrafting, World var2 ) {
        int foundIngredientCount = 0;
        
        for( int i = 0; i < 3; ++i ) {
            for( int j = 0; j < 3; ++j ) {
                ItemStack itemstack = inventoryCrafting.getStackInRowAndColumn( j, i );

                if( itemstack != null ) {
                    Item item = itemstack.getItem();
                    if( item.equals( ModItems.milledWheat ) || item.equals( ModItems.brewGrains ) ) {
                        foundIngredientCount++;
                    } else {
                        return false;
                    }
                }
            }
        }

        return foundIngredientCount == this.ingredientCount;
    }

    @Override
    public ItemStack getCraftingResult( InventoryCrafting inventoryCrafting ) {
        int[] counts = new int[]{ 0,0,0,0 };
        for( int i=0; i<9; i++ ){
            ItemStack stack = inventoryCrafting.getStackInSlot( i );
            if( stack == null )
                continue;
            Item item = stack.getItem();
            int damage = stack.getItemDamage();
            
            if( item.equals( ModItems.milledWheat ) ) {
                counts[ damage ] = counts[ damage ]+1;
            } else if( item.equals( ModItems.brewGrains ) ) {
                int[] extraCounts = stack.stackTagCompound.getIntArray( "ContentsCount" );
                for( int j=0; j<4; j++ ) {
                    counts[j] = counts[j] + extraCounts[j];
                }
            } else {
                System.out.println( "How did this happen?" );
            }
        }

        ItemStack grains = new ItemStack( ModItems.brewGrains );
        NBTTagCompound stackNBT = new NBTTagCompound();
        stackNBT.setIntArray( "ContentsCount", counts );
        grains.stackTagCompound = stackNBT;
        return grains;
    }

    @Override
    public int getRecipeSize() {
        return this.ingredientCount;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack( ModItems.brewGrains );
    }

}
