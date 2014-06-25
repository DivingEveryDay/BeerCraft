package com.divingeveryday.beercraft.client.gui;

import com.divingeveryday.beercraft.container.ContainerBurner;
import com.divingeveryday.beercraft.container.ContainerGrainMill;
import com.divingeveryday.beercraft.container.ContainerGrainRoaster;
import com.divingeveryday.beercraft.reference.GuiIDs;
import com.divingeveryday.beercraft.tileentity.TileEntityBeerCraft;
import com.divingeveryday.beercraft.tileentity.TileEntityBurner;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainRoaster;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ) {
        System.out.println("getServerGuiElement");
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
        }
        return null;
    }
 
    @Override
    public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ) {
        System.out.println("getClientGuiElement");
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
        }
        return null;
    }

}
