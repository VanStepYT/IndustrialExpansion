package com.scand.ie.block.custom;

import com.scand.ie.IEMod;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.components.simple.FlagBarComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Map;

public class AdvancedSolarPanelContainer extends ContainerComponent<AdvancedSolarPanelTileEntity> {
    ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/solar_panel.png");
    public static final Box2i MAIN_BOX = new Box2i(80, 45, 14, 14);
    public static final Vec2i MAIN_POS = new Vec2i(176, 0);
    public static final Vec2i SECOND_POS = new Vec2i(176, 14);


    public AdvancedSolarPanelContainer(AdvancedSolarPanelTileEntity key, Player player, int id) {
        super(key, player, id);
        this.addSlot(FilterSlot.createChargeSlot(key, key.tier, 0, 80, 26));
        this.addPlayerInventory(player.getInventory());
        this.addComponent(FlagBarComponent.createActiveBar(MAIN_BOX, key, MAIN_POS));

    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }
    public static Map<String, TextureAtlasSprite> getMappedEntriesGUI(String mod, String id) {
        return IC2Textures.getMappedEntries(new ResourceLocation(mod, "gui/" + id));
    }

}
