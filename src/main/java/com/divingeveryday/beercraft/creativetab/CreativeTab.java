package com.divingeveryday.beercraft.creativetab;

import com.divingeveryday.beercraft.init.ModItems;
import com.divingeveryday.beercraft.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab {
    public static final CreativeTabs BEERCRAFT_TAB = new CreativeTabs( Reference.MOD_ID )
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.beerMug;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public String getTranslatedTabLabel()
        {
            return StatCollector.translateToLocal("creativetab.title.beercraft");
        }
    };
}
