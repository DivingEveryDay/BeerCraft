package com.divingeveryday.beercraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.divingeveryday.beercraft.container.ContainerKettle;
import com.divingeveryday.beercraft.container.ContainerMashTun;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityMashTun;
import com.divingeveryday.beercraft.tileentity.TileEntitySingleTank;

public class GuiMashTun extends BeerCraftGuiContainer {

    public GuiMashTun( InventoryPlayer inventoryPlayer, TileEntityMashTun mashTun ) {
        super( new ContainerMashTun( inventoryPlayer, mashTun ), Textures.GUI_MASH_TUN );
    }



}
