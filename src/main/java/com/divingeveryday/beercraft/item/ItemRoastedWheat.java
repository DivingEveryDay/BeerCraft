package com.divingeveryday.beercraft.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import com.divingeveryday.beercraft.reference.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRoastedWheat extends ItemBeerCraft {

    private IIcon[] subTypeIcons = new IIcon[3];
    private String[] subTypeNames = new String[]{ "1", "2", "3" };

    public ItemRoastedWheat() {
        super( Names.Items.ROASTED_WHEAT, 64 );
        this.setHasSubtypes( true );
        this.setMaxDamage( 0 );
    }
    @Override
    @SideOnly ( Side.CLIENT)
    public void registerIcons( IIconRegister iconRegister ) {
        super.registerIcons( iconRegister );

        for( int i = 0; i < this.subTypeIcons.length; ++i ) {
            this.subTypeIcons[i] = iconRegister.registerIcon( this.getIconName() + "_" + this.subTypeNames[i] );
        }
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly ( Side.CLIENT)
    public IIcon getIconFromDamage( int damage ) {
        damage = MathHelper.clamp_int(damage, 0, 2);
        
        return this.subTypeIcons[ damage ];
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < this.subTypeIcons.length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName( ItemStack stack )
    {
        int damage = MathHelper.clamp_int(stack.getItemDamage(), 0, 2);
        return super.getUnlocalizedName() + "." + this.subTypeNames[damage];
    }


}
