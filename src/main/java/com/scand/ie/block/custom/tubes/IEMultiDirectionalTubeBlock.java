package com.scand.ie.block.custom.tubes;


import com.scand.ie.IEMod;
import ic2.core.block.transport.item.TubeBlocks;
import ic2.core.platform.rendering.IC2Textures;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class IEMultiDirectionalTubeBlock extends TubeBlocks.MultiDirectionalTubeBlock {


    String[] textures;

    public IEMultiDirectionalTubeBlock(String name, BlockEntityType<? extends BlockEntity> type, int searchState, String... texture) {
        super(name, type, searchState, texture);
        this.textures = texture;
    }

    @Override
    public TextureAtlasSprite getTexture(BlockState state, boolean center, Direction dir, Direction side) {
        return IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID,"transport/tube").get(this.textures[dir == state.getValue(FACING) ? 1 : 0]);

    }
}
