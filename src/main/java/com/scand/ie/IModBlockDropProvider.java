package com.scand.ie;

import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.drops.IBlockDropProvider;
import net.minecraft.world.item.ItemStack;

public interface IModBlockDropProvider {
    IBlockDropProvider SELF_OR_NANO = new IBlockDropProvider.SelfOrOther(() -> {
        return new ItemStack(ModBlocks.NANO_MACHINE_CASING.get());
    });
}
