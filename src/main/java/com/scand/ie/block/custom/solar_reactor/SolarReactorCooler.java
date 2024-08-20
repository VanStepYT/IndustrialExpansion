package com.scand.ie.block.custom.solar_reactor;

import com.scand.ie.IEMod;
import ic2.core.item.base.IC2SimpleItem;
import ic2.core.item.base.PropertiesBuilder;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Nullable;

public class SolarReactorCooler extends IC2SimpleItem {

    public final int coolSpeed;

    public SolarReactorCooler(String itemName, int coolspeed, int maxDamage, String textureName) {
        super(itemName, new PropertiesBuilder().group(IEMod.IE).maxDamage(maxDamage),
                "reactor/solar/cooler",
                textureName);
        this.coolSpeed = coolspeed;
    }

}
