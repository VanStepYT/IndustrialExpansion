package com.scand.ie.block.custom.whitehole;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WhiteHole extends Block {

    public WhiteHole(Properties p_49795_) {
        super(p_49795_);
    }



    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 15;
    }

}
