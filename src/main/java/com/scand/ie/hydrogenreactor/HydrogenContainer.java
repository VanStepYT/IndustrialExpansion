package com.scand.ie.hydrogenreactor;

import com.scand.ie.IEMod;
import ic2.api.items.electric.IMiningDrill;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.filter.ClassFilter;
import ic2.core.inventory.filter.SetItemFilter;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class HydrogenContainer extends ContainerComponent<HydrogenReactorTile> {

    public HydrogenContainer(HydrogenReactorTile key, Player player, int id) {
        super(key, player, id);
        this.addSlot(new FilterSlot(key, 0, 54, 35, new SetItemFilter(IC2Items.CELL_WATER)));
        this.addSlot(FilterSlot.createOutputSlot(key, 1, 112, 35));
        this.addPlayerInventory(player.getInventory());
    }


    ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/hydrogen_reactor.png");
    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }
}
