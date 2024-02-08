package com.scand.ie.block.entity;

import com.scand.ie.block.ModBlocks;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tiles.readers.IEUProducer;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.ITileActivityProvider;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.tiles.impls.BaseGeneratorTileEntity;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import static ic2.core.block.generators.tiles.SolarPanelTileEntity.isSunVisible;

public class AdvancedLVPanelTile extends BaseGeneratorTileEntity implements ITickListener, IEnergySource, IWrenchableTile, IEUProducer, ITileActivityProvider {
    private int lowerProduction;

    public AdvancedLVPanelTile(BlockPos pos, BlockState state) {
        super(pos, state, 1);
        this.tier = 1;
        this.production = 8;
        this.lowerProduction = 4;
        this.maxStorage = 128;
    }

    @Override
    public boolean gainFuel() {
        return false;
    }

    @Override
    public boolean gainEnergy() {
        if(this.isConverting()){
            if(skyBlockCheck()){
                this.storage += this.isSunVisible(level) ? this.production : this.lowerProduction;
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
