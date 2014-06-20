package com.divingeveryday.beercraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerGrainMill extends BeerCraftContainer {

    private int lastMillingTime;
    
    private final int UPDATE_MILLING_TIME = 0;

    public ContainerGrainMill( InventoryPlayer inventoryPlayer, TileEntityGrainMill grainMill ) {
        super( inventoryPlayer, grainMill );

        this.addSlotToContainer( new Slot( grainMill, 0, 54, 34 ) );
        this.addSlotToContainer( new OutputSlot( grainMill, 1, 116, 35 ) );
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        TileEntityGrainMill gm = (TileEntityGrainMill)this.tileEntity;
        for( int i = 0; i < this.crafters.size(); ++i ) {
            ICrafting icrafting = (ICrafting)this.crafters.get( i );

            if( this.lastMillingTime != gm.getMillingTime() ) {
                this.lastMillingTime = gm.getMillingTime();
                icrafting.sendProgressBarUpdate( this, UPDATE_MILLING_TIME, this.lastMillingTime );
            }
        }

    }

    @SideOnly ( Side.CLIENT)
    @Override
    public void updateProgressBar( int id, int value ) {
        TileEntityGrainMill gm = (TileEntityGrainMill)this.tileEntity;
        switch( id ) {
            case UPDATE_MILLING_TIME:
                gm.setMillingTime( value );
                break;
        }
    }

}
