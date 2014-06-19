package com.divingeveryday.beercraft.container;

import com.divingeveryday.beercraft.block.TileEntityGrainRoaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerGrainRoaster extends BeerCraftContainer {

    private final int UPDATE_FUEL_BURN_TIME = 0;
    private final int UPDATE_BURN_TIME_REMAINING = 1;
    private final int UPDATE_ITEM_ROAST_TIME = 2;
    
    private int lastRoastTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerGrainRoaster( InventoryPlayer inventoryPlayer, TileEntityGrainRoaster grainRoaster ) {
        super( inventoryPlayer, grainRoaster );

        this.addSlotToContainer( new Slot( grainRoaster, 0, 56, 17 ) );
        this.addSlotToContainer( new Slot( grainRoaster, 1, 56, 53 ) );
        this.addSlotToContainer( new OutputSlot( grainRoaster, 2, 116, 35 ) );
    }

    public void addCraftingToCrafters( ICrafting par1ICrafting ) {
        super.addCraftingToCrafters( par1ICrafting );
        TileEntityGrainRoaster gr = (TileEntityGrainRoaster)this.tileEntity;
        par1ICrafting.sendProgressBarUpdate( this, 0, gr.fuelBurnTime );
        par1ICrafting.sendProgressBarUpdate( this, 1, gr.roasterBurnTimeRemaining );
        par1ICrafting.sendProgressBarUpdate( this, 2, gr.currentItemRoastTime );
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        TileEntityGrainRoaster gr = (TileEntityGrainRoaster)this.tileEntity;
        for( int i = 0; i < this.crafters.size(); ++i ) {
            ICrafting crafter = (ICrafting)this.crafters.get( i );

            if( this.lastRoastTime != gr.fuelBurnTime ) {
                crafter.sendProgressBarUpdate( this, 0, gr.fuelBurnTime );
            }

            if( this.lastBurnTime != gr.roasterBurnTimeRemaining ) {
                crafter.sendProgressBarUpdate( this, 1, gr.roasterBurnTimeRemaining );
            }

            if( this.lastItemBurnTime != gr.currentItemRoastTime ) {
                crafter.sendProgressBarUpdate( this, 2, gr.currentItemRoastTime );
            }
        }

        this.lastRoastTime = gr.fuelBurnTime;
        this.lastBurnTime = gr.roasterBurnTimeRemaining;
        this.lastItemBurnTime = gr.currentItemRoastTime;
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public void updateProgressBar( int id, int value ) {
        TileEntityGrainRoaster gr = (TileEntityGrainRoaster)this.tileEntity;
        
        switch( id ) {
            case UPDATE_FUEL_BURN_TIME:
                gr.fuelBurnTime = value;
                break;
            case UPDATE_BURN_TIME_REMAINING:
                gr.roasterBurnTimeRemaining = value;
                break;
            case UPDATE_ITEM_ROAST_TIME:
                gr.currentItemRoastTime = value;
                break;
        }
    }

}
