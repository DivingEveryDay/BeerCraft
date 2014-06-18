package com.divingeveryday.beercraft.block;

import com.divingeveryday.beercraft.BeerCraft;
import com.divingeveryday.beercraft.creativetab.CreativeTab;
import com.divingeveryday.beercraft.reference.GuiIDs;
import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.reference.Reference;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;
import com.divingeveryday.beercraft.util.BlockSide;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGrainMill extends BlockBeerCraft implements ITileEntityProvider {

    private static IIcon iconTop;
    private static IIcon iconSide;
    private static IIcon iconGears;

    public BlockGrainMill() {
        super( Material.iron );

        setHardness( 5F );
        this.setCreativeTab( CreativeTab.BEERCRAFT_TAB );
    }

    @Override
    public TileEntity createNewTileEntity( World var1, int var2 ) {
        return new TileEntityGrainMill();
    }

    public boolean onBlockActivated( World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ ) {
        System.out.println( "onBlockActivated");
        if( world.isRemote ) {
            return true; // nothing else on the client side.
        } else {
            System.out.println( "onBlockActivated:openGui");
            FMLNetworkHandler.openGui( player, BeerCraft.instance, GuiIDs.GRAIN_MILL, world, x, y, z );
            return true;
        }
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public void registerBlockIcons( IIconRegister r ) {
        iconTop = r.registerIcon( Textures.RESOURCE_PREFIX + "grain_mill_top" );
        iconSide = r.registerIcon( Textures.RESOURCE_PREFIX + "grain_mill_side" );
        iconGears = r.registerIcon( Textures.RESOURCE_PREFIX + "grain_mill_gears" );
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public IIcon getIcon( int side, int meta ) {
        switch( BlockSide.getRotatedSide( side, meta ) ) {
            case top:
                return iconTop;
            case north:
            case south:
                return iconGears;
            case east:
            case west:
                return iconSide;
            default:
                return iconTop; // Todo: Create a bottom image
        }
    }

}
