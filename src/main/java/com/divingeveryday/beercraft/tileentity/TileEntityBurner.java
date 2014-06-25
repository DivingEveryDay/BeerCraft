package com.divingeveryday.beercraft.tileentity;

import com.divingeveryday.beercraft.block.BlockBurner;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFurnace;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityBurner extends TileEntityBeerCraft {

    private int fullBurnTime;
    private int burnTimeRemaining;
    
    public TileEntityBurner() {
        super( 1, null, new int[] { 1 }, new int[] { 1 } );
    }

    @Override
    public String getInventoryName() {
        return "machine.burner";
    }

    @Override
    public void readFromNBT( NBTTagCompound nbtTagCompound ) {
        super.readFromNBT( nbtTagCompound );
        this.fullBurnTime = nbtTagCompound.getInteger( "fullBurnTime" );
        this.burnTimeRemaining = nbtTagCompound.getInteger( "burnTimeRemaining" );
    }

    @Override
    public void writeToNBT( NBTTagCompound nbtTagCompound ) {
        super.writeToNBT( nbtTagCompound );
        nbtTagCompound.setInteger( "fullBurnTime", this.fullBurnTime );
        nbtTagCompound.setInteger( "burnTimeRemaining", this.burnTimeRemaining );
    }

    public int getInventoryBurnTime() {
        int burnTime = TileEntityFurnace.getItemBurnTime( this.getStackInSlot( 0 ) );
        //System.out.println( "getInventoryBurnTime() = " + burnTime );
        return burnTime;
    }
    
    @SideOnly ( Side.CLIENT)
    public int getBurnProgressScaled( int scale ) {
        if( this.fullBurnTime == 0 )
            return 0;
        
        int bt = this.fullBurnTime - this.burnTimeRemaining;
        int result = this.burnTimeRemaining * scale / this.fullBurnTime;
        return result;
    }

    public void updateEntity() {
        boolean wasBurning = this.burnTimeRemaining > 0;
        boolean isDirty = false;
        
        if( this.burnTimeRemaining > 0 ) {
            this.burnTimeRemaining--;
        }
        
        if( !this.worldObj.isRemote ) {
            if( this.burnTimeRemaining <= 0 && this.getInventoryBurnTime() > 0 ) {
                this.burnTimeRemaining = this.fullBurnTime = this.getInventoryBurnTime();
                this.decrStackSize( 0, 1 );

                isDirty = true;
            }
            
            if( wasBurning != this.burnTimeRemaining > 0 ) {
                BlockBurner.updateBurnerBlockState( this.burnTimeRemaining > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord );
            }
        }
        
        if( isDirty ) {
            this.markDirty();
        }
    }
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return slot == 0 && TileEntityFurnace.getItemBurnTime( itemStack ) > 0;
    }

    public int getBurnTimeRemaining() {
        return this.burnTimeRemaining;
    }

    public void setBurnTimeRemaining( int value ) {
        this.burnTimeRemaining = value;
    }

    public int getFullBurnTime() {
        return this.fullBurnTime;
    }

    public void setFullBurnTime( int value ) {
        this.fullBurnTime = value;
    }
}
