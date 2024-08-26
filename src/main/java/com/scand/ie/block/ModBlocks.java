package com.scand.ie.block;

import com.scand.ie.IEMod;
import com.scand.ie.IModBlockDropProvider;
import com.scand.ie.ModItems.ModItems;
import com.scand.ie.block.custom.*;
import com.scand.ie.block.custom.solar_reactor.SolarReactorBlock;
import com.scand.ie.block.custom.solar_reactor.SolarReactorTile;
import com.scand.ie.block.custom.tubes.SpeedExtractionTube;
import com.scand.ie.block.custom.advanced_quantum_quarry.AdvancedQuantumQuarryTile;
import com.scand.ie.block.custom.tubes.IEMultiDirectionalTubeBlock;
import com.scand.ie.block.custom.blackhole.*;
import com.scand.ie.block.custom.cable.*;
import com.scand.ie.block.custom.machines.SpectralCompressor;
import com.scand.ie.block.custom.machines.SpectralFurnace;
import com.scand.ie.block.custom.machines.SpectralMacerator;
import com.scand.ie.block.custom.machines.singularium.SingulariumCompressor;
import com.scand.ie.block.custom.machines.singularium.SingulariumExtractor;
import com.scand.ie.block.custom.machines.singularium.SingulariumFurnace;
import com.scand.ie.block.custom.machines.singularium.SingulariumMacerator;
import com.scand.ie.block.custom.whitehole.*;
import com.scand.ie.block.custom.wind.AdvancedWindmillTileEntity;
import com.scand.ie.block.custom.wind.WindFarmBlock;
import com.scand.ie.block.custom.wind.WindFarmTileEntity;
import com.scand.ie.block.entity.*;
import com.scand.ie.hydrogenreactor.HeliumReactorBlock;
import com.scand.ie.hydrogenreactor.HeliumReactorTile;
import com.scand.ie.hydrogenreactor.HydrogenReactorBlock;
import com.scand.ie.hydrogenreactor.HydrogenReactorTile;
import com.scand.ie.ies.*;
import ic2.core.block.base.IC2Block;
import ic2.core.block.base.blocks.BaseTexturedBlock;
import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.cables.CableBlock;
import ic2.core.block.generators.BaseGeneratorBlock;
import ic2.core.block.generators.containers.WindmillContainer;
import ic2.core.block.machines.BaseMachineBlock;
import ic2.core.block.machines.ColossalMachineBlock;
import ic2.core.block.misc.MachineBlock;
import ic2.core.block.misc.textured.TexturedBlockBlock;
import ic2.core.block.multi.BaseMultiBlock;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Tiles;
import ic2.core.platform.rendering.features.ITextureProvider;
import ic2.core.platform.rendering.features.providers.NoStateProvider;
import ic2.core.platform.rendering.features.providers.ToggleProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
    public static final BlockEntityType<InvertedStabilizerTile> WHITE_HOLE_STABILIZER_TYPE = IC2Tiles.createTile("white_hole_stabilizer", InvertedStabilizerTile::new);
    public static final BlockEntityType<InvertedControllerTile> WHITE_HOLE_CONTROLLER_TYPE = IC2Tiles.createTile("white_hole_controller", InvertedControllerTile::new);
    public static BlockEntityType<IESTransporter> IES_TRANSPORTER_TYPE = IC2Tiles.createTile("ies_transporter", IESTransporter::new);
    public static BlockEntityType<IESSplitter> IES_SPLITTER_TYPE = IC2Tiles.createTile("ies_splitter", IESSplitter::new);
    public static BlockEntityType<IESOutput> IES_OUTPUT_TYPE = IC2Tiles.createTile("ies_output", IESOutput::new);



    public static RegistryObject<Block> IES_TRANSPORTER = registerBlock("ies_transporter",
            ()-> new IESBlock("ies_transporter",IES_TRANSPORTER_TYPE), IEMod.IE);;
    public static RegistryObject<Block> IES_SPLITTER = registerBlock("ies_splitter",
            ()-> new IESSBlock("ies_splitter",IES_SPLITTER_TYPE), IEMod.IE);;
    public static RegistryObject<Block> IES_OUTPUT = registerBlock("ies_output",
            ()-> new IESBlock("ies_output",IES_OUTPUT_TYPE), IEMod.IE);;

    public static ItemStack getPhotonDrop(int insulation) {
        return new ItemStack(ModBlocks.PHOTON_CABLE.get());
    }
    public static ItemStack getNeutronDrop(int insulation) {
        return new ItemStack(ModBlocks.NEUTRON_CABLE.get());
    }
    public static ItemStack getIridiumDrop(int insulation) {
        return new ItemStack(ModBlocks.IRIDIUM_CABLE.get());
    }

    public static final BlockEntityType<HydrogenReactorTile> HYDROGEN_REACTOR_TYPE =
            IC2Tiles.createTile("hydrogen_reactor", HydrogenReactorTile::new);

    public static final BlockEntityType<HeliumReactorTile> HELIUM_REACTOR_TYPE =
            IC2Tiles.createTile("helium_reactor", HeliumReactorTile::new);
    public static final BlockEntityType<IridiumFabricatorTile> IRIDIUM_FABRICATOR_TYPE = IC2Tiles.createTile("iridium_fabricator", IridiumFabricatorTile::new);
    public static final BlockEntityType<PhotonCableTile> PHOTON_CABLE_TYPE = IC2Tiles.createTile("photon_cable", PhotonCableTile::new);
    public static final BlockEntityType<NeutronCableTile> NEUTRON_CABLE_TYPE = IC2Tiles.createTile("neutron_cable", NeutronCableTile::new);
    public static final BlockEntityType<SpectraliumFabricatorTile> SPECTRALIUM_FABRICATOR_TYPE = IC2Tiles.createTile("spectralium_fabricator", SpectraliumFabricatorTile::new);
    public static final BlockEntityType<MassMultiplicator> MASS_MULTIPLIER_TYPE = IC2Tiles.createTile("mass_multiplier", MassMultiplicator::new);
    public static final BlockEntityType<SpectralMacerator> SPECTRAL_MACERATOR_TYPE = IC2Tiles.createTile("spectral_macerator", SpectralMacerator::new);
    public static final BlockEntityType<SpectralCompressor> SPECTRAL_COMPRESSOR_TYPE = IC2Tiles.createTile("spectral_compressor", SpectralCompressor::new);

    public static final BlockEntityType<SpectralFurnace> SPECTRAL_FURNACE_TYPE = IC2Tiles.createTile("spectral_furnace", SpectralFurnace::new);


    public static final BlockEntityType<SingulariumCompressor> SINGULARIUM_COMPRESSOR_TYPE = IC2Tiles.createTile("singularium_compressor", SingulariumCompressor::new);
    public static final BlockEntityType<SingulariumMacerator> SINGULARIUM_MACERATOR_TYPE = IC2Tiles.createTile("singularium_macerator", SingulariumMacerator::new);
    public static final BlockEntityType<SingulariumFurnace> SINGULARIUM_FURNACE_TYPE = IC2Tiles.createTile("singularium_furnace", SingulariumFurnace::new);
    public static final BlockEntityType<SingulariumExtractor> SINGULARIUM_EXTRACTOR_TYPE = IC2Tiles.createTile("singularium_extractor", SingulariumExtractor::new);


    public static final BlockEntityType<ControllerTile> BLACK_HOLE_CONTROLLER_TYPE = IC2Tiles.createTile("black_hole_controller", ControllerTile::new);
    public static final BlockEntityType<StabilizerTile> BLACK_HOLE_STABILIZER_TYPE = IC2Tiles.createTile("black_hole_stabilizer", StabilizerTile::new);

    public static final BlockEntityType<WindFarmTileEntity> ADVANCED_WINDMILL_TYPE = IC2Tiles.createTile("advanced_windmill", WindFarmTileEntity::new);

    public static final RegistryObject<Block> QUANTUM_QUARRY = registerBlock("quantum_quarry",
            () -> new QuantumQuarryBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f, 18).requiresCorrectToolForDrops()),
            IEMod.IE);;

    public static final BlockEntityType<AutoFarmTile> AUTO_FARM_TILE = IC2Tiles.createTile("autofarm", AutoFarmTile::new);

    public static final RegistryObject<Block> ULV_PANEL = registerBlock("ulv_panel",
            () -> new BaseGeneratorBlock("ulv_panel", IBlockDropProvider.SELF,
                    ITextureProvider.noState("ie","ulv_panel"), SolarPanels.ULV), IEMod.IE);

    public static final RegistryObject<Block> LV_PANEL = registerBlock("lv_panel",
            () -> new BaseGeneratorBlock("lv_panel", IBlockDropProvider.SELF,
                    ITextureProvider.noState("ie","lv_panel"), SolarPanels.LV), IEMod.IE);

    public static final RegistryObject<Block> MV_PANEL = registerBlock("mv_panel",
            () -> new BaseGeneratorBlock("mv_panel", IBlockDropProvider.SELF,
                    ITextureProvider.noState("ie","mv_panel"), SolarPanels.MV), IEMod.IE);

    public static final RegistryObject<Block> HV_PANEL = registerBlock("hv_panel",
            () -> new BaseGeneratorBlock("hv_panel", IBlockDropProvider.SELF,
                    ITextureProvider.noState("ie","hv_panel"), SolarPanels.HV), IEMod.IE);

    public static final RegistryObject<Block> EV_PANEL = registerBlock("ev_panel",
            () -> new BaseGeneratorBlock("ev_panel", IBlockDropProvider.SELF,
                    ITextureProvider.noState("ie","ev_panel"), SolarPanels.EV), IEMod.IE);

    public static final RegistryObject<Block> IV_PANEL = registerBlock("iv_panel",
            () -> new BaseGeneratorBlock("iv_panel", IBlockDropProvider.SELF,
                    ITextureProvider.noState("ie","iv_panel"), SolarPanels.IV), IEMod.IE);

    public static final RegistryObject<Block> LUV_PANEL = registerBlock("luv_panel",
            () -> new BaseGeneratorBlock("luv_panel", IBlockDropProvider.SELF,
                    ITextureProvider.noState("ie","luv_panel"), SolarPanels.LuV), IEMod.IE);


    public static final RegistryObject<Block> ADVANCED_QUANTUM_QUARRY = registerBlock("advanced_quantum_quarry",
            ()-> new BaseMachineBlock("advanced_quantum_quarry", IBlockDropProvider.SELF_OR_COLOSSAL, new NoStateProvider(
                    IEMod.MOD_ID,"advanced_quantum_quarry"), AdvancedQuantumQuarryTile.TYPE),IEMod.IE);;

    public static final RegistryObject<Block> IRIDIUM_FABRICATOR = registerBlock("iridium_fabricator",
            () -> new BaseMachineBlock("iridium_fabricator", IBlockDropProvider.SELF_OR_ADV_MACHINE, ITextureProvider.noState(IEMod.MOD_ID,
                    "iridium_fabricator"),IRIDIUM_FABRICATOR_TYPE),IEMod.IE);;

    public static final RegistryObject<Block> SPEED_EXTRACTION_TUBE = registerBlock("speed_extraction_tube",
            ()->new IEMultiDirectionalTubeBlock("speed_extraction_tube",
                    SpeedExtractionTube.TYPE, 2, "speed_extraction_open",
                    "speed_extraction_closed", "speed_extraction_priority"), IEMod.IE);;

    public static final RegistryObject<Block> MASS_MULTIPLIER = registerBlock("mass_multiplier",
            () -> new BaseMachineBlock("mass_multiplier", IBlockDropProvider.SELF_OR_COLOSSAL, ITextureProvider.noState(IEMod.MOD_ID,
                    "mass_multiplier"),MASS_MULTIPLIER_TYPE),IEMod.IE);;
    public static final RegistryObject<Block> SPECTRALIUM_FABRICATOR = registerBlock("spectralium_fabricator",
            () -> new BaseMachineBlock("spectralium_fabricator", IBlockDropProvider.SELF_OR_ADV_MACHINE, ITextureProvider.noState(IEMod.MOD_ID,
                    "spectralium_fabricator"),SPECTRALIUM_FABRICATOR_TYPE),IEMod.IE);;

    /*public static final RegistryObject<Block> ELECTRIC_BOILER = registerBlock("electric_boiler",
            () -> new BaseMachineBlock("electric_boiler", IBlockDropProvider.SELF_OR_STABLE_MACHINE, ITextureProvider.noState(IEMod.MOD_ID,
                    "electric_boiler"), ElectricBoilerTile.ELECTRIC_BOILER_TYPE),IEMod.IE);;
    public static final RegistryObject<Block> OVERCLOCKED_ELECTRIC_BOILER =
            registerBlock("overclocked_electric_boiler", () ->
                    new BaseMachineBlock("overclocked_electric_boiler",
                    IBlockDropProvider.SELF_OR_STABLE_MACHINE, ITextureProvider.noState(IEMod.MOD_ID,
                    "overclocked_electric_boiler"),
                    OverclockedElectricBoilerTile.OVERCLOCKED_ELECTRIC_BOILER_TYPE), IEMod.IE);;*/
    public static final RegistryObject<Block> BLACK_HOLE_CONTROLLER = registerBlock("black_hole_controller",
            () -> new ControllerBlock("black_hole_controller", IBlockDropProvider.SELF_OR_ADV_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID,
                            "black_hole_controller"),BLACK_HOLE_CONTROLLER_TYPE),IEMod.IE);
    public static final RegistryObject<Block> BLACK_HOLE_STABILIZER = registerBlock("black_hole_stabilizer",
            () -> new StabilizerBlock("black_hole_stabilizer",BLACK_HOLE_STABILIZER_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> WHITE_HOLE_CONTROLLER = registerBlock("white_hole_controller",
            () -> new InvertedControllerBlock("white_hole_controller", IBlockDropProvider.SELF_OR_ADV_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID,
                            "white_hole_controller"),WHITE_HOLE_CONTROLLER_TYPE),IEMod.IE);
    public static final RegistryObject<Block> WHITE_HOLE_STABILIZER = registerBlock("white_hole_stabilizer",
            () -> new InvertedStabilizerBlock("white_hole_stabilizer",WHITE_HOLE_STABILIZER_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> SPECTRAL_MACERATOR = registerBlock("spectral_macerator",
            () -> new BaseMachineBlock("spectral_macerator", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "spectral_macerator"),SPECTRAL_MACERATOR_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> SPECTRAL_COMPRESSOR = registerBlock("spectral_compressor",
            () -> new BaseMachineBlock("spectral_compressor", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "machine/spectral_compressor"),SPECTRAL_COMPRESSOR_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> SPECTRAL_FURNACE = registerBlock("spectral_furnace",
            () -> new BaseMachineBlock("spectral_furnace", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "spectral_furnace"),SPECTRAL_FURNACE_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> SINGULARIUM_FURNACE = registerBlock("singularium_furnace",
            () -> new BaseMachineBlock("singularium_furnace", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "singularium_furnace"),SINGULARIUM_FURNACE_TYPE),
            IEMod.IE);
    public static final RegistryObject<Block> SINGULARIUM_MACERATOR = registerBlock("singularium_macerator",
            () -> new BaseMachineBlock("singularium_macerator", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "singularium_macerator"),SINGULARIUM_MACERATOR_TYPE),
            IEMod.IE);
    public static final RegistryObject<Block> SINGULARIUM_COMPRESSOR = registerBlock("singularium_compressor",
            () -> new BaseMachineBlock("singularium_compressor", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "singularium_compressor"),SINGULARIUM_COMPRESSOR_TYPE),
            IEMod.IE);
    public static final RegistryObject<Block> SINGULARIUM_EXTRACTOR = registerBlock("singularium_extractor",
            () -> new BaseMachineBlock("singularium_extractor", IBlockDropProvider.SELF_OR_STABLE_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "singularium_extractor"),SINGULARIUM_EXTRACTOR_TYPE),
            IEMod.IE);


    public static final RegistryObject<Block> NANO_MACHINE_CASING = registerBlock("nano_machine_casing",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f, 18).requiresCorrectToolForDrops()),
            IEMod.IE);

    public static final RegistryObject<Block> SINGULARITY = registerAnonimusBlock("singularity",
            () -> new BlackHole(BlockBehaviour.Properties.of(Material.METAL).strength(-1f, 18).requiresCorrectToolForDrops()),
            IEMod.IE);

    public static final RegistryObject<Block> INVERTED_SINGULARITY = registerAnonimusBlock("inverted_singularity",
            () -> new WhiteHole(BlockBehaviour.Properties.of(Material.METAL).strength(-1f, 18).requiresCorrectToolForDrops()),
            IEMod.IE);

    public static final RegistryObject<Block> QUANTUM_MACHINE_CASING = registerBlock("quantum_machine_casing",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f, 18).requiresCorrectToolForDrops()),
            IEMod.IE);
    
    public static final RegistryObject<Block> SPECTRAL_MACHINE_CASING = registerBlock("spectral_machine_casing",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f, 18).requiresCorrectToolForDrops()),
            IEMod.IE);
    

    public static final BlockEntityType<SolarReactorTile> SOLAR_REACTOR_TYPE = IC2Tiles.createTile("solar_reactor",SolarReactorTile::new);

    public static final RegistryObject<Block> SOLAR_REACTOR = registerBlock("solar_reactor",
            () -> new SolarReactorBlock("solar_reactor", SOLAR_REACTOR_TYPE),
            IEMod.IE);
    

    //public static final AdvancedLVSolarPanelBlock ADVANCED_LV_PANEL = new AdvancedLVSolarPanelBlock("advanced_lv_panel", ADVANCED_LV_PANEL_TYPE);

    public static BlockEntityType<UVTransformerTileEntity> UVTRANSFORMER_TYPE = IC2Tiles.createTile("uv_transformer", UVTransformerTileEntity::new);
    public static BlockEntityType<HUVTransformerTileEntity> HUVTRANSFORMER_TYPE = IC2Tiles.createTile("huv_transformer", HUVTransformerTileEntity::new);
    public static BlockEntityType<IUVTransformerTileEntity> IUVTRANSFORMER_TYPE = IC2Tiles.createTile("iuv_transformer", IUVTransformerTileEntity::new);
    public static BlockEntityType<AdvancedAdjustableTransformer> ADVANCED_ADJUSTABLE_TRANSFORMER_TYPE =
            IC2Tiles.createTile("advanced_adjustable_transformer", AdvancedAdjustableTransformer::new);
    public static final RegistryObject<Block> UV_TRANSFORMER = registerBlock("uv_transformer",
            () -> new UVTransformerBlock("uv_transformer", UVTRANSFORMER_TYPE),
            IEMod.IE);
    public static final RegistryObject<Block> HUV_TRANSFORMER = registerBlock("huv_transformer",
            () -> new HUVTransformerBlock("huv_transformer", HUVTRANSFORMER_TYPE),
            IEMod.IE);
    public static final RegistryObject<Block> IUV_TRANSFORMER = registerBlock("iuv_transformer",
            () -> new IUVTransformerBlock("iuv_transformer", IUVTRANSFORMER_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> ADVANCED_ADJUSTABLE_TRANSFORMER =
            registerBlock("advanced_adjustable_transformer",
            () -> new AdvancedAdjustableTransformerBlock(
                    "advanced_adjustable_transformer",
                    ADVANCED_ADJUSTABLE_TRANSFORMER_TYPE),
                    IEMod.IE);
    public static final BlockEntityType<UTESUTile> UTESU_TILE_TYPE = IC2Tiles.createTile("utesu", UTESUTile::new);
    public static final BlockEntityType<HUTESUTile> HUTESU_TILE_TYPE = IC2Tiles.createTile("hutesu", HUTESUTile::new);
    public static final BlockEntityType<IUTESUTile> IUTESU_TILE_TYPE = IC2Tiles.createTile("iutesu", IUTESUTile::new);

    public static final RegistryObject<Block> UTESU = registerBlock("utesu",
            () -> new UTESUBlock("utesu", UTESU_TILE_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> HUTESU = registerBlock("hutesu",
            () -> new HUTESUBlock("hutesu", HUTESU_TILE_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> IUTESU = registerBlock("iutesu",
            () -> new IUTESUBlock("iutesu", IUTESU_TILE_TYPE),
            IEMod.IE);

    public static final  CableBlock.CableInstance PHOTON_CABLE_INSTANCE = new
            PhotonCableClass.CableInstance(false,false,false,0, new float[]{3.0F},
            "electric/cable", ModBlocks::getPhotonDrop).addTextures(new String[]{"photon"});
    //public static final Block PHOTON_CABLE = registerBlock(CableBlock.createBlock("photon_cable", PHOTON_CABLE_INSTANCE, PHOTON_CABLE_TYPE));


    public static final RegistryObject<Block> PHOTON_CABLE = registerBlock("photon_cable",
            ()-> PhotonCableClass.createBlock("photon_cable",PHOTON_CABLE_INSTANCE,PHOTON_CABLE_TYPE),
            IEMod.IE);

    public static final  CableBlock.CableInstance NEUTRON_CABLE_INSTANCE = new
            NeutronCableClass.CableInstance(false,false,false,0, new float[]{3.0F},
            "electric/cable", ModBlocks::getNeutronDrop).addTextures(new String[]{"neutron"});

    public static final RegistryObject<Block> NEUTRON_CABLE = registerBlock("neutron_cable",
            ()-> NeutronCableClass.createBlock("neutron_cable",NEUTRON_CABLE_INSTANCE,NEUTRON_CABLE_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> HYDROGEN_COOLER = registerBlock("hydrogen_cooler",
            () -> new IEMachineBlock("hydrogen_cooler","misc/hydrogen_cooler"), IEMod.IE);
    public static final RegistryObject<Block> HYDROGEN_HEATER = registerBlock("hydrogen_heater",
            () -> new IEMachineBlock("hydrogen_heater","misc/hydrogen_heater"), IEMod.IE);

    public static final RegistryObject<Block> HELIUM_COOLER = registerBlock("helium_cooler",
            () -> new IEMachineBlock("helium_cooler","misc/helium_cooler"), IEMod.IE);
    public static final RegistryObject<Block> HELIUM_HEATER = registerBlock("helium_heater",
            () -> new IEMachineBlock("helium_heater","misc/helium_heater"), IEMod.IE);

    public static final RegistryObject<Block> SILVER_THERMOELEMENT = registerBlock("silver_thermoelement",
            () -> new IEMachineBlock("silver_thermoelement","misc/silver_thermoelement"), IEMod.IE);
    public static final RegistryObject<Block> ALUMINIUM_THERMOELEMENT = registerBlock("aluminium_thermoelement",
            () -> new IEMachineBlock("aluminium_thermoelement","misc/aluminium_thermoelement"), IEMod.IE);
    public static final RegistryObject<Block> HELIUM_THERMOELEMENT = registerBlock("helium_thermoelement",
            () -> new IEMachineBlock("helium_thermoelement","misc/helium_thermoelement"), IEMod.IE);

    public static final BlockEntityType<IridiumCableTile> IRIDIUM_CABLE_TYPE = IC2Tiles.createTile("iridium_cable", IridiumCableTile::new);
    public static final  CableBlock.CableInstance IRIDIUM_CABLE_INSTANCE = new
            IridiumCableClass.CableInstance(false,false,false,0, new float[]{2.0F},
            "electric/cable", ModBlocks::getIridiumDrop).addTextures(new String[]{"iridium"});
    public static final RegistryObject<Block> IRIDIUM_CABLE = registerBlock("iridium_cable",
            ()-> IridiumCableClass.createBlock("iridium_cable",IRIDIUM_CABLE_INSTANCE,IRIDIUM_CABLE_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> AUTOFARM = registerBlock("autofarm",
            () -> new BaseMachineBlock("autofarm", IBlockDropProvider.SELF_OR_ADV_MACHINE,
                    ITextureProvider.toggle(IEMod.MOD_ID, "autofarm"),AUTO_FARM_TILE),
            IEMod.IE);


    public static final RegistryObject<Block> HYDROGEN_REACTOR = registerBlock("hydrogen_reactor",
            ()-> new HydrogenReactorBlock("hydrogen_reactor", HYDROGEN_REACTOR_TYPE),
            IEMod.IE);
    public static final RegistryObject<Block> HELIUM_REACTOR = registerBlock("helium_reactor",
            ()-> new HeliumReactorBlock("helium_reactor", HELIUM_REACTOR_TYPE),
            IEMod.IE);

    public static final RegistryObject<Block> ADVANCED_WINDMILL = registerBlock("advanced_windmill",
            ()-> new WindFarmBlock("advanced_windmill", IModBlockDropProvider.SELF_OR_NANO, ITextureProvider.noState("ie", "advanced_windmill"), ADVANCED_WINDMILL_TYPE),
            IEMod.IE);

    private static <T extends Block>RegistryObject<T>
    registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<T>
    registerAnonimusBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
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
