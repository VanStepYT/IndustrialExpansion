package com.scand.ie.ModItems.custom;

import ic2.api.reactor.IReactor;
import ic2.core.item.reactor.ReactorHeatVentItem;
import ic2.core.item.reactor.ReactorUraniumRod;
import ic2.core.item.reactor.base.IUraniumRod;
import net.minecraft.world.item.ItemStack;

public class ReactorUnbreakableRod extends ReactorUraniumRod {
    public ReactorUnbreakableRod(String itemName, String textureFolder, String textureName, IUraniumRod uranium, int rodCount, int componentId) {
        super(itemName, textureFolder, textureName, uranium, rodCount, componentId);
    }
}
