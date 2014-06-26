package com.divingeveryday.beercraft.block;

import java.util.Random;

import com.divingeveryday.beercraft.BeerCraft;
import com.divingeveryday.beercraft.init.ModBlocks;
import com.divingeveryday.beercraft.reference.GuiIDs;
import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityBurner;
import com.divingeveryday.beercraft.util.BlockSide;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBurner extends BlockBeerCraftEntityBlock {
    private final boolean isActive;
    private IIcon  iconTopBottom;
    private IIcon  iconSide;
    private static boolean WhatIsThisUsedFor; // used in updateXXBlockState from BlockFurnace

    public BlockBurner( boolean isActive ) {
        super( Names.Blocks.BURNER + (isActive ? "_active" : "_inactive"), Material.iron, !isActive );
        this.isActive = isActive;
    }

    @Override
    public TileEntity createNewTileEntity( World var1, int var2 ) {
        return new TileEntityBurner();
    }

    public boolean onBlockActivated( World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ ) {
        if( world.isRemote ) {
            return true; // nothing else on the client side.
        } else {
            FMLNetworkHandler.openGui( player, BeerCraft.instance, GuiIDs.BURNER, world, x, y, z );
            return true;
        }
    }

    public Item getItemDropped( int p_149650_1_, Random p_149650_2_, int p_149650_3_ ) {
        return Item.getItemFromBlock( ModBlocks.blockBurnerInactive );
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public void registerBlockIcons( IIconRegister r ) {
        this.iconTopBottom = r.registerIcon( Textures.RESOURCE_PREFIX + "burner_top" );
        this.iconSide = r.registerIcon( Textures.RESOURCE_PREFIX + "burner_side_"
                + (this.isActive ? "active" : "inactive") );
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public IIcon getIcon( int side, int meta ) {
        //System.out.println( this.iconSide.getIconName() );
        switch( BlockSide.getRotatedSide( side, meta ) ) {
            case north:
            case south:
            case east:
            case west:
                return iconSide;
            default:
                return iconTopBottom;
        }
    }

    /**
     * Update which block the burner is using depending on whether or not it is
     * burning
     */
    public static void updateBurnerBlockState( boolean isActive, World world, int x, int y, int z ) {
    //    System.out.println( "updateFurnaceBlockState( " + isActive + ", ... )" );
        int meta = world.getBlockMetadata( x, y, z );
        TileEntity tileentity = world.getTileEntity( x, y, z );
        WhatIsThisUsedFor = true;

        if( isActive ) {
            world.setBlock( x, y, z, ModBlocks.blockBurnerActive );
        } else {
            world.setBlock( x, y, z, ModBlocks.blockBurnerInactive );
        }

        WhatIsThisUsedFor = false;
        world.setBlockMetadataWithNotify( x, y, z, meta, 2 );

        if( tileentity != null ) {
            tileentity.validate();
            world.setTileEntity( x, y, z, tileentity );
        }
    }

}
