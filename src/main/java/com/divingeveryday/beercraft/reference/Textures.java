package com.divingeveryday.beercraft.reference;

import net.minecraft.util.ResourceLocation;

public class Textures {
    public static final String           RESOURCE_PREFIX        = Reference.MOD_ID.toLowerCase() + ":";

    // Base file paths
    public static final String           MODEL_TEXTURE_LOCATION = "textures/models/";
    // Model textures
    // public static final ResourceLocation MODEL_EXAMPLE =
    // modelTextureResource( "example.png" )

    public static final String           GUI_SHEET_LOCATION     = "textures/guis/";
    // GUI textures
    public static final ResourceLocation GUI_GRAIN_MILL         = guiSheetResource( "grain_mill.png" );

    public static final ResourceLocation GUI_GRAIN_ROASTER      = guiSheetResource( "grain_roaster.png" );

    public static final ResourceLocation GUI_BURNER = guiSheetResource( "burner.png" );

    public static final ResourceLocation GUI_KETTLE = guiSheetResource( "kettle.png" );

    public static final ResourceLocation GUI_MASH_TUN = guiSheetResource( "mash_tun.png" );

    public static final ResourceLocation GUI_FERMENTER = guiSheetResource( "fermenter.png" );

    private static ResourceLocation resource( String path ) {
        return new ResourceLocation( Reference.MOD_ID, path );
    }

    private static ResourceLocation guiSheetResource( String path ) {
        return resource( GUI_SHEET_LOCATION + path );
    }

    private static ResourceLocation modelTextureResource( String path ) {
        return resource( MODEL_TEXTURE_LOCATION + path );
    }
}
