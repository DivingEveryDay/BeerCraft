package com.divingeveryday.beercraft.block;

import com.divingeveryday.beercraft.BeerCraft;
import com.divingeveryday.beercraft.reference.GuiIDs;
import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityMashTun;
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

public class BlockMashTun extends BlockBeerCraftEntityBlock {
    private IIcon  iconTop;
    private IIcon  iconSide;
    private IIcon iconBottom;

    public BlockMashTun() {
        super( Names.Blocks.MASH_TUN, Material.iron );
    }

    @Override
    public TileEntity createNewTileEntity( World var1, int var2 ) {
        return new TileEntityMashTun();
    }
    
    public boolean onBlockActivated( World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ ) {
        if( world.isRemote ) {
            return true; // nothing else on the client side.
        } else {
            FMLNetworkHandler.openGui( player, BeerCraft.instance, GuiIDs.MASH_TUN, world, x, y, z );
            return true;
        }
    }


    @Override
    @SideOnly ( Side.CLIENT)
    public void registerBlockIcons( IIconRegister r ) {
        this.iconTop = r.registerIcon( Textures.RESOURCE_PREFIX + "mash_tun_top" );
        this.iconSide = r.registerIcon( Textures.RESOURCE_PREFIX + "mash_tun_side" );
        this.iconBottom = r.registerIcon( Textures.RESOURCE_PREFIX + "fermenter_bottom" );

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
            case top:
                return iconTop;
            default:
                return iconBottom;
        }
    }

}
