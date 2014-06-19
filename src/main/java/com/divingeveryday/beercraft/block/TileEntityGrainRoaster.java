package com.divingeveryday.beercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;

import com.divingeveryday.beercraft.tileentity.TileEntityBeerCraft;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGrainRoaster extends TileEntityBeerCraft {

    /**
     * The ItemStacks that hold the items currently being used in the roaster
     */
    private ItemStack[] roasterItemStacks = new ItemStack[3];

    /** The number of ticks that the roaster will keep burning */
    public int          roasterBurnTimeRemaining;
    /**
     * The number of ticks that a fresh copy of the currently-burning fuel would
     * keep the roaster burning for
     */
    public int          fuelBurnTime;
    /** The number of ticks that the current item has been cooking for */
    public int          currentItemRoastTime;

    protected TileEntityGrainRoaster() {
        super( 3, new int[] { 0 }, new int[] { 1 }, new int[] { 2 } );
    }

    @Override
    public String getInventoryName() {
        return "machine.grain_roaster";
    }

    /**
     * Returns an integer between 0 and the passed value representing how close
     * the current item is to being completely cooked
     */
    @SideOnly ( Side.CLIENT)
    public int getCookProgressScaled( int scale ) {
        return this.fuelBurnTime * scale / 200;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much
     * burn time is left on the current fuel item, where 0 means that the item
     * is exhausted and the passed value means that the item is fresh
     */
    @SideOnly ( Side.CLIENT)
    public int getRoastTimeRemainingScaled( int scale ) {
        if( this.currentItemRoastTime == 0 ) {
            this.currentItemRoastTime = 200;
        }

        return this.roasterBurnTimeRemaining * scale / this.currentItemRoastTime;
    }

    /**
     * Furnace isBurning
     */
    public boolean isRoasting() {
        return this.roasterBurnTimeRemaining > 0;
    }

    public void updateEntity() {
        boolean isRoasting = this.roasterBurnTimeRemaining > 0;
        boolean isDirty = false;

        if( this.roasterBurnTimeRemaining > 0 ) {
            --this.roasterBurnTimeRemaining;
        }

        if( !this.worldObj.isRemote ) {
            if( this.roasterBurnTimeRemaining == 0 && this.canRoast() ) {
                this.currentItemRoastTime = this.roasterBurnTimeRemaining = getItemBurnTime( this.roasterItemStacks[1] );

                if( this.roasterBurnTimeRemaining > 0 ) {
                    isDirty = true;

                    if( this.roasterItemStacks[1] != null ) {
                        --this.roasterItemStacks[1].stackSize;

                        if( this.roasterItemStacks[1].stackSize == 0 ) {
                            this.roasterItemStacks[1] = roasterItemStacks[1].getItem().getContainerItem(
                                    roasterItemStacks[1] );
                        }
                    }
                }
            }

            if( this.isRoasting() && this.canRoast() ) {
                ++this.fuelBurnTime;

                if( this.fuelBurnTime == 200 ) {
                    this.fuelBurnTime = 0;
                    this.roastItem();
                    isDirty = true;
                }
            } else {
                this.fuelBurnTime = 0;
            }

            if( isRoasting != this.roasterBurnTimeRemaining > 0 ) {
                isDirty = true;
                BlockFurnace.updateFurnaceBlockState( this.roasterBurnTimeRemaining > 0, this.worldObj, this.xCoord,
                        this.yCoord, this.zCoord );
            }
        }

        if( isDirty ) {
            this.markDirty();
        }
    }

    private static ItemStack getRoastingResult( ItemStack sourceStack ) {
        return null;
    }
    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item,
     * destination stack isn't full, etc.
     */
    private boolean canRoast() {
        if( this.roasterItemStacks[0] == null ) {
            return false;
        } else {
            ItemStack roastResult = getRoastingResult( this.roasterItemStacks[0] );
            if( roastResult == null )
                return false;
            if( this.roasterItemStacks[2] == null )
                return true;
            if( !this.roasterItemStacks[2].isItemEqual( roastResult ) )
                return false;
            int result = roasterItemStacks[2].stackSize + roastResult.stackSize;
            return result <= getInventoryStackLimit() && result <= this.roasterItemStacks[2].getMaxStackSize();
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted
     * item in the furnace result stack
     */
    public void roastItem() {
        if( this.canRoast() ) {
            ItemStack roastResult = getRoastingResult( this.roasterItemStacks[0] );

            if( this.roasterItemStacks[2] == null ) {
                this.roasterItemStacks[2] = roastResult.copy();
            } else if( this.roasterItemStacks[2].isItemEqual( roastResult ) ) {
                this.roasterItemStacks[2].stackSize += roastResult.stackSize; 
            }

            --this.roasterItemStacks[0].stackSize;

            if( this.roasterItemStacks[0].stackSize <= 0 ) {
                this.roasterItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the
     * furnace burning, or 0 if the item isn't fuel
     */
    public static int getItemBurnTime( ItemStack itemStack ) {
        if( itemStack == null ) {
            return 0;
        } else {
            Item item = itemStack.getItem();

            if( item instanceof ItemBlock && Block.getBlockFromItem( item ) != Blocks.air ) {
                Block block = Block.getBlockFromItem( item );

                if( block == Blocks.wooden_slab ) {
                    return 150;
                }

                if( block.getMaterial() == Material.wood ) {
                    return 300;
                }

                if( block == Blocks.coal_block ) {
                    return 16000;
                }
            }

            if( item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals( "WOOD" ) )
                return 200;
            if( item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals( "WOOD" ) )
                return 200;
            if( item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals( "WOOD" ) )
                return 200;
            if( item == Items.stick )
                return 100;
            if( item == Items.coal )
                return 1600;
            if( item == Items.lava_bucket )
                return 20000;
            if( item == Item.getItemFromBlock( Blocks.sapling ) )
                return 100;
            if( item == Items.blaze_rod )
                return 2400;
            return GameRegistry.getFuelValue( itemStack );
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the
     * furnace burning, or 0 if the item isn't fuel
     */
    public static boolean isItemFuel( ItemStack itemStack ) {
        return getItemBurnTime( itemStack ) > 0;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring
     * stack size) into the given slot.
     */
    public boolean isItemValidForSlot( int slot, ItemStack itemStack ) {
        return slot == 2 ? false : (slot == 1 ? isItemFuel( itemStack ) : true);
    }

    /**
     * Returns true if automation can insert the given item in the given slot
     * from the given side. Args: Slot, item, side
     */
    public boolean canInsertItem( int slot, ItemStack itemStack, int side ) {
        return this.isItemValidForSlot( slot, itemStack );
    }

    /**
     * Returns true if automation can extract the given item in the given slot
     * from the given side. Args: Slot, item, side
     */
    public boolean canExtractItem( int par1, ItemStack par2ItemStack, int par3 ) {
        return par3 != 0 || par1 != 1 || par2ItemStack.getItem() == Items.bucket;
    }
}
