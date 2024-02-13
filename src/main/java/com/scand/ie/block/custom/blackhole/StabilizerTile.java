package com.scand.ie.block.custom.blackhole;

import com.scand.ie.IEMod;
import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.features.IAreaOfEffect;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.tiles.impls.BaseGeneratorTileEntity;
import ic2.core.block.rendering.world.impl.BlockHighlighter;
import ic2.core.inventory.container.IC2Container;
import ic2.core.item.renders.IC2TileEntityStackRenderer;
import ic2.core.platform.rendering.events.TilesRenderedEvent;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StabilizerTile extends BaseGeneratorTileEntity implements ITickListener {

    public StabilizerTile(BlockPos pos, BlockState state) {
        super(pos, state, 0);
        this.production = 1048576;
    }



    @Override
    public boolean gainFuel() {
        return false;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.BLACK_HOLE_STABILIZER_TYPE;
    }

    @Override
    public float getEUProduction() {
        return 0;
    }

    @Override
    public void onTick() {
        if(checkforSingularity(this.getPosition())){
            setActive(true);
            this.storage+=this.production;
            this.getLevel();
        }
        else setActive(false);
    }

    private boolean checkforSingularity(BlockPos pos) {
        boolean check = level.getBlockState(pos.above(8)).getBlock()==ModBlocks.SINGULARITY.get() ||
                level.getBlockState(pos.west(8)).getBlock()==ModBlocks.SINGULARITY.get() ||
                level.getBlockState(pos.below(8)).getBlock()==ModBlocks.SINGULARITY.get() ||
                level.getBlockState(pos.north(8)).getBlock()==ModBlocks.SINGULARITY.get() ||
                level.getBlockState(pos.south(8)).getBlock()==ModBlocks.SINGULARITY.get() ||
                level.getBlockState(pos.east(8)).getBlock()==ModBlocks.SINGULARITY.get();

        return check;
    }

    private BlockPos getSingularityPos(BlockPos pos){
        if (this.level.getBlockState(pos.above(8)).getBlock() == ModBlocks.SINGULARITY.get()) {
            return pos.above(8);
        }
        if (this.level.getBlockState(pos.below(8)).getBlock() == ModBlocks.SINGULARITY.get()) {
            return pos.below(8);
        }
        if (this.level.getBlockState(pos.north(8)).getBlock() == ModBlocks.SINGULARITY.get()) {
            return pos.north(8);
        }
        if (this.level.getBlockState(pos.west(8)).getBlock() == ModBlocks.SINGULARITY.get()) {
            return pos.west(8);
        }
        if (this.level.getBlockState(pos.east(8)).getBlock() == ModBlocks.SINGULARITY.get()) {
            return pos.east(8);
        }
        if (this.level.getBlockState(pos.south(8)).getBlock() == ModBlocks.SINGULARITY.get()) {
            return pos.south(8);
        }
        return pos;
    }

    @Override
    public int getMaxFuel() {
        return 0;
    }

    @Override
    public boolean hasGui(Player player, InteractionHand hand, Direction side) {
        return false;
    }

    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return null;
    }



    private int[] getSingularityDifference(){
        BlockPos pos = this.worldPosition;
        BlockPos singularityPos = this.getSingularityPos(pos);

        int x = singularityPos.getX()-pos.getX();
        int y = singularityPos.getY()-pos.getY();
        int z = singularityPos.getZ()-pos.getZ();

        return new int[]{x,y,z};
    }

}
