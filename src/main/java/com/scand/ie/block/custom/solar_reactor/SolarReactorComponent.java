package com.scand.ie.block.custom.solar_reactor;

import com.scand.ie.IEMod;
import ic2.core.item.base.IC2SimpleItem;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.item.reactor.ReactorUraniumRod;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class SolarReactorComponent extends IC2SimpleItem {
    public final int euGen;
    public final int heatGen;

    public final boolean day;

    public SolarReactorComponent(String itemName, int eugen, int heatgen, int maxDamage, Boolean isDay, String textureName) {
        super(itemName, new PropertiesBuilder().maxDamage(maxDamage).group(IEMod.IE),
                isDay ? "reactor/solar/component/day" : "reactor/solar/component/night",
                textureName);
        this.euGen = eugen;
        this.heatGen = heatgen;
        this.day = isDay;
    }

    public void update(ItemStack stack){
        stack.setDamageValue(stack.getDamageValue()+1);
        if(stack.getDamageValue()>=stack.getMaxDamage()){
            stack.setCount(0);
        }
    }
}
