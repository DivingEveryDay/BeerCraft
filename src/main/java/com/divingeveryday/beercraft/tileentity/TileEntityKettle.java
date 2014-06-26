package com.divingeveryday.beercraft.tileentity;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class TileEntityKettle extends TileEntitySingleTank {
    public static final int TankCapacity = FluidContainerRegistry.BUCKET_VOLUME * 6;
    
    public TileEntityKettle() {
        super( 6, null, null, null, TankCapacity );
    }

    @Override
    public String getInventoryName() {
        return "machine.kettle";
    }

    @Override
    public boolean canFill( ForgeDirection from, Fluid fluid ) {
        return true;
    }

    @Override
    public boolean canDrain( ForgeDirection from, Fluid fluid ) {
        return true;
    }

}
