package com.scand.ie.block.custom.cable;

import com.scand.ie.block.ModBlocks;
import ic2.core.block.cables.CableTileEntity;
import ic2.core.block.rendering.props.CableProperty;
import ic2.core.block.rendering.props.CamouflageProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelData;

public class NeutronCableTile extends CableTileEntity {
    public NeutronCableTile(BlockPos pos, BlockState state) {
        super(pos, state, 0);
    }

    @OnlyIn(Dist.CLIENT)
    public ModelData getModelData() {
        return this.foamed == 2 ? ModelData.builder().with(CamouflageProperty.INSTANCE, (R) -> {
            return this.storage.getQuads(this.getBlockState(), this.getLevel(), this.getBlockPos(), R);
        }).build() : ModelData.builder().with(CableProperty.INSTANCE, this.connectivity << 6 | this.anchors).build();
    }

    @Override
    public double getConductionLoss() {
        return 0.001;
    }

    @Override
    public int getInsulationEnergyAbsorption() {
        return 9001;
    }

    @Override
    public int getInsulationBreakdownEnergy() {
        return 9001;
    }

    @Override
    public int getConductorBreakdownEnergy() {
        return 100000001;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.NEUTRON_CABLE_TYPE;
    }
}
