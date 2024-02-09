package com.scand.ie.block;

import com.scand.ie.IEMod;
import com.scand.ie.block.entity.HUTESUTile;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.storage.EnergyStorageBlock;
import ic2.core.platform.rendering.features.providers.NoStateProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class HUTESUBlock extends EnergyStorageBlock {
    public HUTESUBlock(String blockName, BlockEntityType<HUTESUTile> type) {
        super(blockName, IBlockDropProvider.SELF,
                new NoStateProvider(IEMod.MOD_ID, blockName), type);
    }
}
