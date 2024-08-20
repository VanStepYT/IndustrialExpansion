package com.scand.ie.hydrogenreactor;

import com.scand.ie.Config;
import com.scand.ie.IEMod;
import com.scand.ie.ModItems.ModItems;
import com.scand.ie.block.ModBlocks;
import com.scand.ie.block.custom.steam.ElectricBoilerTile;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.network.buffer.NetworkInfo;
import ic2.api.tiles.readers.IActivityProvider;
import ic2.api.tiles.readers.IEUProducer;
import ic2.api.util.DirectionList;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.misc.BucketFiller;
import ic2.core.block.base.tiles.impls.BaseGeneratorTileEntity;
import ic2.core.entity.explosion.IC2Explosion;
import ic2.core.fluid.ExtractionTank;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.container.IC2Container;
import ic2.core.inventory.handler.AccessRule;
import ic2.core.inventory.handler.InventoryHandler;
import ic2.core.inventory.handler.SlotType;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Tiles;
import ic2.core.utils.collection.IterableWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.apache.logging.log4j.core.util.ObjectArrayIterator;
import org.stringtemplate.v4.misc.ArrayIterator;

import java.util.*;

public class HydrogenReactorTile extends BaseGeneratorTileEntity implements IEUProducer, IEnergySource, ITickListener, IActivityProvider, IWrenchableTile {


    private int multi;
    private float generation;
    public float heat;
    public int counter;

    public HydrogenReactorTile(BlockPos pos, BlockState state) {
        super(pos, state, 2);
        this.addCapability(ForgeCapabilities.FLUID_HANDLER, this);
        this.tier = 5;
        this.multi = 128;
        this.maxStorage = 1310720;
        this.production=0;
        this.counter = 0;
    }

    @Override
    public boolean gainFuel() {
        return false;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.HYDROGEN_REACTOR_TYPE;
    }



    @Override
    public boolean gainEnergy() {
        this.getHeat();
        if (this.storage+this.production <= this.maxStorage) {
            this.storage += this.production;
            return true;
        }
        return false;
    }
    public static final List<Block> heatBlocks = List.of(new Block[]{ModBlocks.HYDROGEN_HEATER.get()});
    public static final List<Block> coolingBlocks = List.of(new Block[]{ModBlocks.HYDROGEN_COOLER.get()});
    public static final Map<Block, Float> machineBlockMap = new HashMap<Block,Float>();

    static {
        machineBlockMap.put(IC2Blocks.MACHINE_BLOCK, 0.02f);
        machineBlockMap.put(IC2Blocks.ADVANCED_MACHINE_BLOCK, 0.03f);
        machineBlockMap.put(IC2Blocks.STABILIZED_MACHINE_BLOCK, 0.04f);
        machineBlockMap.put(IC2Blocks.COLOSSAL_BASE, 0.05f);
        machineBlockMap.put(ModBlocks.SILVER_THERMOELEMENT.get(), 0.12f);
        machineBlockMap.put(ModBlocks.ALUMINIUM_THERMOELEMENT.get(), 0.16f);
    }

    public void getHeat(){
        float heat = 0f;
        float cooling = 0f;
        BlockState[] blockStates = (this.getLevel().getBlockStates(new AABB(this.getPosition())
                .inflate(5)).toList().toArray(new BlockState[0]));

        Iterator<BlockState> iterator = new ObjectArrayIterator<>(blockStates);
        float multa = 1f;
        while (iterator.hasNext()){
            Block block = iterator.next().getBlock();

            if(heatBlocks.contains(block)){
                heat+=2f;
            }
            else if(coolingBlocks.contains(block)){
                cooling+=1f;
            }
            else if (machineBlockMap.containsKey(block)) {
                multa += machineBlockMap.getOrDefault(block,0f);
            }
        }
        this.heat = heat - (cooling*multa);
        if (heat > 10f){
            if (this.inventory.get(0).getCount() > 0){
                this.production = Math.round(heat*this.multi);
                this.counter++;
                if (this.counter%20000/heat*30 == 0){
                    this.setOrGrow(1, new ItemStack(ModItems.HYDROGEN_CELL.get(), 1), true);
                    this.inventory.get(0).shrink(1);
                    this.counter = 0;

                }
                if (this.heat > 10f){
                    this.explode();
                }

            }else{
                this.production = 0;
            }
        }else{
            this.production = 0;
        }

    }

    @Override
    protected void addSlotInfo(InventoryHandler handler) {
        handler.registerBlockSides(DirectionList.ALL);
        handler.registerBlockAccess(DirectionList.ALL, AccessRule.BOTH);

        handler.registerSlotsForSide(DirectionList.ALL, 0,1);

        handler.registerSlotAccess(AccessRule.EXPORT, 1);
        handler.registerSlotAccess(AccessRule.IMPORT, 0);

        handler.registerNamedSlot(SlotType.INPUT, 0);
        handler.registerNamedSlot(SlotType.OUTPUT, 1);

    }

    @Override
    public float getEUProduction() {
        return 0;
    }

    @Override
    public int getMaxFuel() {
        return 0;
    }

    private void explode(){
        //this.getLevel().explode(this.getLevel().getServer().getPlayerList().getPlayers().toArray(new Player[0])[0],this.getPosition().getX(), this.getPosition().getY(),
          //      this.getPosition().getZ(),55f, Explosion.BlockInteraction.BREAK);

        IC2Explosion explosion = (new IC2Explosion(this.level, this.getLevel().getServer().getPlayerList().getPlayers().get(0),
                new Vec3(this.getPosition().getX()+0.5f, this.getPosition().getY()+0.5f, this.getPosition().getZ()+0.5f), Config.solarReactorExplosionPower.get()+1, 0.1f, DamageSource.ANVIL));
        explosion.doExplosion();
        this.getLevel().removeBlock(this.getPosition(), false);
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new HydrogenContainer(this,player,i);
    }

    @Override
    public boolean hasGui(Player player, InteractionHand hand, Direction side) {
        return true;
    }
}
