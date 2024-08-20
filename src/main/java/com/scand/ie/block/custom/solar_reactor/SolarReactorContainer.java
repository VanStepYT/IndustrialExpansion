package com.scand.ie.block.custom.solar_reactor;

import com.scand.ie.IEMod;
import ic2.core.block.base.misc.comparator.types.special.HeatComparator;
import ic2.core.block.generators.containers.ReactorContainer;
import ic2.core.block.machines.containers.lv.RareEarthExtractorContainer;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.filter.ClassFilter;
import ic2.core.inventory.gui.IC2Screen;
import ic2.core.inventory.gui.components.simple.ProgressComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.inventory.slot.SlotBase;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SolarReactorContainer extends ContainerComponent<SolarReactorTile> {
    public SolarReactorContainer(SolarReactorTile key, Player player, int id) {
        super(key, player, id);
        this.addPlayerInventoryWithOffset(player.getInventory(),0,56);
        this.addComponent(new SolarGenComponent(new Box2i(22, 7,8,64), key,new Vec2i(194,0),true));
        this.addComponent(new SolarHeatComponent(new Box2i(156, 7,8,64), key,new Vec2i(176,0),true));
        this.addComponent(new SolarCoolComponent(new Box2i(145, 7,8,64), key,new Vec2i(185,0),true));
        int index = -1;
        for(int y = 0; y < 7; y++){
            for(int x = 0; x <8; x++){
                index += 1;
                int slotX = 17 + (x * 18);
                int slotY = 5 + (y * 18);
                this.addSlot(new SlotBase(key,index,slotX,slotY));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void onGuiLoaded(IC2Screen screen) {
        screen.setYSize(221);
    }

    ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/solar_reactor.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        //Map<String, TextureAtlasSprite> map = IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID, "iridium_fabricator");
        return TEXTURE;
    }
}
