package com.scand.ie.block;

import com.scand.ie.IEMod;
import com.scand.ie.ModItems.ModItems;
import ic2.core.block.cables.CableBlock;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.platform.rendering.models.BaseModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpectralCableBlock extends CableBlock {
    public ItemStack getSpectralDrop(int insulation) {
        return new ItemStack(Items.RAW_IRON);
    }

    public final SpectralCableBlock.CableInstance INSTANCE = new CableBlock.CableInstance(false, false, false, 0, new float[]{3.0F}, "electric/cable", this::getSpectralDrop);

    protected SpectralCableBlock(String blockName, BlockEntityType<SpectralCable> cableTile) {
        super(blockName, cableTile);
        this.setOverrideRarity(Rarity.RARE);
    }

    @Override
    public SpectralCableBlock.CableInstance createInstance() {
        return INSTANCE;
    }
    @OnlyIn(Dist.CLIENT)
    public TextureAtlasSprite getTexture(BlockState state, boolean center, Direction dir, Direction side) {
        return (TextureAtlasSprite) IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID, "cables/spectral").get(side.getAxis().isHorizontal() ? "spectral_side" : "spectral_top");
    }
    @OnlyIn(Dist.CLIENT)
    public BaseModel getForCustomState(BlockState state) {
        return (BaseModel)((Integer)state.getValue(FOAMED) == 0 ? new SpectralCableModel(this, state) : super.getForCustomState(state));
    }

    public static SpectralCableBlock createSBlock(String name, final SpectralCableBlock.CableInstance cable, BlockEntityType<SpectralCable> creator) {
        return new SpectralCableBlock(name, creator) {
            public CableInstance createInstance() {
                return cable;
            }
        };
    }

}
