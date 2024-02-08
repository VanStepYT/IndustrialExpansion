package com.scand.ie.block;

import ic2.core.block.base.tiles.BaseElectricTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class QuantumQuarryTile extends BaseElectricTileEntity {

    private int EU = 0;
    private int maxEU = 100;

    private final ItemStackHandler itemHandler = new ItemStackHandler(18){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    public QuantumQuarryTile(BlockPos pos, BlockState state, int size, int maxInput, int maxEnergy) {
        super(pos, state, size, maxInput, maxEnergy);
    }

    @Override
    public boolean supportsNotify() {
        return false;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlockEntities.QUANTUM_QUARRY.get();
    }

    @Override
    public int getTier() {
        return 6;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState state, T t) {
        if(level.isClientSide){
            return;
        }
        if(getIndex(t) != 420){
            if (true) {

            }
        }
    }

    private static <T extends BlockEntity> int getIndex(T t) {
        return 0;
    }
}
