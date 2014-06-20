package com.divingeveryday.beercraft.client.gui;

import com.divingeveryday.beercraft.container.ContainerGrainMill;
import com.divingeveryday.beercraft.container.ContainerGrainRoaster;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainRoaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiGrainRoaster extends BeerCraftGuiContainer {

    private TileEntityGrainRoaster grainRoaster;

    public GuiGrainRoaster( InventoryPlayer inventoryPlayer, TileEntityGrainRoaster grainRoaster ) {
        super( new ContainerGrainRoaster( inventoryPlayer, grainRoaster ), Textures.GUI_GRAIN_ROASTER );
        this.grainRoaster = grainRoaster;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer( float opacity, int x, int y ) {
        super.drawGuiContainerBackgroundLayer( opacity, x, y );

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        int scaleValue;

        if (this.grainRoaster.isRoasting())
        {
            scaleValue = this.grainRoaster.getRoastTimeRemainingScaled( 12);
            this.drawTexturedModalRect(xStart + 56, yStart + 36 + 12 - scaleValue, 176, 12 - scaleValue, 14, scaleValue + 2);
        }

        scaleValue = this.grainRoaster.getCookProgressScaled( 24);
        this.drawTexturedModalRect(xStart + 79, yStart + 34, 176, 14, scaleValue + 1, 16);
    }

}
