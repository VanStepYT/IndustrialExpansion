package com.scand.ie.block.entity;

import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.tiles.impls.BaseEnergyStorageTileEntity;
import ic2.core.block.storage.tiles.storage.ISUTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class UTESUTile extends BaseEnergyStorageTileEntity {

    public UTESUTile(BlockPos pos, BlockState state) {
        super(pos, state, 7, 131072, 2147483647);
    }
    @Override
    public int getGuiOffset() {
        return -20;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.UTESU_TILE_TYPE;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0;
    }
}
