package com.scand.ie.block.custom.wind;

import com.scand.ie.IEMod;
import com.scand.ie.block.ModBlocks;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.items.IWindmillBlade;
import ic2.api.network.tile.PacketRange;
import ic2.api.tiles.IEnergyStorage;
import ic2.api.tiles.readers.IAirSpeed;
import ic2.api.tiles.readers.IEUProducer;
import ic2.api.tiles.readers.IEUStorage;
import ic2.api.util.DirectionList;
import ic2.core.IC2;
import ic2.core.block.base.IC2Block;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.features.multiblock.IMultiBlockClickable;
import ic2.core.block.base.features.multiblock.IMultiBlockFluidExporter;
import ic2.core.block.base.features.multiblock.ITexturedMultiBlock;
import ic2.core.block.base.misc.comparator.ComparatorNames;
import ic2.core.block.base.misc.comparator.types.base.WindComparator;
import ic2.core.block.base.tiles.BaseLinkingTileEntity;
import ic2.core.block.base.tiles.BaseMultiBlockTileEntity;
import ic2.core.block.base.tiles.BaseMultiElectricTileEntity;
import ic2.core.block.base.tiles.impls.BaseGeneratorTileEntity;
import ic2.core.block.generators.BaseGeneratorBlock;
import ic2.core.block.generators.tiles.WindTurbineTileEntity;
import ic2.core.block.generators.tiles.WindmillTileEntity;
import ic2.core.block.machines.ColossalMachineBlock;
import ic2.core.block.machines.tiles.ev.ColossalFurnace;
import ic2.core.block.rendering.world.impl.MultiBlock;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import ic2.core.platform.events.MultiBlockManager;
import ic2.core.platform.events.StructureManager;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.rendering.features.ITextureProvider;
import ic2.core.platform.wind.WindManager;
import ic2.core.utils.collection.CollectionUtils;
import ic2.core.utils.config.ic2.IC2Config;
import ic2.core.utils.helpers.capabilities.IToggleableCapabilityProvider;
import ic2.core.utils.math.StructureBuilder;
import ic2.core.utils.math.geometry.Box;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Debug;

import java.util.Iterator;
import java.util.Map;

public class WindFarmTileEntity extends BaseMultiElectricTileEntity implements IEUProducer, IEUStorage, IEnergySource, ITexturedMultiBlock {

    public int storage;
    public float production;

    public static final ITextureProvider PROVIDER = ITextureProvider.noState("ie","advanced_windmill");

    public WindFarmTileEntity(BlockPos pos, BlockState state) {
        super(pos, state, 1, 32768, 40000);
    }

    @Override
    public int getSourceTier() {
        return 4;
    }

    @Override
    public int getMaxEnergyOutput() {
        return IC2.CONFIG.windMillOutput.get()*4;
    }

    @Override
    public int getProvidedEnergy() {
        return (int) this.production;
    }

    @Override
    public void onStructureTick() {
        super.onStructureTick();

        this.production = 100;

        double air = WindManager.INSTANCE.getAirSpeed(this.getLevel(), this.getBlockPos(), this.getFacing().toYRot(), 90);

        this.storage+=this.production;
    }

    @Override
    public void onTick() {
        this.checkStructure();

        if (this.isValid) {
            this.onStructureTick();
            this.handleComparators();
        }
    }

    @Override
    public void consumeEnergy(int i) {

    }

    @Override
    public boolean canEmitEnergy(IEnergyAcceptor iEnergyAcceptor, Direction direction) {
        return this.isValid;
    }

    public boolean isStateStillValid(BlockPos actualPos, BlockPos structurePos, BlockState newState) {
        return newState.getBlock() == IC2Blocks.COLOSSAL_BASE;
    }

    public int getStructureSize(){
        return 2;
    }

    public boolean testStructure(StructureBuilder builder) {
        int radius = builder.findRadius(3, true, this::valid);
        if (radius <= 2) {
            return false;
        } else {
            for(int i = 1; builder.cubicStructure(i, radius + 1, radius + 1, radius + 1); ++i) {
                if (!builder.isBlock(IC2Blocks.COLOSSAL_BASE)) {
                    return false;
                }

                this.children.add((BaseLinkingTileEntity)builder.getTile(BaseLinkingTileEntity.class));
            }

            return true;
        }
    }

    protected boolean valid(StructureBuilder builder) {
        if (!builder.isBlock(IC2Blocks.COLOSSAL_BASE)) {
            return false;
        } else {
            BaseLinkingTileEntity tile = (BaseLinkingTileEntity)builder.getTile(BaseLinkingTileEntity.class);
            return tile != null && tile.getMaster() == null;
        }
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.ADVANCED_WINDMILL_TYPE;
    }

    @Override
    public float getEUProduction() {
        return this.production;
    }

    @Override
    public ITextureProvider getMasterTexture() {
        return PROVIDER;
    }
}
