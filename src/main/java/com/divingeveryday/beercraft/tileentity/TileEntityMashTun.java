package com.divingeveryday.beercraft.tileentity;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class TileEntityMashTun extends TileEntitySingleTank {
    public static final int TankCapacity = FluidContainerRegistry.BUCKET_VOLUME * 6;
    
    public TileEntityMashTun() {
        super( 2, null, null, null, TankCapacity );
    }

    @Override
    public String getInventoryName() {
        return "machine.mash_tun";
    }

    @Override
    public boolean canFill( ForgeDirection from, Fluid fluid ) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canDrain( ForgeDirection from, Fluid fluid ) {
        // TODO Auto-generated method stub
        return false;
    }

}
