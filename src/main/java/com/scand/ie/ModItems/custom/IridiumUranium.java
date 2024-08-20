package com.scand.ie.ModItems.custom;

import com.scand.ie.ModItems.ModItems;
import ic2.core.item.reactor.urantypes.EnderUranium;
import ic2.core.item.reactor.urantypes.StandardUranium;
import ic2.core.item.reactor.urantypes.UraniumBaseType;
import ic2.core.platform.registries.IC2Items;
import ic2.core.utils.math.ColorUtils;
import net.minecraft.world.item.ItemStack;

public class IridiumUranium extends UraniumBaseType {
    public static final IridiumUranium INSTANCE = new IridiumUranium();

    private IridiumUranium() {
    }
    @Override
    public int getRodDurability() {
        return 10000000;
    }

    @Override
    public float getPulseEU() {
        return 8.0F;
    }
    @Override
    public int getUraniumPulses() {
        return 1;
    }

    @Override
    public int getPulsesForConnection() {
        return 0;
    }
    @Override
    public float getPulseHeatModifier() {
        return 10F;
    }
    @Override
    public float getExplosionModifier() {
        return 10F;
    }
    @Override
    public boolean isEnrichedUranium() {
        return true;
    }
    @Override
    public int getFusionHeat() {
        return 1;
    }

    @Override
    public ItemStack getBaseIngot() {
        return new ItemStack(IC2Items.ORE_IRIDIUM);
    }

    @Override
    public String getName() {
        return "iridium";
    }

    @Override
    public int getColor() {
        return ColorUtils.rgb(235, 255, 235, 255);
    }

    @Override
    public ItemStack createNearDepletedRod(int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack createReEnrichedRod() {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack createIsotopicRod() {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack createSingleRod() {
        return new ItemStack(ModItems.URANIUM_ROD_IRIDIUM_SINGLE.get());
    }

    @Override
    public ItemStack createDualRod() {
        return new ItemStack(ModItems.URANIUM_ROD_IRIDIUM_DUAL.get());
    }

    @Override
    public ItemStack createQuadRod() {
        return new ItemStack(ModItems.URANIUM_ROD_IRIDIUM_QUAD.get());
    }
}
