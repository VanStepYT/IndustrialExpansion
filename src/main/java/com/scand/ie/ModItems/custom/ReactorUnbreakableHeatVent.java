package com.scand.ie.ModItems.custom;

import ic2.api.reactor.IReactor;
import ic2.core.item.reactor.ReactorHeatVentItem;
import ic2.core.item.reactor.base.ReactorHeatStorageBase;
import net.minecraft.world.item.ItemStack;

public class ReactorUnbreakableHeatVent extends ReactorHeatVentItem {
    public ReactorUnbreakableHeatVent(String name, VentProperty props) {
        super(name, props);
    }

    @Override
    public int storeHeat(ItemStack stack, IReactor reactor, int x, int y, int heatChange) {
        return 0;
    }
}
