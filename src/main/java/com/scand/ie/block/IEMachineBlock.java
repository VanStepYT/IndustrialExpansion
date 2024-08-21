package com.scand.ie.block;

import com.scand.ie.IEMod;
import ic2.core.block.base.features.IWrenchRemovable;
import ic2.core.block.misc.MachineBlock;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.utils.helpers.Tool;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IEMachineBlock extends MachineBlock implements IWrenchRemovable {
    private final String type;

    public IEMachineBlock(String blockName, String type) {
        super(blockName, type);
        this.setHarvestTool(Tool.PICKAXE.withLevel(1));
        this.type = type;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return List.of(this.asItem().getDefaultInstance());
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        return true;
    }

    @Override
    public TextureAtlasSprite getSpriteForState(BlockState state, Direction side) {
        return (TextureAtlasSprite) IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID, this.type).get(side.getName());
    }

    @Override
    public void setFacing(Direction direction) {
    }

    @Override
    public boolean canRemoveBlock(Player player) {
        return true;
    }

    @Override
    public double getDropRate(Player player) {
        return 1;
    }
}
