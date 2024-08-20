package com.scand.ie.block.custom.solar_reactor;

import com.scand.ie.IEMod;
import ic2.core.item.base.IC2SimpleItem;
import ic2.core.item.base.PropertiesBuilder;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Nullable;

public class SolarReactorPreventer extends IC2SimpleItem {

    public SolarReactorPreventer(String itemName, int preventCount, String textureName) {
        super(itemName, new PropertiesBuilder().maxDamage(preventCount).group(IEMod.IE),
                "reactor/solar/preventer", textureName);
    }
}
