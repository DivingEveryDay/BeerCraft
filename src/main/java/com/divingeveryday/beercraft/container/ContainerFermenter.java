package com.divingeveryday.beercraft.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.divingeveryday.beercraft.tileentity.TileEntityFermenter;
import com.divingeveryday.beercraft.tileentity.TileEntitySingleTank;

public class ContainerFermenter extends BeerCraftContainer {
    public ContainerFermenter( InventoryPlayer inventoryPlayer, TileEntityFermenter fermenter ) {
        super( inventoryPlayer, fermenter );

//        this.addSlotToContainer( new Slot( fermenter, 0, 56, 53 ) );
    }

}
