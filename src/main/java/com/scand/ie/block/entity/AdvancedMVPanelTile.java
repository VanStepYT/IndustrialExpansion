package com.scand.ie.block.entity;

import com.scand.ie.block.ModBlocks;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tiles.readers.IEUProducer;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.ITileActivityProvider;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.tiles.impls.BaseGeneratorTileEntity;
import ic2.core.inventory.container.IC2Container;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AdvancedMVPanelTile extends BaseGeneratorTileEntity implements ITickListener, IEnergySource, IWrenchableTile, IEUProducer, ITileActivityProvider {
    private final int maxOutput;
    private final BlockPos pos;
    private int lowerProduction;

    public AdvancedMVPanelTile(BlockPos pos, BlockState state) {
        super(pos, state, 1);
        this.pos = pos;
        this.tier = 2;
        this.production = 128;
        this.lowerProduction = 64;
        this.maxStorage = 8192;
        this.maxOutput = 256;
    }

    @Override
    public boolean gainFuel() {
        return false;
    }

    @Override
    public boolean gainEnergy() {
        if(this.isConverting()){
            if(skyBlockCheck()){
                if(this.isSunVisible(level)){
                    this.storage += this.production;
                }else {
                    this.storage += this.lowerProduction;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isConverting(){
        if(this.skyBlockCheck()){
            if(isSunVisible(level)){
                return this.storage + this.production <= this.maxStorage;
            }
            else{
                return this.storage + this.lowerProduction <= this.maxStorage;
            }
        }
        return false;
    }

    private boolean isSunVisible(Level level) {
        if(!level.isDay()){
            return false;
        }
        return !level.isRaining() && !level.isThundering();
    }


    public boolean skyBlockCheck() {
        return this.getLevel().canSeeSkyFromBelowWater(this.getPosition().above()) && this.getLevel().dimensionType().hasSkyLight();
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.ADVANCED_LV_PANEL_TYPE;
    }

    @Override
    public float getEUProduction() {
        return 0;
    }

    @Override
    public int getMaxFuel() {
        return 0;
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return null;
    }

    @Override
    public boolean hasGui(Player player, InteractionHand hand, Direction side) {
        return false;
    }
}
