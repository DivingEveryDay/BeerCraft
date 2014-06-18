package com.divingeveryday.beercraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityBeerCraft extends TileEntity implements ISidedInventory {

    private int[]       slotsTop;
    private int[]       slotsBottom;
    private int[]       slotsSides;

    private ItemStack[] inventoryItemStacks;

    protected TileEntityBeerCraft( int stacks, int[] topSlots, int[] bottomSlots, int[] sideSlots ) {
        this.inventoryItemStacks = new ItemStack[stacks];
        this.slotsTop = topSlots;
        this.slotsBottom = bottomSlots;
        this.slotsSides = sideSlots;
    }

    @Override
    public void readFromNBT( NBTTagCompound nbtTagCompound ) {
        super.readFromNBT( nbtTagCompound );

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items", 10);
        inventoryItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventoryItemStacks.length)
            {
                inventoryItemStacks[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT( NBTTagCompound nbtTagCompound ) {
        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventoryItemStacks.length; ++currentIndex)
        {
            if (inventoryItemStacks[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventoryItemStacks[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.inventoryItemStacks.length;
    }

    /**
     * Returns the stack in slot slotIndex
     */
    @Override
    public ItemStack getStackInSlot( int slotIndex ) {
    //    assert slotIndex < this.inventoryItemStacks.length;
        return this.inventoryItemStacks[slotIndex];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number
     * (second arg) of items and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize( int slotIndex, int decrementAmount ) {
        ItemStack itemStack = getStackInSlot( slotIndex );
        if( itemStack != null ) {
            if( itemStack.stackSize <= decrementAmount ) {
                setInventorySlotContents( slotIndex, null );
            } else {
                itemStack = itemStack.splitStack( decrementAmount );
                if( itemStack.stackSize == 0 ) {
                    setInventorySlotContents( slotIndex, null );
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing( int slotIndex ) {
        if( this.inventoryItemStacks[slotIndex] != null ) {
            ItemStack itemStack = this.inventoryItemStacks[slotIndex];
            this.inventoryItemStacks[slotIndex] = null;
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents( int slotIndex, ItemStack itemStack ) {
        this.inventoryItemStacks[slotIndex] = itemStack;

        if( itemStack != null && itemStack.stackSize > this.getInventoryStackLimit() ) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public boolean hasCustomInventoryName() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer( EntityPlayer player ) {
        return true;
    }

    @Override
    public void openInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */ 
    @Override
    public boolean isItemValidForSlot( int slotIndex, ItemStack itemStack ) {
        return false;
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    @Override
    public int[] getAccessibleSlotsFromSide( int var1 ) {
        return var1 == 0 ? slotsBottom : (var1 == 1 ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canInsertItem( int slot, ItemStack stack, int side ) {
        return this.isItemValidForSlot(slot, stack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
     @Override
    public boolean canExtractItem( int slot, ItemStack stack, int side ) {
        return false;
    }

}
