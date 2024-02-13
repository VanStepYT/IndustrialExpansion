package com.scand.ie.block.custom.blackhole;

import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.base.tiles.BaseTileEntity;
import ic2.core.block.machines.BaseMachineBlock;
import ic2.core.platform.rendering.features.ITextureProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ControllerBlock extends BaseMachineBlock {
    public ControllerBlock(String blockName, IBlockDropProvider drop, ITextureProvider provider, BlockEntityType<? extends BaseTileEntity> type) {
        super(blockName, drop, provider, type);
    }

    @Override
    public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
        super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
        if(p_60516_.getBlockState(p_60517_.above(8)).getBlock() == ModBlocks.SINGULARITY.get()){
            p_60516_.setBlockAndUpdate(p_60517_.above(8), Blocks.AIR.defaultBlockState());
        }
    }
}
