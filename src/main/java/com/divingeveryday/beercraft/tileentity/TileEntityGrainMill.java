package com.divingeveryday.beercraft.tileentity;

import com.divingeveryday.beercraft.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGrainMill extends TileEntityBeerCraft {
//    private HashMap     millingMap   = new HashMap();
    private static int MILLING_TIME = 60;
    private int         millingTime  = MILLING_TIME;

    public TileEntityGrainMill() {
        super( 2, new int[] { 0 }, new int[] { 1 }, null );

//        this.millingMap.put( new ItemStack( Items.wheat, 1, 0), new ItemStack( ModItems.milledWheat, 1, 0 ) );
//        this.millingMap.put( new ItemStack( ModItems.roastedWheat, 1, 0), new ItemStack( ModItems.milledWheat, 1, 1 ) );
    }

    @Override
    public String getInventoryName() {
        return "machine.grain_mill";
    }

    
    /**
     * @return the millingTime
     */
    public int getMillingTime() {
        return millingTime;
    }

    /**
     * @param millingTime the millingTime to set
     */
    public void setMillingTime( int millingTime ) {
        this.millingTime = millingTime;
    }

    public boolean isMilling() {
        return this.millingTime < MILLING_TIME;
    }

    @SideOnly ( Side.CLIENT)
    public int getMillingProgressScaled( int scale ) {
        int mt = this.millingTime >= this.MILLING_TIME ? 0 : this.millingTime;
        int result = mt * scale / this.MILLING_TIME;
   //     System.out.println( "getMillingProgressScaled:this.millingTime = " + this.millingTime );
        return result;
    }

    public ItemStack getMillingResult( ItemStack sourceItemStack ) {
        Item item = sourceItemStack.getItem();
        if( item == Items.wheat ) {
            return new ItemStack( ModItems.milledWheat, 1, 0 );
        }
        if( item == ModItems.roastedWheat ) {
            int damage = sourceItemStack.getItemDamage();
            return new ItemStack( ModItems.milledWheat, 1, damage+1 );
        }
/*        Object key = sourceItemStack;//.getItem();
        if( this.millingMap.containsKey( key ) ) {
            return ((ItemStack)this.millingMap.get( key )).copy();
        }*/
        return null;
    }

    /**
     * Returns true if the GrainMill can process an item, i.e. has a source
     * item, destination stack isn't full, etc.
     */
    private boolean canMill() {
        ItemStack sourceStack = getStackInSlot( 0 );
        ItemStack destStack = getStackInSlot( 1 );

        if( sourceStack == null ) {
            return false; // nothing there to grind up!
        }
        ItemStack resultStack = getMillingResult( sourceStack );
        if( resultStack == null ) {
            return false;
        }
        if( destStack == null ) {
            return true;
        }
        if( destStack.getItem() == resultStack.getItem() &&
            destStack.getItemDamage() == resultStack.getItemDamage() &&
                destStack.stackSize + resultStack.stackSize <= this.getInventoryStackLimit() ) {
            return true;
        }

        return false;
    }

    /**
     * Turn one item from the source stack into the appropriate milled item in
     * the result stack
     */
    private void millItem() {
        if( this.canMill() ) {
            ItemStack sourceStack = getStackInSlot( 0 );
            ItemStack destStack = getStackInSlot( 1 );
            ItemStack resultStack = getMillingResult( sourceStack );

            if( destStack == null ) {
                this.setInventorySlotContents( 1, resultStack );
            } else {
                destStack.stackSize += 1;
            }
            this.decrStackSize( 0, 1 );
        }
    }

    @Override
    public void updateEntity() {
        boolean dirtyFlag = false;

        if( !this.worldObj.isRemote ) {
            boolean milling = this.millingTime < this.MILLING_TIME;
            if( milling && this.canMill() ) {
                ++this.millingTime;
                if( this.millingTime >= this.MILLING_TIME ) {
                    this.millItem();
                    dirtyFlag = true;
                }
            } else {
                this.millingTime = this.MILLING_TIME;
            }
            if( this.millingTime >= this.MILLING_TIME && this.canMill() ) {
                this.millingTime = 0;
                dirtyFlag = true;
            }
            if( dirtyFlag ) {
                this.markDirty();
            }
        }
    }

}
