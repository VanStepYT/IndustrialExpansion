package com.scand.ie.block;

import ic2.core.block.base.misc.ITubeBlock;
import ic2.core.block.rendering.block.tubes.CableModel;
import net.minecraft.client.renderer.block.model.BlockElementFace;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class SpectralCableModel extends CableModel {
    public SpectralCableModel(ITubeBlock tube, BlockState state) {
        super(tube, state);
    }

    public BlockElementFace getFace(Direction dir, Direction side, float min, float max) {
        if (side == dir) {
            return new BlockElementFace((Direction)null, -1, "", new BlockFaceUV(new float[]{min, min, max, max}, 0));
        } else if (side.getAxis().isVertical()) {
            return side.getAxisDirection() == Direction.AxisDirection.NEGATIVE ? new BlockElementFace((Direction)null, -1, "", new BlockFaceUV(new float[]{min, 0.0F, max, min}, dir.get2DDataValue() * 90 + (dir.getAxis() == Direction.Axis.X ? 180 : 0))) : new BlockElementFace((Direction)null, -1, "", new BlockFaceUV(new float[]{min, max, max, 16.0F}, dir.get2DDataValue() * 90));
        } else if (side.getAxis().isHorizontal()) {
            return side.getAxisDirection() == Direction.AxisDirection.NEGATIVE ? new BlockElementFace((Direction)null, -1, "", new BlockFaceUV(new float[]{0.0F, min, min, max}, 0)) : new BlockElementFace((Direction)null, -1, "", new BlockFaceUV(new float[]{max, min, 16.0F, max}, 0));
        } else {
            return null;
        }
    }
}
