package com.scand.ie.block;

import com.scand.ie.IEMod;
import com.scand.ie.ModItems.ModItems;
import com.scand.ie.block.entity.*;
import ic2.core.block.base.IAutoCreator;
import ic2.core.block.base.IToolProvider;
import ic2.core.block.base.misc.color.IColorListener;
import ic2.core.block.cables.CableTileEntity;
import ic2.core.block.cables.Cables;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Tiles;
import ic2.core.utils.plugins.IRegistryProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static ic2.core.platform.registries.IC2Items.COLORABLE;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IEMod.MOD_ID);

    public static final BlockEntityType<AdvancedLVPanelTile> ADVANCED_LV_PANEL_TYPE = IC2Tiles.createTile("advanced_lv_solar_panel", AdvancedLVPanelTile::new);
    public static final BlockEntityType<AdvancedMVPanelTile> ADVANCED_MV_PANEL_TYPE = IC2Tiles.createTile("advanced_mv_solar_panel", AdvancedMVPanelTile::new);
    public static final BlockEntityType<AdvancedHVPanelTile> ADVANCED_HV_PANEL_TYPE = IC2Tiles.createTile("advanced_hv_solar_panel", AdvancedHVPanelTile::new);
    public static final BlockEntityType<AdvancedEVPanelTile> ADVANCED_EV_PANEL_TYPE = IC2Tiles.createTile("advanced_ev_solar_panel", AdvancedEVPanelTile::new);
    public static final BlockEntityType<AdvancedLUVPanelTile> ADVANCED_LUV_PANEL_TYPE = IC2Tiles.createTile("advanced_luv_solar_panel", AdvancedLUVPanelTile::new);
    public static final BlockEntityType<SpectralCable> SPECTRAL_CABLE_TYPE = IC2Tiles.createTile("spectral_cable", SpectralCable::new);
    public static final RegistryObject<Block> QUANTUM_QUARRY = registerBlock("quantum_quarry",
            () -> new QuantumQuarryBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> NANO_MACHINE_CASING = registerBlock("nano_machine_casing",
            () -> new QuantumQuarryBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> QUANTUM_MACHINE_CASING = registerBlock("quantum_machine_casing",
            () -> new QuantumQuarryBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()),
            CreativeModeTab.TAB_MISC);

    //public static final AdvancedLVSolarPanelBlock ADVANCED_LV_PANEL = new AdvancedLVSolarPanelBlock("advanced_lv_panel", ADVANCED_LV_PANEL_TYPE);

    public static final RegistryObject<Block> ADVANCED_LV_PANEL = registerBlock("advanced_lv_panel",
            () -> new AdvancedLVSolarPanelBlock("advanced_lv_panel", ADVANCED_LV_PANEL_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> ADVANCED_MV_PANEL = registerBlock("advanced_mv_panel",
            () -> new AdvancedMVSolarPanelBlock("advanced_mv_panel", ADVANCED_MV_PANEL_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> ADVANCED_HV_PANEL = registerBlock("advanced_hv_panel",
            () -> new AdvancedHVSolarPanelBlock("advanced_hv_panel", ADVANCED_HV_PANEL_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> ADVANCED_EV_PANEL = registerBlock("advanced_ev_panel",
            () -> new AdvancedEVSolarPanelBlock("advanced_ev_panel", ADVANCED_EV_PANEL_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> ADVANCED_LUV_PANEL = registerBlock("advanced_luv_panel",
            () -> new AdvancedLUVSolarPanelBlock("advanced_luv_panel", ADVANCED_LUV_PANEL_TYPE),
            CreativeModeTab.TAB_MISC);
    public static BlockEntityType<UVTransformerTileEntity> UVTRANSFORMER_TYPE = IC2Tiles.createTile("uv_transformer", UVTransformerTileEntity::new);
    public static final RegistryObject<Block> UV_TRANSFORMER = registerBlock("uv_transformer",
            () -> new UVTransformerBlock("uv_transformer", UVTRANSFORMER_TYPE),
            CreativeModeTab.TAB_MISC);
    public static final BlockEntityType<UTESUTile> UTESU_TILE_TYPE = IC2Tiles.createTile("utesu", (BlockEntityType.BlockEntitySupplier<UTESUTile>) UTESUTile::new);

    public static final RegistryObject<Block> UTESU = registerBlock("utesu",
            () -> new UTESUBlock("utesu", UTESU_TILE_TYPE),
            CreativeModeTab.TAB_MISC);


    public static Block SPECTRAL_CABLE;

    static void addCables() {
        SPECTRAL_CABLE = registerBlock(SpectralCableBlock.createSBlock("spectral_cable", (new SpectralCableBlock.CableInstance(false, false, false, 0, new float[]{6.0F}, "spectral/", Cables::getPlasmaDrop)).addTextures(new String[]{"plasma"}).setHasUninsulatedColor(), ModBlocks.SPECTRAL_CABLE_TYPE));

    }

    public static <T extends Block & IRegistryProvider> T registerBlock(T block) {
        ForgeRegistries.BLOCKS.register((block).getRegistryName(), block);
        if (block instanceof IAutoCreator creator) {
            Item item = creator.createItem();
            if (item != null) {
                if (item instanceof IRegistryProvider) {
                    IRegistryProvider registry = (IRegistryProvider)item;
                    ModItems.registerItem(item, registry.getRegistryName());
                } else {
                    ModItems.registerItem(item, ((IRegistryProvider)block).getRegistryName());
                }
            }
        }

        if (block instanceof IToolProvider provider) {
            provider.registerTools();
        }

        if (block instanceof IColorListener listener) {
            if (listener.needsColoring()) {
                IC2Blocks.COLORABLE.add(block);
            }
        }

        return block;
    }

    private static <T extends Block>RegistryObject<T>
    registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    }

    private static <T extends Block>
    RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        //addCables();
    }
}
