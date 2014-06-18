package com.divingeveryday.beercraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import com.divingeveryday.beercraft.tileentity.TileEntityBeerCraft;

public class BeerCraftContainer extends Container {

    // Player Inventory
    private final int             PLAYER_INVENTORY_ROWS    = 3;
    private final int             PLAYER_INVENTORY_COLUMNS = 9;

    protected TileEntityBeerCraft tileEntity;

    public BeerCraftContainer( InventoryPlayer inventoryPlayer, TileEntityBeerCraft tileEntity ) {
        this.tileEntity = tileEntity;
        if( tileEntity instanceof IInventory ) {
            ((IInventory)tileEntity).openInventory();
        }

        // Add the player's inventory slots to the container
        for( int row = 0; row < PLAYER_INVENTORY_ROWS; ++row ) {
            for( int column = 0; column < PLAYER_INVENTORY_COLUMNS; ++column ) {
                this.addSlotToContainer( new Slot( inventoryPlayer, column + row * 9 + 9, 8 + column * 18, 84 + row * 18 ) );
            }
        }

        // Add the player's action bar slots to the container
        for( int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex ) {
            this.addSlotToContainer( new Slot( inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142 ) );
        }

    }

    @Override
    public boolean canInteractWith( EntityPlayer player ) {
        return this.tileEntity.isUseableByPlayer( player );
    }

}
