package com.scand.ie.block.entity;

import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.tiles.impls.BaseTransformerTileEntity;
import ic2.core.block.storage.tiles.transformer.AdjustableTransformerTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AdvancedAdjustableTransformer extends AdjustableTransformerTileEntity {

    public AdvancedAdjustableTransformer(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.ADVANCED_ADJUSTABLE_TRANSFORMER_TYPE;
    }

    @Override
    public int getTier() {
        return 9;
    }

    @Override
    public int getSinkTier() {
        return 9;
    }

    @Override
    public int getMaxEnergyOutput() {
        return 131072;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0;
    }
}
