package com.scand.ie.block;

import ic2.api.blocks.IWrenchable;
import ic2.core.block.base.IStateController;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuarryBlock extends Block implements IStateController<QuantumQuarryTile>, IWrenchable {
    public QuarryBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void onStateUpdate(Level level, BlockPos blockPos, BlockState blockState, QuantumQuarryTile quantumQuarryTile) {

    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    @Override
    public Direction getFacing(BlockState blockState, Level level, BlockPos blockPos) {
        return null;
    }

    @Override
    public boolean canSetFacing(BlockState blockState, Level level, BlockPos blockPos, Player player, Direction direction) {
        return false;
    }

    @Override
    public boolean setFacing(BlockState blockState, Level level, BlockPos blockPos, Player player, Direction direction) {
        return false;
    }

    @Override
    public boolean doSpecialAction(BlockState blockState, Level level, BlockPos blockPos, Direction direction, Player player, Vec3 vec3) {
        return false;
    }

    @Override
    public AABB hasSpecialAction(BlockState blockState, Level level, BlockPos blockPos, Direction direction, Player player, Vec3 vec3) {
        return null;
    }

    @Override
    public boolean canRemoveBlock(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return true;
    }

    @Override
    public double getDropRate(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return 1.0;
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T>
    getTicker(Level lvl, BlockState state, BlockEntityType<T> entityType) {
        if(entityType==ModBlockEntities.QUANTUM_QUARRY.get()){
            return QuantumQuarryTile::tick;
        }
        return null;
    }
}
