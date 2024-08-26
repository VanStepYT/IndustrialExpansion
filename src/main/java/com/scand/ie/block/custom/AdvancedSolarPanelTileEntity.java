package com.scand.ie.block.custom;

import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.tiles.impls.BaseGeneratorTileEntity;
import ic2.core.inventory.container.IC2Container;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AdvancedSolarPanelTileEntity extends BaseGeneratorTileEntity implements ITickListener {
    private boolean isVisible;

    public AdvancedSolarPanelTileEntity(BlockPos pos, BlockState state, int tier) {
        super(pos, state, 3);
        this.tier = tier;
        this.isVisible = false;
    }

    @Override
    public boolean gainFuel() {
        return false;
    }

    private int getTierProduction(){
        int t = this.getTier();
        return (int) (2*(Math.pow(4, t+1)));
    }

    @Override
    public float getEUProduction() {
        return isSunVisible(this.level, this.getBlockPos().above()) ? this.getTierProduction() :
                (float) this.getTierProduction() / 2;
    }


    public static boolean isSunVisible(Level world, BlockPos pos) {
        if (world.dimensionType().hasSkyLight() && world.isDay()) {
            if (!world.canSeeSkyFromBelowWater(pos)) {
                return false;
            } else {
                Biome biome = (Biome)world.getBiome(pos).value();
                if (biome.getPrecipitation() == Biome.Precipitation.NONE) {
                    return true;
                } else {
                    return !world.isRaining() && !world.isThundering();
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public void onTick() {
        super.onTick();
        this.setActive(isSunVisible(this.getLevel(), this.getBlockPos().above()));
        this.production = (int) this.getEUProduction();
        this.storage = this.production;
    }

    @Override
    public int getMaxFuel() {
        return 0;
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new AdvancedSolarPanelContainer(this, player, i);
    }
}
