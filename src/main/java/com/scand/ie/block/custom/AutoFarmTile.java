package com.scand.ie.block.custom;

import com.scand.ie.IERecipes;
import com.scand.ie.block.ModBlocks;
import ic2.api.recipes.ingridients.inputs.IInput;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.IC2;
import ic2.core.block.base.tiles.impls.machine.single.BasicMachineTileEntity;
import ic2.core.block.machines.tiles.lv.MaceratorTileEntity;
import ic2.core.platform.recipes.misc.GlobalRecipes;
import ic2.core.platform.recipes.misc.GlobalRecipesClient;
import ic2.core.platform.registries.IC2Recipes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AutoFarmTile extends BasicMachineTileEntity {
    public static final ResourceLocation TEXTURE = new ResourceLocation("ie", "textures/gui/gui_autofarm.png");

    public AutoFarmTile(BlockPos pos, BlockState state) {
        super(pos, state, 3, 64, 512, 256);
    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    @Override
    public void consumeInput(IInput[] inputs, int slot, boolean emptyContainer) {
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.AUTO_FARM_TILE;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return IERecipes.AUTOFARM;
    }
}
