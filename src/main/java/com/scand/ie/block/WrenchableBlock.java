package com.scand.ie.block;

import ic2.api.blocks.IWrenchable;
import ic2.core.block.base.features.IWrenchRemovable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class WrenchableBlock extends Block implements IWrenchRemovable {
    public WrenchableBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void setFacing(Direction direction) {
        return;
    }

    @Override
    public boolean canRemoveBlock(Player player) {
        return true;
    }

    @Override
    public double getDropRate(Player player) {
        return 1;
    }
}
