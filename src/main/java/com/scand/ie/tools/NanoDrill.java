package com.scand.ie.tools;

import com.scand.ie.IEMod;
import ic2.api.items.electric.ElectricItem;
import ic2.api.items.electric.IMiningDrill;
import ic2.core.IC2;
import ic2.core.item.base.features.IMultiTargetTool;
import ic2.core.item.tool.electric.AdvancedDrill;
import ic2.core.item.tool.electric.DrillTool;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Stats;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
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

public class NanoDrill extends DrillTool implements IMultiTargetTool {
    public NanoDrill() {
        super("nano_drill", 4, 40, 540f);
        this.tier = 4;
        this.capacity = 35000;
    }
    @OnlyIn(Dist.CLIENT)
    public TextureAtlasSprite getTexture() {
        return IC2Textures.getMappedEntriesItem(IEMod.MOD_ID, "tools/drill").get("nano");
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
        if (this.isMultiMining(stack) && this.canMultiMine(stack)) {
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
            boolean multi = !data.getBoolean("multi");
            data.putBoolean("multi", multi);
            if (IC2.PLATFORM.isSimulating()) {
                playerIn.displayClientMessage(this.translate(multi ? "tooltip.item.ic2.multi_mine.enable" : "tooltip.item.ic2.multi_mine.disable"), false);
            }

            return InteractionResultHolder.success(stack);
        } else {
            return super.use(worldIn, playerIn, handIn);
        }
    }

    public boolean canMultiMine(ItemStack stack) {
        return ElectricItem.MANAGER.canUse(stack, this.getEnergyCost(stack) * 9);
    }

    public boolean isMultiMining(ItemStack stack) {
        return StackUtil.getNbtData(stack).getBoolean("multi");
    }

    public Iterator<BlockPos> getHitPositions(ItemStack stack, Player player, BlockPos pos, Direction dir) {
        return Box.fromPos(pos, true).expandSide(dir.getAxis(), 1).iterator();
    }
}
