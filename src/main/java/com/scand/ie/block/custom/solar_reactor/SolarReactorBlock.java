package com.scand.ie.block.custom.solar_reactor;

import com.scand.ie.IEMod;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.generators.BaseGeneratorBlock;
import ic2.core.platform.rendering.features.providers.ToggleProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class SolarReactorBlock extends BaseGeneratorBlock {

    public SolarReactorBlock(String blockName, BlockEntityType<? extends BlockEntity> tile) {
        super(blockName, IBlockDropProvider.SELF,
                new ToggleProvider(IEMod.MOD_ID,blockName), tile);
    }
}
