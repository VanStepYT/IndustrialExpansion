package com.scand.ie.block;

import com.scand.ie.IEMod;
import com.scand.ie.IERecipes;
import com.scand.ie.block.custom.AutoFarmTile;
import ic2.api.recipes.registries.IElectrolyzerRecipeList;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.block.base.tiles.impls.machine.single.BaseMachineTileEntity;
import ic2.core.block.machines.recipes.ElectrolyzerRecipeList;
import ic2.core.block.machines.recipes.MachineRecipeList;
import ic2.jeiplugin.JEIModule;
import ic2.jeiplugin.core.recipes.categories.BasicMachineCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEIIntegration implements IModPlugin {
    public static final RecipeType<IMachineRecipeList.RecipeEntry> AUTOFARM = RecipeType.create(IEMod.MOD_ID, "autofarm", IMachineRecipeList.RecipeEntry.class);
    //The entire thing is borrowed from Advanced-Solars-Classic
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(IEMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.AUTOFARM.get()), AUTOFARM);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (!JEIModule.ALLOWS_LOADING) return;
        registration.addRecipeCategories(new BasicMachineCategory(registration.getJeiHelpers().getGuiHelper(), AUTOFARM, AutoFarmTile.TEXTURE, ModBlocks.AUTOFARM.get()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if(!JEIModule.ALLOWS_LOADING) return;
        registration.addRecipes(AUTOFARM, IERecipes.AUTOFARM.getAllEntries());
    }
}
