package com.scand.ie.ies;

import com.scand.ie.IEMod;
import com.scand.ie.block.ModBlocks;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.IEnergyNet;
import ic2.core.block.base.tiles.impls.BaseEnergyStorageTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class IESTransporter extends BaseEnergyStorageTileEntity {

    public IESTransporter(BlockPos pos, BlockState state) {
            super(pos, state, 13, EnergyNet.INSTANCE.getPowerFromTier(13), EnergyNet.INSTANCE.getPowerFromTier(13));
    }
    @Override
    public int getGuiOffset() {
        return -20;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(IEMod.MOD_ID, "textures/gui/iest.png");
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.IES_TRANSPORTER_TYPE;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0;
    }
}
