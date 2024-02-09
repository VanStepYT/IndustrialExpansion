package com.scand.ie.block.custom;

import com.scand.ie.IEMod;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.slot.FilterSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpectraliumFabricatorContainer extends ContainerComponent<SpectraliumFabricatorTile> {

    public SpectraliumFabricatorContainer(SpectraliumFabricatorTile key, Player player, int id) {
        super(key, player, id);
        this.addSlot(FilterSlot.createOutputSlot(key, 0, 80, 34));
        this.addPlayerInventory(player.getInventory());
        this.addComponent(new SpectraliumFabricatorComponent(key));
    }

    ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/spectralium_fabricator.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        //Map<String, TextureAtlasSprite> map = IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID, "iridium_fabricator");
        return TEXTURE;
    }
}
