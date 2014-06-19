package com.divingeveryday.beercraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCraftEntityBlock extends BlockBeerCraft {

    protected BlockCraftEntityBlock( String registerName, Material material ) {
        super( registerName, material );
        // TODO Auto-generated constructor stub
    }

    @Override
    public void breakBlock( World world, int x, int y, int z, Block block, int meta ) {
        dropInventory( world, x, y, z );
        super.breakBlock( world, x, y, z, block, meta );
        world.removeTileEntity(  x, y, z );
    }

    protected void dropInventory( World world, int x, int y, int z ) {
        TileEntity tileEntity = world.getTileEntity( x, y, z );

        if( !(tileEntity instanceof IInventory) ) {
            return;
        }

        IInventory inventory = (IInventory)tileEntity;

        for( int i = 0; i < inventory.getSizeInventory(); i++ ) {
            ItemStack itemStack = inventory.getStackInSlot( i );

            if( itemStack != null && itemStack.stackSize > 0 ) {
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem( world, x + dX, y + dY, z + dZ, itemStack.copy() );

                if( itemStack.hasTagCompound() ) {
                    entityItem.getEntityItem().setTagCompound( (NBTTagCompound)itemStack.getTagCompound().copy() );
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld( entityItem );
                itemStack.stackSize = 0;
            }
        }
    }

    public boolean onBlockEventReceived( World world, int x, int y, int z, int eventId, int param ) {
        super.onBlockEventReceived( world, x, y, z, eventId, param );
        TileEntity tileentity = world.getTileEntity( x, y, z );
        return tileentity == null ? false : tileentity.receiveClientEvent( eventId, param );
    }

}
