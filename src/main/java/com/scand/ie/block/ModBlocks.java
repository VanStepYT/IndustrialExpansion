package com.scand.ie.block;

import com.scand.ie.IEMod;
import com.scand.ie.ModItems.ModItems;
import com.scand.ie.block.custom.IridiumFabricatorTile;
import com.scand.ie.block.custom.SpectraliumFabricatorTile;
import com.scand.ie.block.custom.cable.PhotonCableTile;
import com.scand.ie.block.custom.machines.SpectralCompressor;
import com.scand.ie.block.custom.machines.SpectralFurnace;
import com.scand.ie.block.custom.machines.SpectralMacerator;
import com.scand.ie.block.entity.*;
import ic2.core.block.base.IAutoCreator;
import ic2.core.block.base.IToolProvider;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.base.misc.color.IColorListener;
import ic2.core.block.cables.CableBlock;
import ic2.core.block.cables.CableTileEntity;
import ic2.core.block.cables.Cables;
import ic2.core.block.machines.BaseMachineBlock;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Tiles;
import ic2.core.platform.rendering.IC2Models;
import ic2.core.platform.rendering.features.ITextureProvider;
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

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IEMod.MOD_ID);

    public static final BlockEntityType<AdvancedLVPanelTile> ADVANCED_LV_PANEL_TYPE = IC2Tiles.createTile("advanced_lv_solar_panel", AdvancedLVPanelTile::new);
    public static final BlockEntityType<AdvancedMVPanelTile> ADVANCED_MV_PANEL_TYPE = IC2Tiles.createTile("advanced_mv_solar_panel", AdvancedMVPanelTile::new);
    public static final BlockEntityType<AdvancedHVPanelTile> ADVANCED_HV_PANEL_TYPE = IC2Tiles.createTile("advanced_hv_solar_panel", AdvancedHVPanelTile::new);
    public static final BlockEntityType<AdvancedEVPanelTile> ADVANCED_EV_PANEL_TYPE = IC2Tiles.createTile("advanced_ev_solar_panel", AdvancedEVPanelTile::new);
    public static final BlockEntityType<AdvancedLUVPanelTile> ADVANCED_LUV_PANEL_TYPE = IC2Tiles.createTile("advanced_luv_solar_panel", AdvancedLUVPanelTile::new);
    public static final BlockEntityType<AdvancedUVPanelTile> ADVANCED_UV_PANEL_TYPE = IC2Tiles.createTile("advanced_uv_solar_panel", AdvancedUVPanelTile::new);
    public static final BlockEntityType<IridiumFabricatorTile> IRIDIUM_FABRICATOR_TYPE = IC2Tiles.createTile("iridium_fabricator", IridiumFabricatorTile::new);
    public static final BlockEntityType<PhotonCableTile> PHOTON_CABLE_TYPE = IC2Tiles.createTile("photon_cable", PhotonCableTile::new);
    public static final BlockEntityType<SpectraliumFabricatorTile> SPECTRALIUM_FABRICATOR_TYPE = IC2Tiles.createTile("spectralium_fabricator", SpectraliumFabricatorTile::new);
    public static final BlockEntityType<SpectralMacerator> SPECTRAL_MACERATOR_TYPE = IC2Tiles.createTile("spectral_macerator", SpectralMacerator::new);
    public static final BlockEntityType<SpectralCompressor> SPECTRAL_COMPRESSOR_TYPE = IC2Tiles.createTile("spectral_compressor", SpectralCompressor::new);

    public static final BlockEntityType<SpectralFurnace> SPECTRAL_FURNACE_TYPE = IC2Tiles.createTile("spectral_furnace", SpectralFurnace::new);
    public static final RegistryObject<Block> QUANTUM_QUARRY = registerBlock("quantum_quarry",
            () -> new QuantumQuarryBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> IRIDIUM_FABRICATOR = registerBlock("iridium_fabricator",
            () -> new BaseMachineBlock("iridium_fabricator", IBlockDropProvider.SELF_OR_ADV_MACHINE, ITextureProvider.noState(IEMod.MOD_ID,
                    "iridium_fabricator"),IRIDIUM_FABRICATOR_TYPE),CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> SPECTRALIUM_FABRICATOR = registerBlock("spectralium_fabricator",
            () -> new BaseMachineBlock("spectralium_fabricator", IBlockDropProvider.SELF_OR_ADV_MACHINE, ITextureProvider.noState(IEMod.MOD_ID,
                    "spectralium_fabricator"),SPECTRALIUM_FABRICATOR_TYPE),CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> SPECTRAL_MACERATOR = registerBlock("spectral_macerator",
            () -> new BaseMachineBlock("spectral_macerator", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "spectral_macerator"),SPECTRAL_MACERATOR_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> SPECTRAL_COMPRESSOR = registerBlock("spectral_compressor",
            () -> new BaseMachineBlock("spectral_compressor", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "spectral_compressor"),SPECTRAL_COMPRESSOR_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> SPECTRAL_FURNACE = registerBlock("spectral_furnace",
            () -> new BaseMachineBlock("spectral_furnace", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "spectral_furnace"),SPECTRAL_FURNACE_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> NANO_MACHINE_CASING = registerBlock("nano_machine_casing",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> QUANTUM_MACHINE_CASING = registerBlock("quantum_machine_casing",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> SPECTRAL_MACHINE_CASING = registerBlock("spectral_machine_casing",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()),
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
    public static final RegistryObject<Block> ADVANCED_UV_PANEL = registerBlock("advanced_uv_panel",
            () -> new AdvancedUVSolarPanelBlock("advanced_uv_panel", ADVANCED_UV_PANEL_TYPE),
            CreativeModeTab.TAB_MISC);


    public static BlockEntityType<UVTransformerTileEntity> UVTRANSFORMER_TYPE = IC2Tiles.createTile("uv_transformer", UVTransformerTileEntity::new);
    public static final RegistryObject<Block> UV_TRANSFORMER = registerBlock("uv_transformer",
            () -> new UVTransformerBlock("uv_transformer", UVTRANSFORMER_TYPE),
            CreativeModeTab.TAB_MISC);
    public static final BlockEntityType<UTESUTile> UTESU_TILE_TYPE = IC2Tiles.createTile("utesu", UTESUTile::new);
    public static final BlockEntityType<HUTESUTile> HUTESU_TILE_TYPE = IC2Tiles.createTile("hutesu", HUTESUTile::new);

    public static final RegistryObject<Block> UTESU = registerBlock("utesu",
            () -> new UTESUBlock("utesu", UTESU_TILE_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> HUTESU = registerBlock("hutesu",
            () -> new HUTESUBlock("hutesu", HUTESU_TILE_TYPE),
            CreativeModeTab.TAB_MISC);

    public static final  CableBlock.CableInstance PHOTON_CABLE_INSTANCE = new
            CableBlock.CableInstance(false,false,false,0, new float[]{2.0F},
            "electric/cable", Cables::getPlasmaDrop).addTextures(new String[]{"photon"});
    //public static final Block PHOTON_CABLE = registerBlock(CableBlock.createBlock("photon_cable", PHOTON_CABLE_INSTANCE, PHOTON_CABLE_TYPE));

    public static final RegistryObject<Block> PHOTON_CABLE = registerBlock("photon_cable",
            ()->CableBlock.createBlock("photon_cable",
                    new CableBlock.CableInstance(false,false,false,0, new float[]{2.0F},
                            "electric/cable", Cables::getPlasmaDrop).addTextures("photon"),
                    PHOTON_CABLE_TYPE),
            CreativeModeTab.TAB_MISC);

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

    public static CableBlock createBlock(String name, final CableBlock.CableInstance cable, BlockEntityType<? extends CableTileEntity> creator) {
        return new CableBlock(name, creator) {
            public CableInstance createInstance() {
                return cable;
            }
        };
    }

    public static void register(IEventBus eventBus){

        BLOCKS.register(eventBus);
        //addCables();


    }
}
