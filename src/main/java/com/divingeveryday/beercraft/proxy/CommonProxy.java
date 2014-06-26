package com.divingeveryday.beercraft.proxy;

import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.tileentity.TileEntityBurner;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainRoaster;
import com.divingeveryday.beercraft.tileentity.TileEntityKettle;
import com.divingeveryday.beercraft.tileentity.TileEntitySingleTank;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy {

    @Override
    public void registerTileEntities() {
        GameRegistry.registerTileEntity( TileEntityGrainMill.class, "tile." + Names.Blocks.GRAIN_MILL );
        GameRegistry.registerTileEntity( TileEntityGrainRoaster.class, "tile." + Names.Blocks.GRAIN_ROASTER );
        GameRegistry.registerTileEntity( TileEntityBurner.class, "tile." + Names.Blocks.BURNER );
        GameRegistry.registerTileEntity( TileEntityKettle.class, "tile." + Names.Blocks.KETTLE );
        
    }

    @Override
    public void initRenderingAndTextures() {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerEventHandlers() {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerKeybindings() {
        // TODO Auto-generated method stub

    }

}
