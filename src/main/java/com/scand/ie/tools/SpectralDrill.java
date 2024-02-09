package com.scand.ie.tools;

import com.scand.ie.IEMod;
import ic2.api.items.electric.ElectricItem;
import ic2.core.IC2;
import ic2.core.block.machines.tiles.hv.MassFabricatorTileEntity;
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

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Iterator;

public class SpectralDrill extends DrillTool implements IMultiTargetTool {
    public SpectralDrill() {
        super("spectral_drill", 4, 20, 540f);
        this.tier = 4;
        this.capacity = 1000000;
    }
    @OnlyIn(Dist.CLIENT)
    public TextureAtlasSprite getTexture() {
        return IC2Textures.getMappedEntriesItem(IEMod.MOD_ID, "tools/drill").get("spectral");
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
        if ((this.isMultiMining(stack)||this.isAdvancedMultiMining(stack)||this.isSuperMultiMining(stack)) && this.canMultiMine(stack)) {
            Level world = player.level;
            BlockHitResult ray = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
            int removed = 0;
            Iterator offsets = IterableWrapper.wrap(this.getHitPositions(stack, player, pos, ray.getDirection())).iterator();
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
            boolean superE = data.getBoolean("supermulti");

            boolean multi = multiE;
            boolean adv = advE;
            boolean supra = superE;
            int mode = 0;

            if(multiE){
                multi = false;
                adv = true;
                supra = false;
                mode = 2;
            }
            else if (advE){
                multi = false;
                adv = false;
                supra = true;
                mode = 3;
            }
            else if (superE){
                multi = false;
                adv = false;
                supra = false;
                mode = 0;
            }
            else{
                multi = true;
                adv = false;
                supra = false;
                mode = 1;
            }

            data.putBoolean("multi", multi);
            data.putBoolean("advmulti", adv);
            data.putBoolean("supermulti", supra);
            if (IC2.PLATFORM.isSimulating()) {
                switch (mode){
                    case 0 -> playerIn.displayClientMessage(this.translate("tooltip.item.ie.multi_mode.normal"), false);
                    case 1 -> playerIn.displayClientMessage(this.translate("tooltip.item.ie.multi_mode.big"), false);
                    case 2 -> playerIn.displayClientMessage(this.translate("tooltip.item.ie.multi_mode.advbig"), false);
                    case 3 -> playerIn.displayClientMessage(this.translate("tooltip.item.ie.multi_mode.superbig"), false);
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
        int expand = 0;
        if(this.isMultiMining(itemStack)){expand=1;}
        if(this.isAdvancedMultiMining(itemStack)){expand=2;}
        if(this.isSuperMultiMining(itemStack)){expand=3;}
        IEMod.LOGGER.info("setting expanding for: "+String.valueOf(expand));

        return getHitPositions(itemStack,player,blockPos,direction,expand);
    }

    public boolean isMultiMining(ItemStack stack) {
        return StackUtil.getNbtData(stack).getBoolean("multi");
    }
    public boolean isAdvancedMultiMining(ItemStack stack) {
        return StackUtil.getNbtData(stack).getBoolean("advmulti");
    }

    public boolean isSuperMultiMining(ItemStack stack) {
        IEMod.LOGGER.info("The nbt tag is: "+String.valueOf(StackUtil.getNbtData(stack).getBoolean("supermulti")));
        return StackUtil.getNbtData(stack).getBoolean("supermulti");
    }

    public Iterator<BlockPos> getHitPositions(ItemStack stack, Player player, BlockPos pos, Direction dir, int expanding) {
        BlockPos aPos = pos;
        if(dir != Direction.UP && dir != Direction.DOWN) {
            if (this.isAdvancedMultiMining(stack)) {
                aPos = aPos.above(1);
            }
            if (this.isSuperMultiMining(stack)) {
                aPos = aPos.above(2);
            }
        }

        IEMod.LOGGER.info("expanding for: "+String.valueOf(expanding));

        return Box.fromPos(aPos, true).expandSide(dir.getAxis(), expanding).iterator();
    }
}
