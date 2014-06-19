package com.divingeveryday.beercraft;

import com.divingeveryday.beercraft.client.gui.GuiHandler;
import com.divingeveryday.beercraft.init.ModBlocks;
import com.divingeveryday.beercraft.init.ModItems;
import com.divingeveryday.beercraft.init.ModRecipies;
import com.divingeveryday.beercraft.proxy.IProxy;
import com.divingeveryday.beercraft.reference.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import java.io.File;

@Mod( modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, certificateFingerprint = Reference.FINGERPRINT )
public class BeerCraft {
    @Instance( Reference.MOD_ID )
    public static BeerCraft instance;

    @SidedProxy( clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS )
    public static IProxy    proxy;

    @EventHandler
    public void invalidFingerprint( FMLFingerprintViolationEvent event ) {

    }

    @EventHandler
    public void serverStarting( FMLServerStartingEvent event ) {

    }

    @EventHandler
    public void preInit( FMLPreInitializationEvent event ) {
        // ConfigurationHandler.init( event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID.toLowerCase() + File.separator );

        // PacketHandler.init();

        proxy.registerKeybindings();

        ModItems.init();

        ModBlocks.init();
        
        ModRecipies.init();
    }

    @EventHandler
    public void init( FMLInitializationEvent event ) {
        // Register the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler( instance, new GuiHandler() );

        // Initialize mod tile entities
        proxy.registerTileEntities();

        // Initialize custom rendering and pre-load textures (Client only)
        proxy.initRenderingAndTextures();

        // Register the Items Event Handler
        proxy.registerEventHandlers();

        // CraftingHandler.init();

        // Register our fuels
        // GameRegistry.registerFuelHandler( new FuelHandler() );
    }

    @EventHandler
    public void postInit( FMLPostInitializationEvent event ) {

    }

    @EventHandler
    public void handleIMCMessages( IMCEvent event ) {

    }
}
