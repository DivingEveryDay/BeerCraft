package com.divingeveryday.beercraft.fluid;

import com.divingeveryday.beercraft.reference.Names;

public class FluidSimpleBeer extends FluidBeerCraft {

    public FluidSimpleBeer(  ) {
        super( Names.Fluids.SIMPLE_BEER );
        this.setDensity(1054);
        this.setTemperature( 295);
    }

}
