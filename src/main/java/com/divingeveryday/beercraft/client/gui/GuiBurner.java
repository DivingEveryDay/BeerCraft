package com.divingeveryday.beercraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.divingeveryday.beercraft.container.ContainerBurner;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityBurner;

public class GuiBurner extends BeerCraftGuiContainer {

    private TileEntityBurner burner;

    public GuiBurner( InventoryPlayer inventoryPlayer, TileEntityBurner burner ) {
        super( new ContainerBurner( inventoryPlayer, burner ), Textures.GUI_BURNER );

        this.burner = burner;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer( float opacity, int x, int y ) {
        super.drawGuiContainerBackgroundLayer( opacity, x, y );

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        
        int progress = this.burner.getBurnProgressScaled( 12 );
//        System.out.println( progress );
        if( progress > 0 )
            this.drawTexturedModalRect(xStart + 56, yStart + 36 + 12 - progress, 176, 12 - progress, 14, progress + 2);
    }


}























