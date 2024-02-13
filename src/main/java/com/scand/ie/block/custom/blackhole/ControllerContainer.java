package com.scand.ie.block.custom.blackhole;

import com.scand.ie.IEMod;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.components.simple.AreaOfEffectComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.utils.math.geometry.Box2i;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ControllerContainer extends ContainerComponent<ControllerTile> {

    private final ControllerTile tile;
    ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/controller.png");

    public ControllerContainer(ControllerTile key, Player player, int id) {

        super(key, player, id);
        this.tile = key;
        this.addSlot(FilterSlot.createOutputSlot(key, 0, 80, 34));
        this.addPlayerInventory(player.getInventory());
        this.addComponent(new AreaOfEffectComponent(key, new Box2i(119, 70, 50, 12)));
        //this.addComponent(new ControllerComponent(key));

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        return TEXTURE;
    }
}
