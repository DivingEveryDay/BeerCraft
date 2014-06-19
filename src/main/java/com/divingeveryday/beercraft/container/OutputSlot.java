package com.divingeveryday.beercraft.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class OutputSlot extends Slot {

    public OutputSlot( IInventory inventory, int par2, int par3, int par4 ) {
        super( inventory, par2, par3, par4 );
    }

    /**
     * Check if the stack is a valid item for this slot. Always false
     */
    public boolean isItemValid( ItemStack itemStack ) {
        return false;
    }

    //ToDo: add some kinda XP like FurnaceSlot does.
}
