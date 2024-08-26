package com.scand.ie.block.custom.wind;

import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.base.tiles.BaseMultiBlockTileEntity;
import ic2.core.block.base.tiles.BaseTileEntity;
import ic2.core.block.base.tiles.impls.machine.multi.BaseColossalMachineTileEntity;
import ic2.core.block.machines.ColossalMachineBlock;
import ic2.core.platform.rendering.features.ITextureProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class WindFarmBlock extends ColossalMachineBlock {
    public WindFarmBlock(String blockName, IBlockDropProvider drop, ITextureProvider provider, BlockEntityType<? extends BaseTileEntity> type) {
        super(blockName, drop, provider, type);
    }
    public void onStateUpdate(Level world, BlockPos pos, BlockState state, BaseTileEntity tile) {
        tile.setState((BlockState)((BlockState)((BlockState)((BlockState)state.setValue(FACING, tile.getFacing())).setValue(ACTIVE, tile.isActive())).setValue(FORMED, ((BaseMultiBlockTileEntity)tile).isValid)).setValue(SIZE, ((WindFarmTileEntity)tile).getStructureSize()));
    }
}
