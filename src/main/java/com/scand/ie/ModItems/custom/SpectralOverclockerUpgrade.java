package com.scand.ie.ModItems.custom;

import ic2.api.tiles.IMachine;
import ic2.core.item.upgrades.base.BaseUpgradeItem;
import ic2.core.item.upgrades.speed.QuantumOverclockerUpgradeItem;
import net.minecraft.world.item.ItemStack;

public class SpectralOverclockerUpgrade extends BaseUpgradeItem.SimpleUpgradeItem {

    public SpectralOverclockerUpgrade() {
        super("spectral_overclocker_upgrade");
    }

    @Override
    public UpgradeType getType(ItemStack itemStack) {
        return UpgradeType.RECIPE_MOD;
    }

    @Override
    public double getProcessingSpeedMultiplier(ItemStack stack, IMachine machine) {
        return 2;
    }

    @Override
    public double getEnergyDemandMultiplier(ItemStack stack, IMachine machine) {
        return 1.1;
    }
}
