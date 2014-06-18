package com.divingeveryday.beercraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public abstract class BeerCraftGuiContainer extends GuiContainer {

    private ResourceLocation texture;

    public BeerCraftGuiContainer( Container container, ResourceLocation texture ) {
        super( container );
        this.texture = texture;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer( float opacity, int x, int y ) {
        GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );

        this.mc.getTextureManager().bindTexture( this.texture );

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect( xStart, yStart, 0, 0, xSize, ySize );
    }

}
