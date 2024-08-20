package com.scand.ie.block.custom.advanced_quantum_quarry;

import com.scand.ie.IEMod;
import com.scand.ie.ModItems.ModItems;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.tiles.readers.IProgressMachine;
import ic2.api.util.DirectionList;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.tiles.BaseElectricTileEntity;
import ic2.core.block.machines.tiles.hv.MassFabricatorTileEntity;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import ic2.core.inventory.handler.AccessRule;
import ic2.core.inventory.handler.InventoryHandler;
import ic2.core.inventory.handler.SlotType;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvancedQuantumQuarryTile extends BaseElectricTileEntity implements IProgressMachine, ITileGui, IEnergySink, ITickListener {

    public static final BlockEntityType<AdvancedQuantumQuarryTile> TYPE = IC2Tiles.createTile("advanced_quantum_quarry",AdvancedQuantumQuarryTile::new);
    public Map<Item, Integer> batteryMap = new HashMap<>();
    public AdvancedQuantumQuarryTile(BlockPos pos, BlockState state) {
        super(pos, state, 17, 131072, 1000000000);
        batteryMap.put(IC2Items.RE_BATTERY, 100000000);
        batteryMap.put(IC2Items.ENERGY_CRYSTAL, 50000000);
        batteryMap.put(IC2Items.LAPATRON_CRYSTAL, 10000000);
        batteryMap.put(IC2Items.GLOWTRONIC_CRYSTAL, 5000000);
        batteryMap.put(IC2Items.UESC, 1000000);
        batteryMap.put(IC2Items.PESD, 500000);
        batteryMap.put(ModItems.UTEC.get(), 100000);
        batteryMap.put(ModItems.SPECTRAL_CRYSTAL.get(), 50000);
        batteryMap.put(ModItems.SINGULARITY_CRYSTAL.get(), 10000);
    }

    @Override
    protected void addSlotInfo(InventoryHandler handler) {
        handler.registerBlockAccess(DirectionList.ALL, AccessRule.BOTH);
        handler.registerSlotAccess(AccessRule.EXPORT, 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        handler.registerSlotAccess(AccessRule.IMPORT, 15, 16);
        handler.registerNamedSlot(SlotType.INPUT, 15, 16);

        handler.registerNamedSlot(SlotType.OUTPUT, 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14);
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public BlockEntityType<?> createType() {
        return TYPE;
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new AdvancedQuantumQuarryContainer(this,player,i);
    }





    @Override
    public void onTick() {

        if(!this.getStackInSlot(16).isEmpty()){
            if(this.getProgress() >= batteryMap.getOrDefault(getStackInSlot(16).getItem(), 100000000)){
                Map<Item, Integer> drillmap = new HashMap<>();
                drillmap.put(IC2Items.DRILL, 1);
                drillmap.put(IC2Items.DRILL_DIAMOND, 2);
                drillmap.put(IC2Items.DRILL_ADVANCED, 9);
                drillmap.put(ModItems.NANO_DRILL.get(), 12);
                drillmap.put(ModItems.QUANTUM_DRILL.get(), 25);
                drillmap.put(ModItems.SPECTRAL_DRILL.get(), 49);

                List<Item> items = List.of(Items.RAW_IRON,Items.RAW_GOLD,Items.RAW_COPPER, IC2Items.RAW_ALUMINIUM,
                        IC2Items.RAW_SILVER,IC2Items.RAW_TIN,Items.DIAMOND,Items.COAL,Items.REDSTONE,
                        Items.LAPIS_LAZULI,Items.EMERALD, IC2Items.ORE_URANIUM_DROP);

                int miningRange = drillmap.getOrDefault(this.getStackInSlot(15).getItem(), 1);

                for(int i = 0; i < miningRange; i++){
                    Item ore = items.get(this.getLevel().getRandom().nextInt(12));
                    if(canCraft(ore) != 420){
                        craft(ore, canCraft(ore));
                    }
                }
                this.energy=0;
            }
        }

    }


    private void craft(Item ore, int index) {
        setOrGrow(index, new ItemStack(ore), false);
    }

    private boolean canPlaceItem(int slot,ItemStack itemstack){
        ItemStack stack = this.getStackInSlot(slot);
        if(stack.isEmpty()){
            return true;
        }
        else if(stack.getItem() == itemstack.getItem()){
            if(stack.getCount() + itemstack.getCount() <= stack.getMaxStackSize()){
                return true;
            }
        }
        return false;
    }


    private int canCraft(Item ore) {
        int output = 420;
        for(int a = 0; a < 15; a++){
            if(this.canPlaceItem(a, ore.getDefaultInstance())){
                output = a;
                a=18;
            }
        }
        return output;
    }

    @Override
    public float getProgress() {
        return energy;
    }

    @Override
    public float getMaxProgress() {
        return batteryMap.getOrDefault(getStackInSlot(16).getItem(), 1000000000);
    }
}
