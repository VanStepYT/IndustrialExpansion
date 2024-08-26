package com.scand.ie.block.custom.machines;

import com.scand.ie.IEMod;
import com.scand.ie.block.ModBlocks;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.IC2;
import ic2.core.block.base.features.IParticleSpawner;
import ic2.core.block.base.tiles.BaseLinkingTileEntity;
import ic2.core.block.base.tiles.impls.machine.multi.DynamicColossalMachineTileEntity;
import ic2.core.block.base.tiles.impls.machine.single.BasicMachineTileEntity;
import ic2.core.block.machines.tiles.ev.ColossalFurnace;
import ic2.core.fluid.InsertionTank;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Sounds;
import ic2.core.platform.rendering.features.ITextureProvider;
import ic2.core.utils.math.StructureBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SpectralCompressor extends BasicMachineTileEntity implements IParticleSpawner {
    public static final ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/quantum_macerator.png");
    public SpectralCompressor(BlockPos pos, BlockState state) {
        super(pos, state,3, 32, 75, 2048);
    }

    protected ResourceLocation getWorkingSound() {
        return IC2Sounds.MACERATOR_PROCESSING;
    }




    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.SPECTRAL_MACERATOR_TYPE;
    }




    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return (IC2.RECIPES.get(this.isSimulating())).macerator;
    }

    @Override
    public void animationTick(RandomSource randomSource) {

    }
}
