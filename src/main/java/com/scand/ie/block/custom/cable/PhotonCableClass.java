package com.scand.ie.block.custom.cable;

import com.scand.ie.IEMod;
import com.scand.ie.block.ModBlocks;
import ic2.core.IC2;
import ic2.core.block.cables.CableBlock;
import ic2.core.block.cables.CableTileEntity;
import ic2.core.platform.rendering.IC2Textures;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PhotonCableClass extends CableBlock {

    protected PhotonCableClass(String blockName, BlockEntityType<? extends CableTileEntity> cableTile) {
        super(blockName, cableTile);
    }


    public static PhotonCableClass createBlock(String name, final CableInstance cable, BlockEntityType<? extends CableTileEntity> creator) {
        return new PhotonCableClass(name, creator) {
            public CableInstance createInstance() {
                return cable;
            }
        };
    }



    @Override
    public CableInstance createInstance() {
        return new
                CableBlock.CableInstance(false,false,false,0, new float[]{3.0F},
                "electric/cable", ModBlocks::getPhotonDrop).addTextures(new String[]{"photon"});

    }

    @Override
    public TextureAtlasSprite getTexture(BlockState state, boolean center, Direction dir, Direction side) {

        return IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID, "electric/cable").get("photon");
    }
}
