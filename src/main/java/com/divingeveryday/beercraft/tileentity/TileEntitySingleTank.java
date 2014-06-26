package com.divingeveryday.beercraft.tileentity;

import com.divingeveryday.beercraft.fluid.BeerCraftTank;

import buildcraft.api.gates.IAction;
import buildcraft.core.IMachine;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public abstract class TileEntitySingleTank extends TileEntityBeerCraft implements IFluidHandler {

    protected BeerCraftTank tank;

    public TileEntitySingleTank(int stacks, int[] topSlots, int[] bottomSlots, int[] sideSlots, int tankCapacity ) {
        super( stacks, topSlots, bottomSlots, sideSlots );
        this.tank = new BeerCraftTank( "tank", tankCapacity, this );
    }

    @Override
    public void readFromNBT( NBTTagCompound nbtTagCompound ) {
        super.readFromNBT( nbtTagCompound );
        this.tank.readFromNBT( nbtTagCompound );
    }

    @Override
    public void writeToNBT( NBTTagCompound nbtTagCompound ) {
        super.writeToNBT( nbtTagCompound );
        this.tank.writeToNBT( nbtTagCompound );
    }

    @Override
    public int fill( ForgeDirection from, FluidStack resource, boolean doFill ) {
        return tank.fill( resource, doFill );
    }

    @Override
    public FluidStack drain( ForgeDirection from, FluidStack resource, boolean doDrain ) {
        if( resource == null || !resource.isFluidEqual( tank.getFluid() ) ) {
            return null;
        }
        return this.tank.drain( resource.amount, doDrain );
    }

    @Override
    public FluidStack drain( ForgeDirection from, int maxDrain, boolean doDrain ) {
        return this.tank.drain(maxDrain, doDrain);
    }

    @Override
    public abstract boolean canFill( ForgeDirection from, Fluid fluid );

    @Override
    public abstract boolean canDrain( ForgeDirection from, Fluid fluid );

    @Override
    public FluidTankInfo[] getTankInfo( ForgeDirection from ) {
        return new FluidTankInfo[] { this.tank.getInfo() };
    }
}
