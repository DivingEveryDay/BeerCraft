package com.divingeveryday.beercraft.block;

import java.util.Random;

import buildcraft.energy.BlockBuildcraftFluid;
import buildcraft.energy.render.EntityDropParticleFX;

import com.divingeveryday.beercraft.reference.Textures;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockBeerCraftFluid extends BlockFluidClassic {
    protected boolean flammable;
    protected int     flammability = 0;

    protected float   particleRed;
    protected float   particleGreen;
    protected float   particleBlue;

    @SideOnly ( Side.CLIENT)
    protected IIcon   stillIcon;
    @SideOnly ( Side.CLIENT)
    protected IIcon   flowingIcon;

    public BlockBeerCraftFluid( Fluid fluid, Material material ) {
        super( fluid, material );
        // TODO Auto-generated constructor stub
    }

    @Override
    public IIcon getIcon( int side, int meta ) {
        return side != 0 && side != 1 ? this.flowingIcon : this.stillIcon;
    }

    public String getRegisterName() {
        return this.fluidName + "Block";
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public void registerBlockIcons( IIconRegister iconRegister ) {
        this.stillIcon = iconRegister.registerIcon( Textures.RESOURCE_PREFIX + this.fluidName + "_still" );
        this.flowingIcon = iconRegister.registerIcon( Textures.RESOURCE_PREFIX + this.fluidName + "_flow" );
    }

    /* Next methods copied from buildcraft.energy.BlockBuildcraftFluid */
    @Override
    public void onNeighborBlockChange( World world, int x, int y, int z, Block block ) {
        super.onNeighborBlockChange( world, x, y, z, block );
        if( flammable && world.provider.dimensionId == -1 ) {
            world.newExplosion( null, x, y, z, 4F, true, true );
            world.setBlockToAir( x, y, z );
        }
    }

    public BlockBeerCraftFluid setFlammable( boolean flammable ) {
        this.flammable = flammable;
        return this;
    }

    public BlockBeerCraftFluid setFlammability( int flammability ) {
        this.flammability = flammability;
        return this;
    }

    @Override
    public int getFireSpreadSpeed( IBlockAccess world, int x, int y, int z, ForgeDirection face ) {
        return flammable ? 300 : 0;
    }

    @Override
    public int getFlammability( IBlockAccess world, int x, int y, int z, ForgeDirection face ) {
        return flammability;
    }

    @Override
    public boolean isFlammable( IBlockAccess world, int x, int y, int z, ForgeDirection face ) {
        return flammable;
    }

    @Override
    public boolean isFireSource( World world, int x, int y, int z, ForgeDirection side ) {
        return flammable && flammability == 0;
    }

    public BlockBeerCraftFluid setParticleColor( float particleRed, float particleGreen, float particleBlue ) {
        this.particleRed = particleRed;
        this.particleGreen = particleGreen;
        this.particleBlue = particleBlue;
        return this;
    }

    @Override
    @SideOnly ( Side.CLIENT)
    public void randomDisplayTick( World world, int x, int y, int z, Random rand ) {
        super.randomDisplayTick( world, x, y, z, rand );

        if( rand.nextInt( 10 ) == 0 && World.doesBlockHaveSolidTopSurface( world, x, y - 1, z )
                && !world.getBlock( x, y - 2, z ).getMaterial().blocksMovement() ) {

            double px = x + rand.nextFloat();
            double py = y - 1.05D;
            double pz = z + rand.nextFloat();

            EntityFX fx = new EntityDropParticleFX( world, px, py, pz, particleRed, particleGreen, particleBlue );
            FMLClientHandler.instance().getClient().effectRenderer.addEffect( fx );
        }
    }

    @Override
    public boolean canDisplace( IBlockAccess world, int x, int y, int z ) {
        if( world.getBlock( x, y, z ).getMaterial().isLiquid() ) {
            return false;
        }
        return super.canDisplace( world, x, y, z );
    }

    @Override
    public boolean displaceIfPossible( World world, int x, int y, int z ) {
        if( world.getBlock( x, y, z ).getMaterial().isLiquid() ) {
            return false;
        }
        return super.displaceIfPossible( world, x, y, z );
    }
    /* end copy */

}
