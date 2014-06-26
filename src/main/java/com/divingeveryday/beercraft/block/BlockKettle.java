package com.divingeveryday.beercraft.block;

import com.divingeveryday.beercraft.BeerCraft;
import com.divingeveryday.beercraft.reference.GuiIDs;
import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityKettle;
import com.divingeveryday.beercraft.tileentity.TileEntitySingleTank;
import com.divingeveryday.beercraft.util.BlockSide;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockKettle extends BlockBeerCraftEntityBlock {
    private IIcon  iconTopBottom;
    private IIcon  iconSide;

    public BlockKettle(  ) {
        super( Names.Blocks.KETTLE, Material.iron );
//        this.setBlockTextureName(Textures.RESOURCE_PREFIX + Names.Blocks.KETTLE);
    }

    @Override
    public TileEntity createNewTileEntity( World var1, int var2 ) {
        return new TileEntityKettle();
    }

    public boolean onBlockActivated( World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ ) {
        if( world.isRemote ) {
            return true; // nothing else on the client side.
        } else {
            FMLNetworkHandler.openGui( player, BeerCraft.instance, GuiIDs.KETTLE, world, x, y, z );
            return true;
        }
    }


    @Override
    @SideOnly ( Side.CLIENT)
    public void registerBlockIcons( IIconRegister r ) {
        this.iconTopBottom = r.registerIcon( Textures.RESOURCE_PREFIX + "kettle_top" );
        this.iconSide = r.registerIcon( Textures.RESOURCE_PREFIX + "kettle_side" );
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

}
