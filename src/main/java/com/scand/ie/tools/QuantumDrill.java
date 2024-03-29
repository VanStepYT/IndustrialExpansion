package com.scand.ie.tools;

import com.scand.ie.IEMod;
import ic2.api.items.electric.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.features.IMultiTargetTool;
import ic2.core.item.tool.electric.DrillTool;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.utils.collection.IterableWrapper;
import ic2.core.utils.helpers.StackUtil;
import ic2.core.utils.math.geometry.Box;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;

import java.util.Iterator;

public class QuantumDrill extends DrillTool implements IMultiTargetTool {
    public QuantumDrill() {
        super("quantum_drill", 4, 40, 540f);
        this.tier = 4;
        this.capacity = 100000;
    }
    @OnlyIn(Dist.CLIENT)
    public TextureAtlasSprite getTexture() {
        return IC2Textures.getMappedEntriesItem(IEMod.MOD_ID, "tools/drill").get("quantum");
    }
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (!ElectricItem.MANAGER.canUse(stack, this.getEnergyCost(stack))) {
            return 1.0F;
        } else if (this.isCorrectToolForDrops(stack, state)) {
            return 540f;
        } else {
            return 1.0F;
        }
    }

    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        if ((this.isMultiMining(stack)||this.isAdvancedMultiMining(stack)) && this.canMultiMine(stack)) {
            Level world = player.level;
            BlockHitResult ray = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
            int removed = 0;
            int getOffset = isAdvancedMultiMining(stack) ? 2 : 1;
            Iterator offsets = IterableWrapper.wrap(this.getHitPositions(stack, player, pos, ray.getDirection(), getOffset)).iterator();
            while(offsets.hasNext()) {
                BlockPos offset = (BlockPos)offsets.next();
                BlockState state = world.getBlockState(offset);
                if (!state.isAir() && state.getDestroySpeed(world, pos) >= 0.0F) {
                    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(world, offset, state, player);
                    MinecraftForge.EVENT_BUS.post(event);
                    if (!event.isCanceled()) {
                        BlockEntity tile = world.getBlockEntity(offset);
                        if (state.onDestroyedByPlayer(world, offset, player, true, state.getFluidState())) {
                            Block block = state.getBlock();
                            block.destroy(world, offset, state);
                            block.playerDestroy(world, player, offset, state, tile, stack);
                            if (event.getExpToDrop() != -1 && world instanceof ServerLevel) {
                                block.popExperience((ServerLevel) world, pos, event.getExpToDrop());
                            }

                            removed++;
                        }
                    }
                }
            }

            if (removed > 0) {
                ElectricItem.MANAGER.use(stack, this.getEnergyCost(stack), player);
                return true;
            }
        }

        return false;
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (IC2.KEYBOARD.isModeSwitchKeyDown(playerIn)) {
            ItemStack stack = playerIn.getItemInHand(handIn);
            CompoundTag data = stack.getOrCreateTag();
            boolean multiE = data.getBoolean("multi");
            boolean advE = data.getBoolean("advmulti");

            boolean multi = multiE;
            boolean adv = advE;
            int mode = 0;

            if(multiE){
                multi = false;
                adv = true;
                mode = 2;
            }
            else if (advE){
                multi = false;
                adv = false;
                mode = 0;
            }
            else{
                multi = true;
                adv = false;
                mode = 1;
            }

            data.putBoolean("multi", multi);
            data.putBoolean("advmulti", adv);
            if (IC2.PLATFORM.isSimulating()) {
                playerIn.displayClientMessage(this.translate(multi ? "tooltip.item.ic2.multi_mine.enable" : "tooltip.item.ic2.multi_mine.disable"), false);
                switch (mode){
                    case 0 -> playerIn.displayClientMessage(this.translate("tooltip.item.ie.multi_mode.normal"), false);
                    case 1 -> playerIn.displayClientMessage(this.translate("tooltip.item.ie.multi_mode.big"), false);
                    case 2 -> playerIn.displayClientMessage(this.translate("tooltip.item.ie.multi_mode.advbig"), false);

                }
            }

            return InteractionResultHolder.success(stack);
        } else {
            return super.use(worldIn, playerIn, handIn);
        }
    }

    public boolean canMultiMine(ItemStack stack) {
        return ElectricItem.MANAGER.canUse(stack, this.getEnergyCost(stack));
    }

    @Override
    public Iterator<BlockPos> getHitPositions(ItemStack itemStack, Player player, BlockPos blockPos, Direction direction) {
        int getOffset = isAdvancedMultiMining(itemStack) ? 2 : 1;
        return getHitPositions(itemStack,player,blockPos,direction,getOffset);
    }

    public boolean canAdvancedMultiMine(ItemStack stack) {
        return ElectricItem.MANAGER.canUse(stack, this.getEnergyCost(stack));
    }

    public boolean isMultiMining(ItemStack stack) {
        return StackUtil.getNbtData(stack).getBoolean("multi");
    }
    public boolean isAdvancedMultiMining(ItemStack stack) {
        return StackUtil.getNbtData(stack).getBoolean("advmulti");
    }

    public Iterator<BlockPos> getHitPositions(ItemStack stack, Player player, BlockPos pos, Direction dir, int expanding) {
        BlockPos Apos = pos.above(this.isAdvancedMultiMining(stack) && !(dir == Direction.UP || dir == Direction.DOWN)  ? 1:0);
        return Box.fromPos(Apos, true).expandSide(dir.getAxis(), expanding).iterator();
    }
}
