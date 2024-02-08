package com.scand.ie.block;

import com.scand.ie.IEMod;
import com.scand.ie.block.entity.UTESUTile;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.storage.EnergyStorageBlock;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.rendering.features.ITextureProvider;
import ic2.core.platform.rendering.features.providers.NoStateProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class UTESUBlock extends EnergyStorageBlock {
    public UTESUBlock(String blockName, BlockEntityType<UTESUTile> type) {
        super(blockName, IBlockDropProvider.SELF,
                new NoStateProvider(IEMod.MOD_ID, blockName), type);
    }
}
