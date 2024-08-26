package com.scand.ie.block.custom;

import ic2.api.energy.EnergyNet;
import ic2.core.platform.registries.IC2Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SolarPanels {
    public static final BlockEntityType<AdvancedULVPanel> ULV =
            IC2Tiles.createTile("advanced_ulv_panel", AdvancedULVPanel::new);

    public static final BlockEntityType<AdvancedLVPanel> LV =
            IC2Tiles.createTile("advanced_lv_panel", AdvancedLVPanel::new);

    public static final BlockEntityType<AdvancedMVPanel> MV =
            IC2Tiles.createTile("advanced_mv_panel", AdvancedMVPanel::new);

    public static final BlockEntityType<AdvancedHVPanel> HV =
            IC2Tiles.createTile("advanced_hv_panel", AdvancedHVPanel::new);

    public static final BlockEntityType<AdvancedEVPanel> EV =
            IC2Tiles.createTile("advanced_ev_panel", AdvancedEVPanel::new);

    public static final BlockEntityType<AdvancedIVPanel> IV =
            IC2Tiles.createTile("advanced_iv_panel", AdvancedIVPanel::new);

    public static final BlockEntityType<AdvancedLuVPanel> LuV =
            IC2Tiles.createTile("advanced_luv_panel", AdvancedLuVPanel::new);

    public static class AdvancedULVPanel extends AdvancedSolarPanelTileEntity{
        public AdvancedULVPanel(BlockPos pos, BlockState state) {
            super(pos, state, 0);
        }

        @Override
        public BlockEntityType<?> createType() {
            return ULV;
        }
    }
    public static class AdvancedLVPanel extends AdvancedSolarPanelTileEntity{
        public AdvancedLVPanel(BlockPos pos, BlockState state) {
            super(pos, state, 1);
        }

        @Override
        public BlockEntityType<?> createType() {
            return LV;
        }
    }
    public static class AdvancedMVPanel extends AdvancedSolarPanelTileEntity{
        public AdvancedMVPanel(BlockPos pos, BlockState state) {
            super(pos, state, 2);
        }

        @Override
        public BlockEntityType<?> createType() {
            return MV;
        }
    }
    public static class AdvancedHVPanel extends AdvancedSolarPanelTileEntity{
        public AdvancedHVPanel(BlockPos pos, BlockState state) {
            super(pos, state, 3);
        }

        @Override
        public BlockEntityType<?> createType() {
            return HV;
        }
    }
    public static class AdvancedEVPanel extends AdvancedSolarPanelTileEntity{
        public AdvancedEVPanel(BlockPos pos, BlockState state) {
            super(pos, state, 4);
        }

        @Override
        public BlockEntityType<?> createType() {
            return EV;
        }
    }
    public static class AdvancedIVPanel extends AdvancedSolarPanelTileEntity{
        public AdvancedIVPanel(BlockPos pos, BlockState state) {
            super(pos, state, 5);
        }

        @Override
        public BlockEntityType<?> createType() {
            return IV;
        }
    }
    public static class AdvancedLuVPanel extends AdvancedSolarPanelTileEntity{
        public AdvancedLuVPanel(BlockPos pos, BlockState state) {
            super(pos, state, 6);
        }

        @Override
        public BlockEntityType<?> createType() {
            return LuV;
        }
    }
}
