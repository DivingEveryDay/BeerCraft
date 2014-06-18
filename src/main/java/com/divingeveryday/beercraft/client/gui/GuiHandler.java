package com.divingeveryday.beercraft.client.gui;

import com.divingeveryday.beercraft.container.ContainerGrainMill;
import com.divingeveryday.beercraft.reference.GuiIDs;
import com.divingeveryday.beercraft.tileentity.TileEntityBeerCraft;
import com.divingeveryday.beercraft.tileentity.TileEntityGrainMill;

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
                System.out.println("GuiIDs.GRAIN_MILL");
                if( tileEntity instanceof TileEntityGrainMill )
                    return new ContainerGrainMill( player.inventory, (TileEntityGrainMill)tileEntity );
                break;
               
        }
        return null;
    }
 
    @Override
    public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ) {
        System.out.println("getClientGuiElement");
        TileEntity tileEntity = world.getTileEntity( x, y, z );
        switch( ID ) {
            case GuiIDs.GRAIN_MILL:
                System.out.println("GuiIDs.GRAIN_MILL");
                if( tileEntity instanceof TileEntityGrainMill )
                    return new GuiGrainMill( player.inventory, (TileEntityGrainMill)tileEntity );
                break;
               
        }
        return null;
    }

}
