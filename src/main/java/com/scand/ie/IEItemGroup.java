package com.scand.ie;

import com.scand.ie.ModItems.ModItems;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class IEItemGroup extends CreativeModeTab {
    public IEItemGroup() {
        super("ie");
    }
    @Override
    public ItemStack makeIcon() {
        ItemStack stack = new ItemStack(ModItems.SINGULARITY.get());
        return stack;
    }
}
