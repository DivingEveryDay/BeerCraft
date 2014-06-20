package com.divingeveryday.beercraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import buildcraft.core.CreativeTabBuildCraft;

import com.divingeveryday.beercraft.creativetab.CreativeTab;
import com.divingeveryday.beercraft.reference.Textures;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBeerCraftBucket extends ItemBucket {

    private String registerName;

    public ItemBeerCraftBucket( String registerName, Block fluidBlock ) {
        super( fluidBlock );
        setContainerItem( Items.bucket );
        setCreativeTab( CreativeTab.BEERCRAFT_TAB );
        this.setUnlocalizedName( registerName );
        this.registerName = registerName;
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public void registerIcons( IIconRegister par1IconRegister ) {
        this.itemIcon = par1IconRegister.registerIcon( Textures.RESOURCE_PREFIX + registerName );
    }

    public String getRegisterName() {
        return this.registerName;
    }

    @Override
    public String getUnlocalizedName() {
        return String.format( "item.%s%s", Textures.RESOURCE_PREFIX,
                getUnwrappedUnlocalizedName( super.getUnlocalizedName() ) );
    }

    @Override
    public String getUnlocalizedName( ItemStack itemStack ) {
        return String.format( "item.%s%s", Textures.RESOURCE_PREFIX,
                getUnwrappedUnlocalizedName( super.getUnlocalizedName() ) );
    }

    protected String getUnwrappedUnlocalizedName( String unlocalizedName ) {
        return unlocalizedName.substring( unlocalizedName.indexOf( "." ) + 1 );
    }


}
