package com.scand.ie.block.custom.whitehole;

import com.scand.ie.ModDamageTypes;
import com.scand.ie.ModItems.ModItems;
import com.scand.ie.block.ModBlocks;
import ic2.core.IC2;
import ic2.core.audio.AudioManager;
import ic2.core.audio.AudioSourceClient;
import ic2.core.audio.IAudioSource;
import ic2.core.audio.providers.SimplePosition;
import ic2.core.block.base.features.IAreaOfEffect;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.tiles.BaseElectricTileEntity;
import ic2.core.block.machines.tiles.lv.MaceratorTileEntity;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.List;

public class InvertedControllerTile extends BaseElectricTileEntity implements ITickListener {

    int visualId = -1;

    IAudioSource audio;
    private int progress;
    private SimplePosition audioPos;

    public InvertedControllerTile(BlockPos pos, BlockState state) {
        super(pos, state, 1, Integer.MAX_VALUE, 0);}

    @Override
    public void onLoaded() {
        super.onLoaded();
        if(this.isRendering()){
            this.audioPos = new SimplePosition(this.getLevel(), this.getPosition().above(14));
            this.audio = IC2.AUDIO.createSource(this.audioPos, this.getWorkingSound(), AudioManager.SoundType.STATIC, 2F, true, false);
            this.audio.play();
        }
    }

    @Override
    public void onUnloaded(boolean chunk) {
        super.onUnloaded(chunk);
        IC2.AUDIO.removeSource(audioPos);
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
            if(getDistance(player, pos)<2){
                if(!player.isCreative() && !player.isSpectator()){
                    player.hurt(ModDamageTypes.BLACK_HOLE, 1f);
                }

            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void bounce(BlockPos pos){
        Player[] players = this.getLevel().players().toArray(new Player[0]);

        for (Player player : players){
            if(getDistance(player, pos)<5){
                Entity player1 = player;
                player1.setDeltaMovement(-player1.getDeltaMovement().get(Direction.Axis.X)*12,
                        -player1.getDeltaMovement().get(Direction.Axis.Y)*12,
                        -player1.getDeltaMovement().get(Direction.Axis.Z)*12);
                player1.hurtMarked=true;
            }
        }

    }

    public double getDistance(Entity entity, BlockPos pos){
        double paramX = entity.getX() - pos.getX();
        double paramY = entity.getY() - pos.getY();
        double paramZ = entity.getZ() - pos.getZ();

        return Math.sqrt((paramX * paramX)+(paramY * paramY)+(paramZ * paramZ));

    }



    public boolean checkForSingularity(BlockPos pos){
        assert level != null;

        return true;

    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.WHITE_HOLE_CONTROLLER_TYPE;
    }

    @Override
    public void onTick() {
        if(checkForSingularity(this.getPosition())){
            boolean b = level.setBlockAndUpdate(this.getPosition().above(14), ModBlocks.INVERTED_SINGULARITY.get().defaultBlockState());
            setActive(true);
            this.progress++;

            if(this.progress%20 == 0){
                removeBlocks(this.getPosition().above(14), 13);
                killPlayers(this.getPosition().above(14));

                this.progress=0;
            }
            if(this.progress%2 == 0){
            bounce(this.getPosition().above(14));}
        }
        else {
            setActive(false);
            boolean b = level.setBlockAndUpdate(this.getPosition().above(8), Blocks.AIR.defaultBlockState());
        }

    }

    protected ResourceLocation getWorkingSound() {
        return new ResourceLocation("ie", "sounds/machines/white_hole.ogg");
    }

    public void removeBlocks(BlockPos pos, int radius){
        for(int x = -radius; x<=radius; x++){
            for(int y = -radius; y<=radius; y++){
                for(int z = -radius; z<=radius; z++){
                    BlockPos pos1 = pos.west(x).north(y).above(z);
                    if(this.level.getBlockState(pos1).getBlock() != ModBlocks.INVERTED_SINGULARITY.get()){
                        this.level.destroyBlock(pos1, true);
                    }
                }
            }
        }
    }

    public int getProgress() {
        return this.progress;
    }

}
