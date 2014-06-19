package com.divingeveryday.beercraft.init;

import com.divingeveryday.beercraft.block.BlockBeerCraft;
import com.divingeveryday.beercraft.block.BlockGrainMill;
import com.divingeveryday.beercraft.block.BlockGrainRoaster;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockGrainMill blockGrainMill = new BlockGrainMill();
    public static final BlockBeerCraft blockGrainRoasterActive = new BlockGrainRoaster( true );
    public static final BlockBeerCraft blockGrainRoasterInactive = new BlockGrainRoaster( false );

    public static void init() {
        registerBlock( blockGrainMill );
        registerBlock( blockGrainRoasterActive );
        registerBlock( blockGrainRoasterInactive );
    }

    private static void registerBlock( BlockBeerCraft block ) {
        GameRegistry.registerBlock( block, block.getRegisterName() );
    }

}
