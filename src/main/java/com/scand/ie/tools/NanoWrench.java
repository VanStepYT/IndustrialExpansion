package com.scand.ie.tools;

import com.scand.ie.IEMod;
import ic2.core.IC2;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.item.tool.electric.ElectricWrenchTool;
import ic2.core.platform.rendering.IC2Textures;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class NanoWrench extends ElectricWrenchTool {
    public NanoWrench() {
        super("nano_wrench", (new PropertiesBuilder()).group(IEMod.IE).setNoRepair().maxStackSize(1));
        this.capacity = 80000;
        this.tier = 3;
        this.transferLimit = 1000;
        this.toolDamage = 100;
        this.chanceModifier = 4.0;
        this.losslessUses = 60;
    }
    public TextureAtlasSprite getTexture() {
        return (TextureAtlasSprite) IC2Textures.getMappedEntriesItem("ie","tools/wrench").get("nano");
    }
}
