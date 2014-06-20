package com.divingeveryday.beercraft.init;

import net.minecraftforge.fluids.FluidRegistry;

import buildcraft.energy.BucketHandler;

import com.divingeveryday.beercraft.block.BlockBeerCraftFluid;
import com.divingeveryday.beercraft.block.BlockFluidSimpleBeer;
import com.divingeveryday.beercraft.fluid.FluidBeerCraft;
import com.divingeveryday.beercraft.fluid.FluidSimpleBeer;
import com.divingeveryday.beercraft.item.ItemBeerCraftBucket;
import com.divingeveryday.beercraft.reference.Names;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModFluids {
    public static final FluidBeerCraft simpleBeer = new FluidSimpleBeer();
    public static /*final*/ BlockBeerCraftFluid simpleBeerBlock;// = new BlockFluidSimpleBeer( simpleBeer );
    public static /*final*/ ItemBeerCraftBucket simpleBeerBucket;// = new ItemBeerCraftBucket( simpleBeerBlock );
    
    public static void init() {
        // Simple Beer
        FluidRegistry.registerFluid( simpleBeer );
        simpleBeerBlock = new BlockFluidSimpleBeer( simpleBeer );
        GameRegistry.registerBlock( simpleBeerBlock, simpleBeerBlock.getRegisterName() );
        simpleBeerBucket = new ItemBeerCraftBucket( Names.Items.SIMPLE_BEER_BUCKET, simpleBeerBlock );
        GameRegistry.registerItem( simpleBeerBucket, simpleBeerBucket.getRegisterName() );
        BucketHandler.INSTANCE.buckets.put( simpleBeerBlock, simpleBeerBucket );
    }


}
