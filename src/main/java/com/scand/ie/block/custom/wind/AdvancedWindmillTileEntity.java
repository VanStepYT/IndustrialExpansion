package com.scand.ie.block.custom.wind;

import com.scand.ie.block.ModBlocks;
import ic2.core.IC2;
import ic2.core.block.generators.tiles.WindmillTileEntity;
import ic2.core.block.rendering.tile.WindmillRenderer;
import ic2.core.inventory.container.IC2Container;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.utils.math.geometry.Box;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.util.ObjectArrayIterator;
import org.jetbrains.annotations.Debug;
import org.jline.utils.Log;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdvancedWindmillTileEntity extends WindmillTileEntity {

    public AdvancedWindmillTileEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public float getEUProduction() {
        return super.getEUProduction()*2;
    }

    @Override
    public int getMaxEU() {
        return 100000;
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlocks.ADVANCED_WINDMILL_TYPE;
    }

    @Override
    public int getTier() {
        return 4;
    }
}
