package com.divingeveryday.beercraft.block;

import java.util.Random;

import com.divingeveryday.beercraft.init.ModItems;
import com.divingeveryday.beercraft.reference.Names;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class HopsPlant extends BlockBeerCraft {

	protected HopsPlant() {
		super( Names.Blocks.HOPS_PLANT, Material.plants);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F , 1.5F, 1.0F);
		
	}
/*
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x,
            int y, int z) {
        return null;
    }

    @Override
    public int getRenderType () {
    	 
        return 6;
    }

    @Override
    public boolean isOpaqueCube () {
        return false;
    }

//    @Override
//    public int getBlockTextureFromSideAndMetadata (int side, int metadata) {
//        return 32 + metadata;
//    }

    @Override
    public void updateTick (World world, int x, int y, int z, Random random) {
        if (world.getBlockMetadata(x, y, z) == 1) {
            return;
        }

        if (random.nextInt(isFertile(world, x, y - 1, z) ? 12 : 25) != 0) {
            return;
        }

        world.setBlockMetadataWithNotify(x, y, z, 1, 2);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
    }

    @Override
    public boolean canBlockStay (World world, int x, int y, int z) {
        Block soil = world.getBlock(x, y - 1, z);
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world
                .canBlockSeeTheSky(x, y, z))
                && (soil != null && soil.canSustainPlant(world, x, y - 1, z,
                        ForgeDirection.UP, (IPlantable) ModItems.itemHopsSeeds));
    }

    @Override
    public int idDropped (int metadata, Random random, int par2) {
        switch (metadata) {
        case 0:
            return Generic.tomatoSeeds.itemID;
        case 1:
            return Generic.tomatoFruit.itemID;
        default:
            // Error case!
            return -1; // air
        }
    }

    @Override
    public int idPicked (World world, int x, int y, int z) {
        return Generic.tomatoSeeds.itemID;
    }
    */
}
