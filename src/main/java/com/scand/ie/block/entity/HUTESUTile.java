package com.scand.ie.block.entity;

import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.tiles.impls.BaseEnergyStorageTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HUTESUTile extends BaseEnergyStorageTileEntity {

    public HUTESUTile(BlockPos pos, BlockState state) {
        super(pos, state, 8, 524288, 2147483647);
    }
    @Override
    public int getGuiOffset() {
        return -20;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.HUTESU_TILE_TYPE;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0;
    }
}
