package com.divingeveryday.beercraft.client.gui;

import com.divingeveryday.beercraft.container.ContainerBurner;
import com.divingeveryday.beercraft.container.ContainerFermenter;
import com.divingeveryday.beercraft.container.ContainerGrainMill;
import com.divingeveryday.beercraft.container.ContainerGrainRoaster;
import com.divingeveryday.beercraft.container.ContainerKettle;
import com.divingeveryday.beercraft.container.ContainerMashTun;
import com.divingeveryday.beercraft.reference.GuiIDs;
import com.divingeveryday.beercraft.tileentity.TileEntityBeerCraft;
import com.divingeveryday.beercraft.tileentity.TileEntityBurner;
import com.divingeveryday.beercraft.tileentity.TileEntityFermenter;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainRoaster;
import com.divingeveryday.beercraft.tileentity.TileEntityKettle;
import com.divingeveryday.beercraft.tileentity.TileEntityMashTun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ) {
        System.out.println("getServerGuiElement( " + ID + " )" );
        TileEntity tileEntity = world.getTileEntity( x, y, z );
        switch( ID ) {
            case GuiIDs.GRAIN_MILL:
                if( tileEntity instanceof TileEntityGrainMill )
                    return new ContainerGrainMill( player.inventory, (TileEntityGrainMill)tileEntity );
                break;
            case GuiIDs.GRAIN_ROASTER:
                if( tileEntity instanceof TileEntityGrainRoaster )
                    return new ContainerGrainRoaster( player.inventory, (TileEntityGrainRoaster)tileEntity );
                break;
            case GuiIDs.BURNER:
                if( tileEntity instanceof TileEntityBurner )
                    return new ContainerBurner( player.inventory, (TileEntityBurner)tileEntity );
                break;
            case GuiIDs.KETTLE:
                if( tileEntity instanceof TileEntityKettle )
                    return new ContainerKettle( player.inventory, (TileEntityKettle)tileEntity );
                break;
            case GuiIDs.MASH_TUN:
                if( tileEntity instanceof TileEntityMashTun )
                    return new ContainerMashTun( player.inventory, (TileEntityMashTun)tileEntity );
                break;
            case GuiIDs.FERMENTER:
                if( tileEntity instanceof TileEntityFermenter )
                    return new ContainerFermenter( player.inventory, (TileEntityFermenter)tileEntity );
                break;
        }
        return null;
    }
 
    @Override
    public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ) {
        System.out.println("getClientGuiElement( " + ID + " )");
        TileEntity tileEntity = world.getTileEntity( x, y, z );
        switch( ID ) {
            case GuiIDs.GRAIN_MILL:
                if( tileEntity instanceof TileEntityGrainMill )
                    return new GuiGrainMill( player.inventory, (TileEntityGrainMill)tileEntity );
                break;
            case GuiIDs.GRAIN_ROASTER:
                if( tileEntity instanceof TileEntityGrainRoaster )
                    return new GuiGrainRoaster( player.inventory, (TileEntityGrainRoaster)tileEntity );
                break;
            case GuiIDs.BURNER:
                if( tileEntity instanceof TileEntityBurner )
                    return new GuiBurner( player.inventory, (TileEntityBurner)tileEntity );
                break;
            case GuiIDs.KETTLE:
                if( tileEntity instanceof TileEntityKettle )
                    return new GuiKettle( player.inventory, (TileEntityKettle)tileEntity );
                break;
            case GuiIDs.MASH_TUN:
                if( tileEntity instanceof TileEntityMashTun )
                    return new GuiMashTun( player.inventory, (TileEntityMashTun)tileEntity );
                break;
            case GuiIDs.FERMENTER:
                if( tileEntity instanceof TileEntityFermenter )
                    return new GuiFermenter( player.inventory, (TileEntityFermenter)tileEntity );
                break;

        }
        return null;
    }

}
