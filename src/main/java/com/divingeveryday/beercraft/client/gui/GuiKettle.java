package com.divingeveryday.beercraft.client.gui;

import com.divingeveryday.beercraft.container.ContainerBurner;
import com.divingeveryday.beercraft.container.ContainerKettle;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntitySingleTank;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiKettle extends BeerCraftGuiContainer {

    public GuiKettle( InventoryPlayer inventoryPlayer, TileEntitySingleTank kettle ) {
        super( new ContainerKettle( inventoryPlayer, kettle ), Textures.GUI_KETTLE );
    }


}
