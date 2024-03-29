package com.scand.ie.block.entity;

import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.tiles.impls.BaseTransformerTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HUVTransformerTileEntity extends BaseTransformerTileEntity {

    public HUVTransformerTileEntity(BlockPos pos, BlockState state) {
        super(pos, state, 131072, 524288, 524288);
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.HUVTRANSFORMER_TYPE;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0;
    }
}
