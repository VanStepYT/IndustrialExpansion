package com.scand.ie.block.custom.blackhole;

import com.scand.ie.ModDamageTypes;
import com.scand.ie.ModItems.ModItems;
import com.scand.ie.block.ModBlocks;
import com.scand.ie.block.custom.SpectraliumFabricatorContainer;
import ic2.core.IC2;
import ic2.core.audio.AudioManager;
import ic2.core.audio.ISoundProvider;
import ic2.core.block.base.features.IAreaOfEffect;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.tiles.BaseElectricTileEntity;
import ic2.core.block.generators.components.WindTurbineComponent;
import ic2.core.block.generators.containers.WindTurbineContainer;
import ic2.core.block.generators.tiles.WaterMillTileEntity;
import ic2.core.block.generators.tiles.WindTurbineTileEntity;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import ic2.core.platform.registries.IC2Sounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.jmx.Server;

public class ControllerTile extends BaseElectricTileEntity implements ITickListener, ITileGui, IAreaOfEffect {
    private int progress;
    private final int maxProgress = 1000;

    int visualId = -1;

    public ControllerTile(BlockPos pos, BlockState state) {
        super(pos, state, 1, Integer.MAX_VALUE, 0);}

    @Override
    public void onLoaded() {
        super.onLoaded();
        IC2.AUDIO.createSource(this, this.getWorkingSound(), AudioManager.SoundType.STATIC, 1F, true, false);
    }

    @Override
    public void onUnloaded(boolean chunk) {
        super.onUnloaded(chunk);
        IC2.AUDIO.removeSource(this);
    }

    @Override
    public boolean supportsNotify() {
        return false;
    }

    @Override
    public int getRequestedEnergy() {
        return 0;
    }

    public void killPlayers(BlockPos pos){
        Player[] players = this.getLevel().players().toArray(new Player[0]);

        if (this.getLevel().isClientSide()){
            return;
        }

        for (Player player : players){
            if(getDistance(player, pos)<4){
                if(!player.isCreative() && !player.isSpectator()){
                    player.hurt(ModDamageTypes.BLACK_HOLE, 10f);
                }

            }
        }
    }

    public double getDistance(Entity entity, BlockPos pos){
        double paramX = entity.getX() - pos.getX();
        double paramY = entity.getY() - pos.getY();
        double paramZ = entity.getZ() - pos.getZ();

        return Math.sqrt((paramX * paramX)+(paramY * paramY)+(paramZ * paramZ));

    }

    public AABB getAreaOfEffect() {
        return (new AABB(this.worldPosition.above(8))).inflate(7);
    }

    public int getAreaOfEffectColor() {
        return 0x7FFF0000;
    }

    public void setVisualizationId(int id) {
        this.visualId = id;
    }

    public int getVisualizationId() {
        return this.visualId;
    }



    public boolean checkForSingularity(BlockPos pos){
        assert level != null;
        for(int i=0; i >= 7; i++){
            if(!isAir(this.level.getBlockState(pos.above(i)).getBlock())){
                return false;
            }
        }
        if(this.level.getBlockState(pos.above(8)).getBlock() == ModBlocks.SINGULARITY.get() ||
                isAir(this.level.getBlockState(pos.above(8)).getBlock())){
            return true;
        }
        return false;

    }
    private boolean isAir(Block block){
        return block == Blocks.AIR || block == Blocks.VOID_AIR || block == Blocks.CAVE_AIR;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.BLACK_HOLE_CONTROLLER_TYPE;
    }

    @Override
    public void onTick() {
        if(checkForSingularity(this.getPosition())){
            boolean b = level.setBlockAndUpdate(this.getPosition().above(8), ModBlocks.SINGULARITY.get().defaultBlockState());
            setActive(true);
            this.progress++;
            if(this.progress>=this.maxProgress){
                this.setOrGrow(0,new ItemStack(ModItems.DARK_MATTER_SHARD.get()),false);
                this.progress=0;
            }

            if(this.progress%20 == 0){
                removeBlocks(this.getPosition().above(8), 7);
                killPlayers(this.getPosition().above(8));
            }
        }
        else {
            setActive(false);
            boolean b = level.setBlockAndUpdate(this.getPosition().above(8), Blocks.AIR.defaultBlockState());
        }
        IC2.AUDIO.playSound(this,getWorkingSound(), AudioManager.SoundType.STATIC);
    }

    protected ResourceLocation getWorkingSound() {
        return new ResourceLocation("ie", "sounds/machines/black_hole.ogg");
    }

    public void removeBlocks(BlockPos pos, int radius){
        for(int x = -radius; x<=radius; x++){
            for(int y = -radius; y<=radius; y++){
                for(int z = -radius; z<=radius; z++){
                    BlockPos pos1 = pos.west(x).north(y).above(z);
                    if(this.level.getBlockState(pos1).getBlock() != ModBlocks.SINGULARITY.get()){
                        this.level.destroyBlock(pos1, true);
                    }
                }
            }
        }
    }



    public int getProgress() {
        return this.progress;
    }

    public int getMaxProgress() {
        return this.maxProgress;
    }



    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new ControllerContainer(this,player,i);
    }
}
