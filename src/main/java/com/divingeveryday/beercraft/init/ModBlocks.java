package com.divingeveryday.beercraft.init;

import net.minecraft.block.Block;

import com.divingeveryday.beercraft.block.BlockBeerCraft;
import com.divingeveryday.beercraft.block.BlockGrainMill;
import com.divingeveryday.beercraft.reference.Names;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockGrainMill blockGrainMill = new BlockGrainMill();

    public static void init() {
        registerBlock( blockGrainMill );
    }

    private static void registerBlock( BlockBeerCraft block ) {
        GameRegistry.registerBlock( block, block.getRegisterName() );
    }

}
