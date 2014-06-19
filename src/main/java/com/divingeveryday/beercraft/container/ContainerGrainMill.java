package com.divingeveryday.beercraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerGrainMill extends BeerCraftContainer {

    private int lastMillingTime;

    public ContainerGrainMill( InventoryPlayer inventoryPlayer, TileEntityGrainMill grainMill ) {
        super( inventoryPlayer, grainMill );

        this.addSlotToContainer( new Slot( grainMill, 0, 54, 34 ) );
        this.addSlotToContainer( new OutputSlot( grainMill, 1, 116, 34 ) );

    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or
     * you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot( EntityPlayer player, int slotIndex ) {
        return null;
    }

    @Override
    public void addCraftingToCrafters( ICrafting par1ICrafting ) {
        super.addCraftingToCrafters( par1ICrafting );
        TileEntityGrainMill gm = (TileEntityGrainMill)this.tileEntity;
        par1ICrafting.sendProgressBarUpdate( this, 0, gm.getMillingTime() );
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
                icrafting.sendProgressBarUpdate( this, 0, this.lastMillingTime );
            }
        }

    }

    @SideOnly ( Side.CLIENT)
    @Override
    public void updateProgressBar( int par1, int par2 ) {
        TileEntityGrainMill gm = (TileEntityGrainMill)this.tileEntity;
        if( par1 == 0 ) {
            gm.setMillingTime( par2 );
        }
    }

}
