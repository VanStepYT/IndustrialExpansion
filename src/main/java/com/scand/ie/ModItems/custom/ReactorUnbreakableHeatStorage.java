package com.scand.ie.ModItems.custom;

import ic2.api.reactor.IReactor;
import ic2.core.item.reactor.ReactorHeatStorageItem;
import ic2.core.item.reactor.ReactorHeatVentItem;
import ic2.core.utils.tooltips.ToolTipHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class ReactorUnbreakableHeatStorage extends ReactorHeatStorageItem {

    public ReactorUnbreakableHeatStorage(String name, int maxDamage, String textureFolder, String textureName, int componentId) {
        super(name, maxDamage, textureFolder, textureName, componentId);
    }


    @Override
    public int storeHeat(ItemStack stack, IReactor reactor, int x, int y, int heatChange) {
        return 0;
    }
}
