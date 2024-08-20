package com.scand.ie.hydrogenreactor;

import com.scand.ie.IEMod;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.generators.BaseGeneratorBlock;
import ic2.core.platform.rendering.features.providers.NoStateProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class HydrogenReactorBlock extends BaseGeneratorBlock {
    public HydrogenReactorBlock(String blockName,
                                BlockEntityType<? extends BlockEntity>
                                             type) {
        super(blockName, IBlockDropProvider.SELF,
                new NoStateProvider(IEMod.MOD_ID, blockName), type);


    }

}
