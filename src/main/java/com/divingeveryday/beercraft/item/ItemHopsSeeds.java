package com.divingeveryday.beercraft.item;

import com.divingeveryday.beercraft.reference.Names;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemHopsSeeds extends ItemBeerCraft implements IPlantable {

    public ItemHopsSeeds() {
        super( Names.Items.HOPS_SEEDS, 64 );
    }

    @Override
    public EnumPlantType getPlantType( IBlockAccess world, int x, int y, int z ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Block getPlant( IBlockAccess world, int x, int y, int z ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getPlantMetadata( IBlockAccess world, int x, int y, int z ) {
        // TODO Auto-generated method stub
        return 0;
    }

}
