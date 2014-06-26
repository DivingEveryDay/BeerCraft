package com.divingeveryday.beercraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.divingeveryday.beercraft.container.ContainerFermenter;
import com.divingeveryday.beercraft.container.ContainerKettle;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityFermenter;
import com.divingeveryday.beercraft.tileentity.TileEntitySingleTank;

public class GuiFermenter extends BeerCraftGuiContainer {

    public GuiFermenter( InventoryPlayer inventoryPlayer, TileEntityFermenter fermenter ) {
        super( new ContainerFermenter( inventoryPlayer, fermenter ), Textures.GUI_FERMENTER );
    }



}
