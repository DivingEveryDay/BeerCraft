package com.divingeveryday.beercraft.item;

import com.divingeveryday.beercraft.creativetab.CreativeTab;
import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.reference.Reference;
import com.divingeveryday.beercraft.reference.Textures;

import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/*
 * Base class for items added by BeerCraft
 */
public abstract class ItemBeerCraft extends Item {
    private String registerName;
    public ItemBeerCraft( String unlocalizedName, int stackSize, boolean onCreativeTab ) {
        super();
        this.maxStackSize = stackSize;
        if( onCreativeTab )
            this.setCreativeTab( CreativeTab.BEERCRAFT_TAB );
        this.setNoRepair();
        this.setUnlocalizedName( unlocalizedName );
        this.registerName = unlocalizedName;
    }
    public ItemBeerCraft( String unlocalizedName, int stackSize ) {
        this( unlocalizedName, stackSize, true );
    }

    /**
     * @return the registerName
     */
    public String getRegisterName() {
        return registerName;
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

    @Override
    @SideOnly ( Side.CLIENT)
    public void registerIcons( IIconRegister iconRegister ) {
        itemIcon = iconRegister.registerIcon( this.getIconName() );
    }

    public String getIconName() {
        return this.getUnlocalizedName().substring( this.getUnlocalizedName().indexOf( "." ) + 1 );
    }

    protected String getUnwrappedUnlocalizedName( String unlocalizedName ) {
        return unlocalizedName.substring( unlocalizedName.indexOf( "." ) + 1 );
    }

}
