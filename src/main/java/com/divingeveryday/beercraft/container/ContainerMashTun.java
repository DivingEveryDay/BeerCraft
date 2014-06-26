package com.divingeveryday.beercraft.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.divingeveryday.beercraft.tileentity.TileEntityMashTun;
import com.divingeveryday.beercraft.tileentity.TileEntitySingleTank;

public class ContainerMashTun extends BeerCraftContainer {
    public ContainerMashTun( InventoryPlayer inventoryPlayer, TileEntityMashTun mashTun ) {
        super( inventoryPlayer, mashTun );

//        this.addSlotToContainer( new Slot( mashTun, 0, 56, 53 ) );
    }

}
