package com.scand.ie.block.custom.wind;

import ic2.core.block.generators.containers.WindmillContainer;
import ic2.core.block.generators.tiles.WindmillTileEntity;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.filter.SpecialFilters;
import ic2.core.inventory.gui.components.simple.AreaOfEffectComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.utils.math.geometry.Box2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class AdvancedWindmillContainer extends ContainerComponent<AdvancedWindmillTileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("ic2", "textures/gui_sprites/blocks/generators/gui_windmill.png");

    public AdvancedWindmillContainer(AdvancedWindmillTileEntity key, Player player, int id) {
        super(key, player, id);
        this.addSlot(new FilterSlot(key, 0, 80, 26, SpecialFilters.WINDMILL_ROTOR));
        this.addPlayerInventory(player.getInventory());
        this.addComponent(new AreaOfEffectComponent(key, new Box2i(119, 70, 50, 12)));
    }

    public ResourceLocation getTexture() {
        return TEXTURE;
    }
}
