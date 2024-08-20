package com.scand.ie.block.custom.steam;

import com.scand.ie.IEMod;
import ic2.api.network.buffer.NetworkInfo;
import ic2.core.block.base.cache.ICache;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.IWrenchRemovable;
import ic2.core.block.base.misc.BucketFiller;
import ic2.core.block.base.misc.comparator.ComparatorNames;
import ic2.core.block.base.misc.comparator.types.base.EUComparator;
import ic2.core.block.base.misc.comparator.types.base.TankComparator;
import ic2.core.block.base.misc.comparator.types.special.HeatComparator;
import ic2.core.block.base.tiles.BaseElectricTileEntity;
import ic2.core.block.base.tiles.impls.machine.single.BaseMachineTileEntity;
import ic2.core.block.generators.containers.FuelBoilerContainer;
import ic2.core.block.generators.tiles.FuelBoilerTileEntity;
import ic2.core.block.personal.tile.PersonalTankTileEntity;
import ic2.core.fluid.ExtractionTank;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import ic2.core.platform.registries.IC2Fluids;
import ic2.core.platform.registries.IC2Tiles;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.wiki.components.TextComponent;
import ic2.probeplugin.info.machines.BaseMachineComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.checkerframework.checker.units.qual.C;

public class ElectricBoilerTile extends BaseElectricTileEntity implements IFluidHandler, IWrenchRemovable, ITickListener, ITileGui {

    public static final BlockEntityType<ElectricBoilerTile> ELECTRIC_BOILER_TYPE =
            IC2Tiles.createTile("electric_boiler", ElectricBoilerTile::new);


    public static final Box2i CHARGE_BOX = new Box2i(82, 35, 14, 14);;
    public BucketFiller waterFiller;
    @NetworkInfo
    public IC2Tank waterTank = new IC2Tank(16000, (T) -> T.getFluid() == Fluids.WATER);
    @NetworkInfo
    public ExtractionTank steamTank = new ExtractionTank(128000);
    private int progress;
    public static final int neededEnergy = 32;

    public ElectricBoilerTile(BlockPos pos, BlockState state) {
        super(pos, state, 2, 128, 2048);
        this.progress=0;
        this.addCapability(ForgeCapabilities.FLUID_HANDLER, this);
        this.waterFiller = new BucketFiller(this, this.waterTank, 0, 1);
        this.waterTank.addListener((T) -> {
            this.updateGuiField("waterTank");
        });
        this.steamTank.addListener((T) -> {
            this.updateGuiField("steamTank");
        });
        this.addComparator(new EUComparator("eu", ComparatorNames.EU_STORAGE, this));
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
        return ELECTRIC_BOILER_TYPE;
    }

    @Override
    public int getTanks() {
        return 2;
    }

    public FluidStack getFluidInTank(int tank) {
        return ((IC2Tank)(tank == 0 ? this.waterTank : this.steamTank)).getFluid();
    }

    public int getTankCapacity(int tank) {
        return ((IC2Tank)(tank == 0 ? this.waterTank : this.steamTank)).getCapacity();
    }

    public boolean isFluidValid(int tank, FluidStack stack) {
        return tank == 0 && stack.getFluid() == Fluids.WATER;
    }


    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return this.waterTank.fill(resource, action);
    }

    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        return this.steamTank.drain(resource, action);
    }

    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        return this.steamTank.drain(maxDrain, action);
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0f;
    }

    @Override
    public void onTick() {
        this.waterFiller.fillTank();
        if(this.energy>= neededEnergy){
            if(this.waterTank.getFluidAmount() >= 8){
                this.progress++;
                this.energy-=neededEnergy;this.waterTank.drain(8, FluidAction.EXECUTE);
                this.steamTank.fillInternal(new FluidStack(IC2Fluids.STEAM, 4), FluidAction.EXECUTE);
            }
        }
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new ElectricBoilerContainer(this,player,i);
    }



    @Override
    public boolean canInteractWith(Player player) {
        return true;
    }

    public Box2i getChargeBox() {
        return CHARGE_BOX;
    }
}
