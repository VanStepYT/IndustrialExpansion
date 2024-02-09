package com.scand.ie.block.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.scand.ie.IEMod;
import ic2.api.core.IC2Classic;
import ic2.api.energy.EnergyNet;
import ic2.api.util.DirectionList;
import ic2.core.block.base.cache.CapabilityCache;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.IC2Screen;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.platform.rendering.features.ITextureProvider;
import ic2.core.platform.rendering.features.providers.NoStateProvider;
import ic2.core.utils.helpers.Formatters;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.DecimalFormat;
import java.util.Map;

public class IridiumFabricatorContainer extends ContainerComponent<IridiumFabricatorTile> {

    public IridiumFabricatorContainer(IridiumFabricatorTile key, Player player, int id) {
        super(key, player, id);
        this.addSlot(FilterSlot.createOutputSlot(key, 0, 80, 34));
        this.addPlayerInventory(player.getInventory());
        this.addComponent(new IridiumFabricatorComponent(key));
    }

    ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/iridium_fabricator.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        //Map<String, TextureAtlasSprite> map = IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID, "iridium_fabricator");
        return TEXTURE;
    }
}
