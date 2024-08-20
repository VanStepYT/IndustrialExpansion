package com.scand.ie.block;

import com.scand.ie.IEMod;
import ic2.core.block.misc.MachineBlock;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.utils.helpers.Tool;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class IEMachineBlock extends MachineBlock {
    private final String type;

    public IEMachineBlock(String blockName, String type) {
        super(blockName, type);
        this.setHarvestTool(Tool.PICKAXE.withLevel(1));
        this.type = type;
    }

    @Override
    public TextureAtlasSprite getSpriteForState(BlockState state, Direction side) {
        return (TextureAtlasSprite) IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID, this.type).get(side.getName());
    }
}
