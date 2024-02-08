package com.scand.ie.block;

import com.scand.ie.screen.QuantumQuarryMenu;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.tiles.readers.IEUStorage;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.tiles.BaseTileEntity;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class QuantumQuarryBlockEntity extends BaseTileEntity implements
        MenuProvider, IEnergySink, IEUStorage, IWrenchableTile {

    protected final ContainerData data;
    private int EU = 0;
    private int maxEU = 1000000;

    private final ItemStackHandler itemHandler = new ItemStackHandler(18){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private boolean addedToEnergyNet;

    public QuantumQuarryBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 -> QuantumQuarryBlockEntity.this.EU;
                    case 1 -> QuantumQuarryBlockEntity.this.maxEU;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int v) {
                switch (i){
                    case 0 -> QuantumQuarryBlockEntity.this.EU = v;
                    case 1 -> QuantumQuarryBlockEntity.this.maxEU = v;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };

    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlockEntities.QUANTUM_QUARRY.get();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Quantum Quarry");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new QuantumQuarryMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    public void onLoaded() {
        super.onLoaded();
        if (this.isSimulating() && !this.addedToEnergyNet) {
            this.addedToEnergyNet = true;
            EnergyNet.INSTANCE.addTile(this);
        }

    }
    public void onUnloaded(boolean chunk) {
        if (this.isSimulating() && this.addedToEnergyNet) {
            this.addedToEnergyNet = false;
            EnergyNet.INSTANCE.removeTile(this);
        }

        super.onUnloaded(chunk);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level level, BlockPos blockPos,
                            BlockState state, QuantumQuarryBlockEntity pEntity) {
        if(level.isClientSide){
            return;
        }
        /*LootTable table = level.getServer().getLootTables().get(new ResourceLocation(IEMod.MOD_ID, "quarry_loot"));
        LootContext ctx = new LootContext.Builder((ServerLevel) level)
                .withRandom(level.random)
                .create(
                        LootContextParamSet.builder().build()
                );
        List<ItemStack> items = table.getRandomItems(ctx);
        Item item = items.get(0).getItem();*/
        List<Item> items = List.of(Items.RAW_IRON,Items.RAW_GOLD,Items.RAW_COPPER, IC2Items.RAW_ALUMINIUM,
                IC2Items.RAW_SILVER,IC2Items.RAW_TIN,Items.DIAMOND,Items.COAL,Items.REDSTONE,
                Items.LAPIS_LAZULI,Items.EMERALD);
        Item item = items.get(new Random().nextInt(items.size()));


        if(hasRecipe(pEntity, item)!=420){
            //pEntity.EU++;
            setChanged(level, blockPos, state);

            if(pEntity.EU >= pEntity.maxEU){
                craftItem(pEntity, item);
                pEntity.resetProgress();
            }
        }else {
            pEntity.resetProgress();
        }
    }


    private void resetProgress() {
        this.EU=0;
    }

    private static void craftItem(QuantumQuarryBlockEntity pEntity, Item item) {
        if(hasRecipe(pEntity, item)!=420){
            pEntity.itemHandler.setStackInSlot(hasRecipe(pEntity, item), new ItemStack(item,
                    pEntity.itemHandler.getStackInSlot(hasRecipe(pEntity, item)).getCount()+1));
        }
    }

    private static int hasRecipe(QuantumQuarryBlockEntity pEntity, Item item) {
        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for (int i = 0; i< pEntity.itemHandler.getSlots(); i++){
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }
        for (int index = 0; index < pEntity.itemHandler.getSlots(); index++) {
            if(canInsertAmountIntoOutputSlot(inventory, index) && canInsertItemIntoOutputSlot(inventory,
                    new ItemStack(item, 1), index)){
                return index;
            }
        }
        return 420;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack, int i) {
        return inventory.getItem(i).getItem() == stack.getItem() || inventory.getItem(i).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, int i) {
        return inventory.getItem(i).getMaxStackSize() > inventory.getItem(i).getCount();
    }

    @Override
    public int getSinkTier() {
        return 6;
    }

    @Override
    public int getRequestedEnergy() {
        return maxEU-EU;
    }

    @Override
    public int acceptEnergy(Direction direction, int amount, int voltage) {
        EU += amount;

        return 0;
    }

    @Override
    public boolean canAcceptEnergy(IEnergyEmitter iEnergyEmitter, Direction direction) {
        return true;
    }

    @Override
    public int getStoredEU() {
        return EU;
    }

    @Override
    public int getMaxEU() {
        return maxEU+1;
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public boolean canSetFacing(Direction direction) {
        return false;
    }

    @Override
    public boolean canRemoveBlock(Player player) {
        return true;
    }

    @Override
    public double getDropRate(Player player) {
        return 1.0;
    }
}
