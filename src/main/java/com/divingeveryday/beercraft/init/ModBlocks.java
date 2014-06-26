package com.divingeveryday.beercraft.init;

import com.divingeveryday.beercraft.block.BlockBeerCraft;
import com.divingeveryday.beercraft.block.BlockBurner;
import com.divingeveryday.beercraft.block.BlockFermenter;
import com.divingeveryday.beercraft.block.BlockGrainMill;
import com.divingeveryday.beercraft.block.BlockGrainRoaster;
import com.divingeveryday.beercraft.block.BlockKettle;
import com.divingeveryday.beercraft.block.BlockMashTun;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockGrainMill blockGrainMill = new BlockGrainMill();
    public static final BlockBeerCraft blockGrainRoasterActive = new BlockGrainRoaster( true );
    public static final BlockBeerCraft blockGrainRoasterInactive = new BlockGrainRoaster( false );
    public static final BlockBeerCraft blockBurnerActive = new BlockBurner( true );
    public static final BlockBeerCraft blockBurnerInactive = new BlockBurner( false );
    public static final BlockBeerCraft blockKettle = new BlockKettle();
    public static final BlockBeerCraft blockMashTun = new BlockMashTun();
    public static final BlockBeerCraft blockFermenter = new BlockFermenter();

    public static void init() {
        registerBlock( blockGrainMill );
        registerBlock( blockGrainRoasterActive );
        registerBlock( blockGrainRoasterInactive );
        registerBlock( blockBurnerActive );
        registerBlock( blockBurnerInactive );
        registerBlock( blockKettle );
        registerBlock( blockMashTun );
        registerBlock( blockFermenter );
    }

    private static void registerBlock( BlockBeerCraft block ) {
        GameRegistry.registerBlock( block, block.getRegisterName() );
    }

}
