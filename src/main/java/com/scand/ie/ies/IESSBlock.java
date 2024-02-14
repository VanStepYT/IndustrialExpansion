package com.scand.ie.ies;

import com.scand.ie.IEMod;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.storage.EnergyStorageBlock;
import ic2.core.block.storage.TransformerBlock;
import ic2.core.platform.rendering.features.providers.NoStateProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class IESSBlock extends TransformerBlock {

    public IESSBlock(String blockName, BlockEntityType<?> type) {
        super(blockName, IBlockDropProvider.SELF,
                new NoStateProvider(IEMod.MOD_ID, "ies/" + blockName), type);
    }
}
