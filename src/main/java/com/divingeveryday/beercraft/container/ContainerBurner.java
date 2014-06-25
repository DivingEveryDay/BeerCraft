package com.divingeveryday.beercraft.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import com.divingeveryday.beercraft.tileentity.TileEntityBurner;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBurner extends BeerCraftContainer {

    private int lastBurnTimeRemaining;
    private int lastFullBurnTime;
    
    private static final int UPDATE_BURN_TIME_REMAINING = 0;
    private static final int UPDATE_FULL_BURN_TIME = 1;

    public ContainerBurner( InventoryPlayer inventoryPlayer, TileEntityBurner burner ) {
        super( inventoryPlayer, burner );

        this.addSlotToContainer( new Slot( burner, 0, 56, 53 ) );
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        TileEntityBurner burner = (TileEntityBurner)this.tileEntity;
        for( int i = 0; i < this.crafters.size(); ++i ) {
            ICrafting icrafting = (ICrafting)this.crafters.get( i );

            if( this.lastBurnTimeRemaining != burner.getBurnTimeRemaining() ) {
                this.lastBurnTimeRemaining = burner.getBurnTimeRemaining();
                icrafting.sendProgressBarUpdate( this, UPDATE_BURN_TIME_REMAINING, this.lastBurnTimeRemaining );
            }
            if( this.lastFullBurnTime != burner.getFullBurnTime() )  {
                this.lastFullBurnTime = burner.getFullBurnTime();
                icrafting.sendProgressBarUpdate( this, UPDATE_FULL_BURN_TIME, this.lastFullBurnTime );
            }
        }

    }

    @SideOnly ( Side.CLIENT)
    @Override
    public void updateProgressBar( int id, int value ) {
        TileEntityBurner burner = (TileEntityBurner)this.tileEntity;
        switch( id ) {
            case UPDATE_BURN_TIME_REMAINING:
                burner.setBurnTimeRemaining( value );
                break;
            case UPDATE_FULL_BURN_TIME:
                burner.setFullBurnTime( value );
                break;
        }
    }


}
