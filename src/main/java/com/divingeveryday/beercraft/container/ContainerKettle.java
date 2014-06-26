package com.divingeveryday.beercraft.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.divingeveryday.beercraft.tileentity.TileEntityBurner;
import com.divingeveryday.beercraft.tileentity.TileEntitySingleTank;

public class ContainerKettle extends BeerCraftContainer {
    public ContainerKettle( InventoryPlayer inventoryPlayer, TileEntitySingleTank kettle ) {
        super( inventoryPlayer, kettle );

        this.addSlotToContainer( new Slot( kettle, 0, 56, 53 ) );
    }

}
