package com.divingeveryday.beercraft.block;

import com.divingeveryday.beercraft.BeerCraft;
import com.divingeveryday.beercraft.reference.GuiIDs;
import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainRoaster;
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

public class BlockGrainRoaster extends BlockBeerCraftEntityBlock {

    private final boolean isActive;
    public BlockGrainRoaster( boolean isActive ) {
        super( Names.Blocks.GRAIN_ROASTER + (isActive?"_active":"_inactive"), Material.iron, !isActive );
        this.isActive = isActive;
    }

    private static IIcon iconTop;
    private static IIcon iconSide;

    @Override
    public TileEntity createNewTileEntity( World var1, int var2 ) {
        return new TileEntityGrainRoaster();
    }

    public boolean onBlockActivated( World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ ) {
        if( world.isRemote ) {
            return true; // nothing else on the client side.
        } else {
            FMLNetworkHandler.openGui( player, BeerCraft.instance, GuiIDs.GRAIN_ROASTER, world, x, y, z );
            return true;
        }
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public void registerBlockIcons( IIconRegister r ) {
        this.iconTop = r.registerIcon( Textures.RESOURCE_PREFIX + "grain_roaster_top" );
        this.iconSide = r.registerIcon( Textures.RESOURCE_PREFIX + "grain_roaster_side" );
        this.blockIcon = r.registerIcon( Textures.RESOURCE_PREFIX + "grain_roaster_front_" + (this.isActive ? "active" : "inactive") );
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public IIcon getIcon( int side, int meta ) {
        switch( BlockSide.getRotatedSide( side, meta ) ) {
            case north:
                return this.blockIcon;
            case south:
            case east:
            case west:
                return this.iconSide;
            case top:
            default:
                return iconTop; // Todo: Create a bottom image
        }
    }

}
