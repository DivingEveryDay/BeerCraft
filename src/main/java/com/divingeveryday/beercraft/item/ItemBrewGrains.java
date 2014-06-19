package com.divingeveryday.beercraft.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import com.divingeveryday.beercraft.reference.Names;

public class ItemBrewGrains extends ItemBeerCraft {

    public ItemBrewGrains() {
        super( Names.Items.BREW_GRAINS, 1, false );
    }

    @Override
    public void addInformation( ItemStack itemStack, EntityPlayer player, List list, boolean par4 ) {
        if( itemStack.stackTagCompound != null ) {
            int[] counts = itemStack.stackTagCompound.getIntArray( "ContentsCount" );
            int sum = 0;
            for( int i : counts )
                sum += i;
            if( sum > 0 ) {
                list.add( "Contents:" );
                if( counts[0] > 0 )
                    list.add( " Milled: " + counts[0] );
                if( counts[1] > 0 )
                    list.add( " Lite Roast: " + counts[1] );
                if( counts[2] > 0 )
                    list.add( " Med Roast: " + counts[2] );
                if( counts[3] > 0 )
                    list.add( " Dark Roast: " + counts[3] );
            } else {
                list.add( "An empty sack. Congratz!" );
            }
            /*
             * String owner = itemStack.stackTagCompound.getString( "owner" );
             * int code = itemStack.stackTagCompound.getInteger( "code" );
             * list.add( "owner: " + owner ); if( owner.equals( player.username
             * ) ) { list.add( EnumChatFormatting.GREEN + "code: " + code ); }
             * else { list.add( EnumChatFormatting.RED + "code: " +
             * EnumChatFormatting.OBFUSCATED + code ); }
             */
        }
    }
}
