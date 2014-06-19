package com.divingeveryday.beercraft.client.gui;

import com.divingeveryday.beercraft.block.TileEntityGrainRoaster;
import com.divingeveryday.beercraft.container.ContainerGrainMill;
import com.divingeveryday.beercraft.container.ContainerGrainRoaster;
import com.divingeveryday.beercraft.reference.Textures;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiGrainRoaster extends BeerCraftGuiContainer {

    public GuiGrainRoaster( InventoryPlayer inventoryPlayer, TileEntityGrainRoaster grainRoaster ) {
        super( new ContainerGrainRoaster( inventoryPlayer, grainRoaster ), Textures.GUI_GRAIN_ROASTER );
    }

    @Override
    protected void drawGuiContainerBackgroundLayer( float opacity, int x, int y ) {
        super.drawGuiContainerBackgroundLayer( opacity, x, y );

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        
//        int progress = this.grainMill.getMillingProgressScaled( 24 );
//        this.drawTexturedModalRect( xStart + 79, yStart + 34, 176, 0, progress + 1, 16 );
    }

}
