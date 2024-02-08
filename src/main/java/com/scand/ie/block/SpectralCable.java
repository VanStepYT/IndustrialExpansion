package com.scand.ie.block;

import ic2.core.block.cables.CableBlock;
import ic2.core.block.cables.CableTileEntity;
import ic2.core.block.cables.Cables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SpectralCable extends CableTileEntity {
    public SpectralCable(BlockPos pos, BlockState state) {
        super(pos, state, 0);
    }

    public boolean canDye(int insulation) {
        return false;
    }

    public double getConductionLoss() {
        return 0;
    }

    public int getInsulationEnergyAbsorption() {
        return 640000;
    }


    public int getInsulationBreakdownEnergy() {
        return 640001;
    }

    public int getConductorBreakdownEnergy() {
        return 640001;
    }

    public BlockEntityType<?> createType() {
        return ModBlocks.SPECTRAL_CABLE_TYPE;
    }

}