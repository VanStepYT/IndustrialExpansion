package com.scand.ie.ies;

import com.scand.ie.block.ModBlocks;
import ic2.api.energy.EnergyNet;
import ic2.core.block.base.tiles.impls.BaseTransformerTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class IESSplitter extends BaseTransformerTileEntity {

    public IESSplitter(BlockPos pos, BlockState state) {
        super(pos, state, EnergyNet.INSTANCE.getPowerFromTier(13), EnergyNet.INSTANCE.getPowerFromTier(13), EnergyNet.INSTANCE.getPowerFromTier(13));
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.IES_SPLITTER_TYPE;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0;
    }
}
