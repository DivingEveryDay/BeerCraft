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

public class ItemMilledWheat extends ItemBeerCraft {

    private IIcon[] subTypeIcons = new IIcon[3];
    private String[] subTypeNames = new String[]{ "roasted1", "roasted2", "roasted3" };

    public ItemMilledWheat() {
        super( Names.Items.MILLED_WHEAT, 64 );
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
        damage = MathHelper.clamp_int(damage, 0, 3);
        if( damage == 0 )
            return this.itemIcon;
        return this.subTypeIcons[ damage-1 ];
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        list.add(  new ItemStack( item, 1, 0  ) );
        for (int i = 0; i < this.subTypeIcons.length; ++i)
        {
            list.add(new ItemStack(item, 1, i+1));
        }
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName( ItemStack stack )
    {
        int damage = MathHelper.clamp_int(stack.getItemDamage(), 0, 3);
        if( damage == 0 )
            return super.getUnlocalizedName();
        return super.getUnlocalizedName() + "." + this.subTypeNames[damage-1];
    }

}
