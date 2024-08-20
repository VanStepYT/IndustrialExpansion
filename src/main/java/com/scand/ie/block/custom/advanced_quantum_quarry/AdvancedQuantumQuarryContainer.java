package com.scand.ie.block.custom.advanced_quantum_quarry;

import com.scand.ie.IEMod;
import com.scand.ie.block.custom.advanced_quantum_quarry.AdvancedQuantumQuarryTile;
import ic2.api.items.electric.IMiningDrill;
import ic2.api.tiles.readers.IProgressMachine;
import ic2.core.block.machines.containers.lv.MinerContainer;
import ic2.core.block.machines.tiles.lv.MinerTileEntity;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.filter.ClassFilter;
import ic2.core.inventory.gui.components.simple.ProgressComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.item.misc.BatteryItem;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class AdvancedQuantumQuarryContainer extends ContainerComponent<AdvancedQuantumQuarryTile> {


    public AdvancedQuantumQuarryContainer(AdvancedQuantumQuarryTile key, Player player, int id) {
        super(key, player, id);
        int index = -1;
        this.addSlot(new FilterSlot(key, 15, 135, 33, new ClassFilter(IMiningDrill.class)));
        this.addSlot(new FilterSlot(key, 16, 135, 15, new ClassFilter(BatteryItem.class)));
        this.addComponent(new ProgressComponent(new Box2i(156,13,8,64), (IProgressMachine) key, new Vec2i(176,0),true));
        for(int y = 0; y < 3; y++){
            for(int x = 0; x <5; x++){
                index += 1;
                int slotX = 12 + (x * 18);
                int slotY = 15 + (y * 18);
                this.addSlot(FilterSlot.createOutputSlot(key,index,slotX,slotY));
            }
        }
        this.addPlayerInventory(player.getInventory());
    }

    ResourceLocation TEXTURE = new ResourceLocation(IEMod.MOD_ID, "textures/gui/advanced_quantum_quarry.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        //Map<String, TextureAtlasSprite> map = IC2Textures.getMappedEntriesBlock(IEMod.MOD_ID, "iridium_fabricator");
        return TEXTURE;
    }
}
