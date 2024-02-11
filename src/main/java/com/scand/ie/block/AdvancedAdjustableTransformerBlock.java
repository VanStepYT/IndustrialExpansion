package com.scand.ie.block;

import com.scand.ie.IEMod;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.storage.TransformerBlock;
import ic2.core.platform.rendering.features.providers.NoStateProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class AdvancedAdjustableTransformerBlock extends TransformerBlock{

    public AdvancedAdjustableTransformerBlock(String blockName, BlockEntityType<? extends BlockEntity> type) {
        super(blockName, IBlockDropProvider.SELF,
                new NoStateProvider(IEMod.MOD_ID, blockName), type);
    }

}
