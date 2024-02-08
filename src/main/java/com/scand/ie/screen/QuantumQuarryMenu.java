package com.scand.ie.screen;

import com.scand.ie.block.ModBlocks;
import com.scand.ie.block.QuantumQuarryBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class QuantumQuarryMenu extends AbstractContainerMenu {
    public final QuantumQuarryBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;


    public QuantumQuarryMenu(int id, Inventory inv, FriendlyByteBuf extraData){
        this(id,inv,inv.player.level.getBlockEntity(extraData.readBlockPos())
                , new SimpleContainerData(2));
    }


    public QuantumQuarryMenu(int id, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypes.QUANTUM_QUARRY_MENU.get(), id);
        checkContainerSize(inv, 18);
        blockEntity = (QuantumQuarryBlockEntity) entity;
        this.level = inv.player.level;
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler ->
        {
            this.addSlot(new SlotItemHandler(handler, 0, 12, 15));
            this.addSlot(new SlotItemHandler(handler, 1, 30, 15));
            this.addSlot(new SlotItemHandler(handler, 2, 48, 15));
            this.addSlot(new SlotItemHandler(handler, 3, 66, 15));
            this.addSlot(new SlotItemHandler(handler, 4, 84, 15));
            this.addSlot(new SlotItemHandler(handler, 5, 102, 15));
            this.addSlot(new SlotItemHandler(handler, 6, 12, 33));
            this.addSlot(new SlotItemHandler(handler, 7, 30, 33));
            this.addSlot(new SlotItemHandler(handler, 8, 48, 33));
            this.addSlot(new SlotItemHandler(handler, 9, 66, 33));
            this.addSlot(new SlotItemHandler(handler, 10, 84, 33));
            this.addSlot(new SlotItemHandler(handler, 11, 102, 33));
            this.addSlot(new SlotItemHandler(handler, 12, 12, 51));
            this.addSlot(new SlotItemHandler(handler, 13, 30, 51));
            this.addSlot(new SlotItemHandler(handler, 14, 48, 51));
            this.addSlot(new SlotItemHandler(handler, 15, 66, 51));
            this.addSlot(new SlotItemHandler(handler, 16, 84, 51));
            this.addSlot(new SlotItemHandler(handler, 17, 102, 51));
        });

        addDataSlots(data);
    }

    public int getScaledEU(){

        int eu = this.data.get(0);
        int need = this.data.get(1);  // Max Progress
        float mult = 63f/need;// This is the height in pixels of EU bar
        int r = (int) (eu*mult);

        return r;
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 18;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                ModBlocks.QUANTUM_QUARRY.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
