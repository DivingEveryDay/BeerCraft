package com.divingeveryday.beercraft.proxy;

import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy {

    @Override
    public void registerTileEntities() {
        GameRegistry.registerTileEntity( TileEntityGrainMill.class, "tile." + Names.Blocks.GRAIN_MILL );

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
