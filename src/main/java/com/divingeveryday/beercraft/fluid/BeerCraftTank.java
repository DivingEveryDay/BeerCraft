package com.divingeveryday.beercraft.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;

public class BeerCraftTank extends FluidTank {

    public int           colorRenderCache = 0xFFFFFF;
    private final String name;

    public BeerCraftTank( String name, int capacity, TileEntity tileEntity ) {
        super( capacity );
        this.name = name;
        this.tile = tileEntity;
    }

    public boolean isEmpty() {
        return getFluid() == null || getFluid().amount <= 0;
    }

    public boolean isFull() {
        return getFluid() != null && getFluid().amount >= getCapacity();
    }

    public Fluid getFluidType() {
        return getFluid() != null ? getFluid().getFluid() : null;
    }

    @Override
    public final NBTTagCompound writeToNBT( NBTTagCompound nbt ) {
        NBTTagCompound tankData = new NBTTagCompound();
        super.writeToNBT( tankData );
        writeTankToNBT( tankData );
        nbt.setTag( name, tankData );
        return nbt;
    }

    @Override
    public final FluidTank readFromNBT( NBTTagCompound nbt ) {
        if( nbt.hasKey( name ) ) {
            NBTTagCompound tankData = nbt.getCompoundTag( name );
            super.readFromNBT( tankData );
            readTankFromNBT( tankData );
        }
        return this;
    }

    public void writeTankToNBT( NBTTagCompound nbt ) {
    }

    public void readTankFromNBT( NBTTagCompound nbt ) {
    }

}
