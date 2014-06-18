package com.divingeveryday.beercraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.divingeveryday.beercraft.creativetab.CreativeTab;
import com.divingeveryday.beercraft.reference.Textures;
import com.divingeveryday.beercraft.util.BlockSide;

public class BlockBeerCraft extends Block {

    protected BlockBeerCraft( Material material ) {
        super( material );
        this.setCreativeTab( CreativeTab.BEERCRAFT_TAB );
    }

    @Override
    public String getUnlocalizedName() {
        return String.format( "tile.%s%s", Textures.RESOURCE_PREFIX,
                getUnwrappedUnlocalizedName( super.getUnlocalizedName() ) );
    }

    protected String getUnwrappedUnlocalizedName( String unlocalizedName ) {
        return unlocalizedName.substring( unlocalizedName.indexOf( "." ) + 1 );
    }

    @Override
    public void onBlockPlacedBy( World world, int i, int j, int k, EntityLivingBase entity, ItemStack stack ) {
        super.onBlockPlacedBy( world, i, j, k, entity, stack );

        // set the metadata to hold the orientation of the block
        world.setBlockMetadataWithNotify( i, j, k, BlockSide.getLookDirection( entity ), 1 );
    }

}
