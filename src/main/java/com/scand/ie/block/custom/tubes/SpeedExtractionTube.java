package com.scand.ie.block.custom.tubes;

import ic2.core.block.transport.item.tubes.ExtractionTubeTileEntity;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

public class SpeedExtractionTube extends ExtractionTubeTileEntity {

    public static final BlockEntityType<SpeedExtractionTube> TYPE = IC2Tiles.createTile(
            "speed_extraction_tube", SpeedExtractionTube::new
    );
    public SpeedExtractionTube(BlockPos pos, BlockState state) {
        super(pos, state);
        this.setMaxSpeed(30);
    }


    private void updatePulseState() {
        if (this.pulse) {
            if (this.pulseState == 0 && this.isRedstonePowered()) {
                this.pulseState = 1;
            } else if (this.pulseState == 2 && !this.isRedstonePowered()) {
                this.pulseState = 0;
            }

        }
    }

    @Override
    public BlockEntityType<?> createType() {
        return TYPE;
    }

    @Override
    public void onTubeUpdate() {
        if (!this.isRendering()) {
            this.handleRedstone();
            this.updatePulseState();
            if (this.clock(1)) {
                if (this.sensitive) {
                    if (this.pulse) {
                        if (this.pulseState != 1) {
                            return;
                        }
                    } else if (!this.isRedstonePowered()) {
                        return;
                    }
                }

                if (this.pulse) {
                    this.pulseState = 2;
                }
                for(int i = 0; i<= 10; i++){
                    this.handleExtraction(this.getFacing());
                    if (this.extraFacings.size() > 0) {
                        Iterator var1 = this.extraFacings.iterator();

                        while(var1.hasNext()) {
                            Direction dir = (Direction)var1.next();
                            this.handleExtraction(dir);
                        }
                    }
                }

            }

        }
    }
}
