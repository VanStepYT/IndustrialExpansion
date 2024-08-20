package com.scand.ie.block.custom.solar_reactor;

import com.scand.ie.Config;
import com.scand.ie.block.ModBlocks;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tiles.readers.IActivityProvider;
import ic2.api.tiles.readers.IEUProducer;
import ic2.core.block.base.features.ITileActivityProvider;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.tiles.impls.BaseGeneratorTileEntity;
import ic2.core.entity.explosion.IC2Explosion;
import ic2.core.inventory.container.IC2Container;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class SolarReactorTile extends BaseGeneratorTileEntity implements IWrenchableTile, IEnergySource, IActivityProvider, IEUProducer, ITileActivityProvider {

    public int heat;
    ItemStack[] items = new ItemStack[56];

    public int generation;
    private int progress;
    public int heating;

    public SolarReactorTile(BlockPos pos, BlockState state) {
        super(pos, state, 56);
        this.tier = 7;
        this.production = generation;
        this.maxStorage = 400000;
    }

    public boolean gainEnergy() {
        this.production=this.generation;
        if(this.isConverting()){
            this.storage += this.production;
            return true;
        }
        return false;
    }

    private boolean hasSignal(BlockPos pos){
        for(Direction d : Direction.values()){
            if(this.getLevel().getSignal(pos, d)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onTick() {
        if(this.hasSignal(this.getPosition())) {
            this.progress++;
            if(this.progress%20==0){
                this.process();
                this.setActive(true);

            }
            if(this.progress%30==0){
                this.updateItemList();
            }
            this.gainEnergy();
        }
        else {
            this.setActive(false);
        }
    }

    public void updateItemList(){
        for(int i = 0; i<56; i++){
            this.items[i] = getStackInSlot(i);
        }
    }

    public int cooling;

    public void process(){
        int gen = 0;
        int coolingg = 0;
        int heatingg = 0;
        List<ItemStack> preventers = new ArrayList<>();
        for(ItemStack itemStack : this.items){
            if(itemStack != null){
                Item item = itemStack.getItem();
                if(item instanceof SolarReactorComponent){
                    SolarReactorComponent currentComponent = (SolarReactorComponent) item;
                    if (currentComponent.day){
                        if(isDay()){
                            currentComponent.update(itemStack);
                            //this.storage += currentComponent.euGen;
                            this.heat += currentComponent.heatGen;
                            gen += currentComponent.euGen;
                            heatingg += currentComponent.heatGen;
                        }
                    }
                    else{
                        if(!isDay()){
                            currentComponent.update(itemStack);
                            //this.storage += currentComponent.euGen;
                            this.heat += currentComponent.heatGen;
                            gen += currentComponent.euGen;
                            heatingg += currentComponent.heatGen;
                        }
                    }

                }
                if(item instanceof SolarReactorCooler){
                    SolarReactorCooler currentCooler = (SolarReactorCooler) item;
                    this.heat-=currentCooler.coolSpeed;
                    coolingg += currentCooler.coolSpeed;
                    itemStack.setDamageValue(itemStack.getDamageValue()+1);
                    if(itemStack.getDamageValue()>=itemStack.getMaxDamage()){
                        itemStack.setCount(0);
                    }
                }
                if(item instanceof SolarReactorPreventer) {
                    preventers.add(itemStack);
                }
            }
        }
        this.generation = gen;
        this.cooling = coolingg;
        this.heating = heatingg;
        if(this.getHeat() >= this.getMaxHeat()){
            if(!preventers.isEmpty()){
                ItemStack preventer = preventers.get(0);
                preventer.setDamageValue(preventer.getDamageValue()+1);
                this.heat=0;
                if(preventer.getDamageValue()>=preventer.getMaxDamage()){
                    preventer.setCount(0);
                }
            }
            else{
                this.explode();
            }
        }
        if(this.heat<0){
            this.heat=0;
        }
        this.production = this.generation;
    }

    private boolean isDay() {
        return this.getLevel().isDay() && !this.getLevel().isThundering() && !this.getLevel().isRaining() ;
    }

    @Override
    public boolean gainFuel() {
        return false;
    }

    private void explode(){
        /*this.getLevel().explode(this.getLevel().getServer().getPlayerList().getPlayers().toArray(new Player[0])[0],this.getPosition().getX(), this.getPosition().getY(),
                this.getPosition().getZ(),55f, Explosion.BlockInteraction.DESTROY);*/

        IC2Explosion explosion = (new IC2Explosion(this.level, this.getLevel().getServer().getPlayerList().getPlayers().get(0),
                new Vec3(this.getPosition().getX()+0.5f, this.getPosition().getY()+0.5f, this.getPosition().getZ()+0.5f), 25, 0.1f, DamageSource.ANVIL));
        explosion.doExplosion();
        this.getLevel().removeBlock(this.getPosition(), false);

    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.SOLAR_REACTOR_TYPE;
    }

    public boolean isConverting(){
        return this.storage + this.production <= this.maxStorage;
    }


    @Override
    public int getMaxFuel() {
        return 0;
    }

    public int getHeat() {
        return this.heat;
    }

    public int getMaxHeat() {
        return 10001;
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new SolarReactorContainer(this,player,i);
    }

    @Override
    public int getStoredEU() {
        return this.storage;
    }


    public float getCooling() {
        return this.cooling;
    }

    @Override
    public float getEUProduction() {
        return 0;
    }

    public float getGeneration() {
        return generation;
    }

    public float getMaxGeneration() {
        return 16000;
    }
}
