package com.divingeveryday.beercraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.divingeveryday.beercraft.container.ContainerGrainMill;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;

public class GuiGrainMill extends BeerCraftGuiContainer {

    private TileEntityGrainMill grainMill;

    public GuiGrainMill( InventoryPlayer inventoryPlayer, TileEntityGrainMill grainMill ) {
        super( new ContainerGrainMill( inventoryPlayer, grainMill ), Textures.GUI_GRAIN_MILL );

        this.grainMill = grainMill;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer( float opacity, int x, int y ) {
        super.drawGuiContainerBackgroundLayer( opacity, x, y );

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        
        int progress = this.grainMill.getMillingProgressScaled( 24 );
        this.drawTexturedModalRect( xStart + 79, yStart + 34, 176, 0, progress + 1, 16 );
    }

}
