package com.scand.ie.block.entity;

import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.tiles.impls.BaseEnergyStorageTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class IUTESUTile extends BaseEnergyStorageTileEntity {

    public IUTESUTile(BlockPos pos, BlockState state) {
        super(pos, state, 9, 2097152, (int) Math.pow(10,10));
    }
    @Override
    public int getGuiOffset() {
        return -20;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.IUTESU_TILE_TYPE;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0;
    }
}
