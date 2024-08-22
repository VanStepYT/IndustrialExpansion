package com.scand.ie.tools;

import com.scand.ie.IEMod;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.item.tool.electric.ElectricWrenchTool;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.utils.tooltips.ToolTipHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class QuantumWrench extends ElectricWrenchTool {
    public QuantumWrench() {
        super("quantum_wrench", (new PropertiesBuilder()).group(IEMod.IE).setNoRepair().maxStackSize(1));
        this.capacity = 160000;
        this.tier = 4;
        this.transferLimit = 3000;
        this.toolDamage = 300;
        this.chanceModifier = 8.0;
        this.losslessUses = 60;
    }
    public TextureAtlasSprite getTexture() {
        return (TextureAtlasSprite) IC2Textures.getMappedEntriesItem("ie","tools/wrench").get("quantum");
    }

    @Override
    public void addToolTip(ItemStack stack, Player player, TooltipFlag type, ToolTipHelper helper) {
        helper.addSimpleToolTip("tooltip.item.ic2.electric_wrench.losslessWrenchInfiniteUses");
    }

    public void onLossPrevented(Player player, ItemStack stack) {
    }

    @Override
    public boolean canOverrideLoss(ItemStack stack) {
        return true;
    }

    @Override
    public double getActualLoss(ItemStack stack, double originalLoss) {
        return 1f;
    }
}
