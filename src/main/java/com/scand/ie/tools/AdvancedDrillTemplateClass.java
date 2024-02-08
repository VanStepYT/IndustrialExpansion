package com.scand.ie.tools;

import com.scand.ie.IEMod;
import ic2.api.items.electric.ElectricItem;
import ic2.core.item.base.features.IMultiTargetTool;
import ic2.core.item.tool.electric.DrillTool;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.utils.math.geometry.Box;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;

public class AdvancedDrillTemplateClass extends DrillTool implements IMultiTargetTool {

    @OnlyIn(Dist.CLIENT)
    public TextureAtlasSprite getTexture() {
        return IC2Textures.getMappedEntriesItem(IEMod.MOD_ID, "tools/drill").get("nano");
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return this.miningSpeed;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        if(this.isMultiMining(itemstack) && canMultiMine(itemstack)){

        }
    }

    @Override
    public boolean isMultiMining(ItemStack itemStack) {
        return ElectricItem.MANAGER.canUse(itemStack, this.getEnergyCost(itemStack));
    }

    @Override
    public boolean canMultiMine(ItemStack itemStack) {
        return false;
    }

    @Override
    public Iterator<BlockPos> getHitPositions(ItemStack itemStack, Player player, BlockPos blockPos, Direction direction) {
        return Box.fromPos(blockPos, true).expandSide(direction.getAxis(), isAdvancedMultiMine() ? 1:2).iterator();
    }

    private boolean isAdvancedMultiMine() {
        return false;
    }
}
