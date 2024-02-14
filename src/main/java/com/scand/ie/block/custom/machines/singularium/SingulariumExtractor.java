package com.scand.ie.block.custom.machines.singularium;

import com.scand.ie.IEMod;
import com.scand.ie.block.ModBlocks;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.IC2;
import ic2.core.block.base.features.IParticleSpawner;
import ic2.core.block.base.tiles.impls.machine.single.BasicMachineTileEntity;
import ic2.core.platform.registries.IC2Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SingulariumExtractor extends BasicMachineTileEntity implements IParticleSpawner {
    public static final ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/singularium_extractor.png");
    public SingulariumExtractor(BlockPos pos, BlockState state) {
        super(pos, state,3, 3, 8192, 35, 2097152);
    }

    protected ResourceLocation getWorkingSound() {
        return IC2Sounds.MACERATOR_PROCESSING;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.SINGULARIUM_EXTRACTOR_TYPE;
    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return (IC2.RECIPES.get(this.isSimulating())).extractor;
    }

    @Override
    public void animationTick(RandomSource randomSource) {

    }
}
