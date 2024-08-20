package com.scand.ie.block.custom;

import com.scand.ie.ModItems.ModItems;
import com.scand.ie.block.ModBlocks;
import ic2.api.core.IC2Classic;
import ic2.api.energy.EnergyNet;
import ic2.api.tiles.readers.IProgressMachine;
import ic2.api.util.DirectionList;
import ic2.core.block.base.cache.CapabilityCache;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.ITileActivityProvider;
import ic2.core.block.base.tiles.BaseElectricTileEntity;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import ic2.core.item.base.IC2Item;
import ic2.core.platform.registries.IC2Items;
import ic2.core.wiki.components.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MassMultiplicator extends BaseElectricTileEntity implements ITickListener, ITileGui, IProgressMachine, ITileActivityProvider {
    private final CapabilityCache listeners;
    public int progress;

    public MassMultiplicator(BlockPos pos, BlockState state) {
        super(pos, state, 1, 524288, 70000100);
        this.listeners = new CapabilityCache(this, DirectionList.ALL, IC2Classic.NOTIFY_CAPABILITY);
        this.addedToEnet = false;
        this.maxInput =  524288;
        this.maxEnergy = 70000100;
        this.tier = EnergyNet.INSTANCE.getTierFromPower(maxInput);
        this.baseTier = this.tier;
    }

    @Override
    public int acceptEnergy(Direction side, int amount, int voltage) {
        if (amount <= this.maxEnergy && amount > 0) {
            int added = Math.min(amount, this.maxEnergy - this.energy);
            if (added > 0) {
                if(added/10000 > 1){
                    this.energy *= (int) (added /100000);
                }
                else{
                    this.energy+=added;
                }

                this.updateGuiField("energy");
                this.handleNonTickComparators();
                if (this.needsUpdateTick() && this instanceof ITickListener && !this.isTicking()) {
                    this.addToTick();
                }
            }

            return amount - added;
        } else {
            return 0;
        }
    }

    @Override
    public int getRequestedEnergy() {
        return this.maxEnergy - this.energy;
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.MASS_MULTIPLIER_TYPE;
    }

    @Override
    public float getProgress() {
        return this.energy;
    }

    @Override
    public float getMaxProgress() {
        return this.maxEnergy;
    }

    @Override
    public void onTick() {
        if(this.energy>=this.maxEnergy){
            this.setOrGrow(0,new ItemStack(IC2Items.UUMATTER), false);
            this.energy=1;
        }

    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new MassMultiplicatorContainer(this,player,i);
    }
}
