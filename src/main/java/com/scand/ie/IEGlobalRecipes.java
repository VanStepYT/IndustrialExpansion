package com.scand.ie;

import ic2.core.block.machines.recipes.IRecipeList;
import ic2.core.block.machines.recipes.MachineRecipeList;
import ic2.core.utils.collection.CollectionUtils;

import java.util.List;

public class IEGlobalRecipes {
    List<IRecipeList> recipes = CollectionUtils.createList();
    public MachineRecipeList autofarm;

}
