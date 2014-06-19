package com.divingeveryday.beercraft.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import com.divingeveryday.beercraft.item.ItemBeerCraft;
import com.divingeveryday.beercraft.item.ItemBeerMug;
import com.divingeveryday.beercraft.item.ItemBrewGrains;
import com.divingeveryday.beercraft.item.ItemHopsFlower;
import com.divingeveryday.beercraft.item.ItemHopsSeeds;
import com.divingeveryday.beercraft.item.ItemMilledWheat;
import com.divingeveryday.beercraft.item.ItemRoastedWheat;
import com.divingeveryday.beercraft.item.ItemYeast;
import com.divingeveryday.beercraft.reference.Names;
import com.divingeveryday.beercraft.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder( Reference.MOD_ID )
public class ModItems {
    public static final ItemBeerCraft yeast = new ItemYeast();
    public static final ItemBeerCraft beerMug = new ItemBeerMug();
    public static final ItemBeerCraft hopsSeeds = new ItemHopsSeeds();
    public static final ItemBeerCraft hopsFlower = new ItemHopsFlower();
    public static final ItemBeerCraft milledWheat = new ItemMilledWheat();
    public static final ItemBeerCraft roastedWheat = new ItemRoastedWheat();
    public static final ItemBeerCraft brewGrains = new ItemBrewGrains();
    
    public static void init() {
    //    GameRegistry.registerItem( itemYeast, Names.Items.YEAST );
        registerItem( yeast );
        registerItem( beerMug );
        registerItem( hopsSeeds );
        MinecraftForge.addGrassSeed( new ItemStack(hopsSeeds), 10 );
        registerItem( hopsFlower );
        registerItem( milledWheat );
        registerItem( roastedWheat );
        registerItem( brewGrains );
    }
    
    /*
     * Register this item with the GameRegistry
     */
    private static void registerItem( ItemBeerCraft item ) {
       GameRegistry.registerItem( item, item.getRegisterName() );
    }

}
