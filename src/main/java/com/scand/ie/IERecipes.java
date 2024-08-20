package com.scand.ie;

import com.scand.ie.ModItems.ModItems;
import com.scand.ie.block.ModBlocks;
import ic2.api.recipes.registries.IAdvancedCraftingManager;
import ic2.core.IC2;
import ic2.core.platform.recipes.misc.AdvRecipeRegistry;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Tags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class IERecipes {

    public static void init() {
        AdvRecipeRegistry.INSTANCE.registerListener(IERecipes::initCraftingRecipes);
        initMachineRecipes();
    }

    public static void initCraftingRecipes(IAdvancedCraftingManager manager) {
        manager.addShapedRecipe(rl("extreme_alloy"),
                ModItems.EXTREMELY_ADVANCED_ALLOY.get().getDefaultInstance(),
                "S", "I", "H",
                'S', ModItems.SPECTRALIUM.get(),
                'I', IC2Items.PLATE_IRIDIUM,
                'H', ModItems.HIGHLY_ADVANCED_ALLOY.get());

        manager.addShapedRecipe(rl("raw_high_alloy"),
                ModItems.RAW_HIGHLY_ADVANCED_ALLOY.get().getDefaultInstance(),
                "SSS", "CCC", "AAA",
                'S', IC2Tags.INGOT_SILVER,
                'C', IC2Items.CARBON_PLATE,
                'A', IC2Items.PLATE_ADVANCED_ALLOY);

        manager.addShapedRecipe(rl("singularity_plate"),
                ModItems.SINGULARITY_PLATE.get().getDefaultInstance(),
                "SPS", "PAP", "SPS",
                'S', ModItems.SINGULARIUM.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                'A', ModItems.EXTREMELY_ADVANCED_ALLOY.get());

        manager.addShapedRecipe(rl("singularity_circuit"),
                ModItems.SINGULARITY_CIRCUIT.get().getDefaultInstance(),
                "CPC", "S#S", "CPC",
                'C', ModBlocks.NEUTRON_CABLE.get(),
                'S', ModItems.SINGULARITY.get(),
                '#', ModItems.SPECTRAL_CIRCUIT.get(),
                'P', ModItems.SINGULARITY_PLATE.get());

        manager.addShapedRecipe(rl("utec"),
                ModItems.UTEC.get().getDefaultInstance(),
                "MPM", "I#I", "MPM",
                'M', ModItems.MOON_MATTER.get(),
                'P', ModItems.HIGHLY_ADVANCED_ALLOY.get(),
                '#', IC2Items.PESD,
                'I', IC2Items.PLATE_IRIDIUM);

        manager.addShapedRecipe(rl("spectral_chestplate"),
                ModItems.SPECTRAL_SUIT_CHESTPLATE.get().getDefaultInstance(),
                "C#C", "PQP", "CMC",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                '#', ModItems.SPECTRAL_CRYSTAL.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                'Q', IC2Items.QUANTUM_SUIT_CHESTPLATE,
                'M', ModItems.FLY_MODULE.get());

        manager.addShapedRecipe(rl("spectral_legs"),
                ModItems.SPECTRAL_SUIT_LEGGINGS.get().getDefaultInstance(),
                "C#C", "PQP", "CMC",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                '#', ModItems.SPECTRAL_CRYSTAL.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                'Q', IC2Items.QUANTUM_SUIT_LEGGINGS,
                'M', ModItems.SPECTRAL_CRAFTING_COMPONENT.get());

        manager.addShapedRecipe(rl("spectral_boots"),
                ModItems.SPECTRAL_SUIT_BOOTS.get().getDefaultInstance(),
                "C#C", "PQP", "CMC",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                '#', ModItems.SPECTRAL_CRYSTAL.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                'Q', IC2Items.QUANTUM_SUIT_BOOTS,
                'M', ModItems.SPECTRAL_CRAFTING_COMPONENT.get());

        manager.addShapedRecipe(rl("spectral_helmet"),
                ModItems.SPECTRAL_SUIT_HELMET.get().getDefaultInstance(),
                "C#C", "PQP", "CMC",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                '#', ModItems.SPECTRAL_CRYSTAL.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                'Q', IC2Items.QUANTUM_SUIT_HELMET,
                'M', ModItems.SPECTRAL_CRAFTING_COMPONENT.get());

        manager.addShapedRecipe(rl("spectral_circuit"),
                ModItems.SPECTRAL_CIRCUIT.get().getDefaultInstance(),
                "CPC", "S#S", "CPC",
                'C', ModBlocks.PHOTON_CABLE.get(),
                'S', ModItems.SPECTRAL_CRAFTING_COMPONENT.get(),
                '#', ModItems.QUANTUM_CIRCUIT.get(),
                'P', ModItems.SPECTRAL_PLATE.get());

        manager.addShapedRecipe(rl("quantum_circuit"),
                ModItems.QUANTUM_CIRCUIT.get().getDefaultInstance(),
                "CPC", "S#S", "CPC",
                'C', IC2Items.PLASMA_CABLE,
                'S', IC2Items.TOOL_BOX_IRIDIUM,
                '#', ModItems.NANO_CIRCUIT.get(),
                'P', IC2Items.PLATE_IRIDIUM);

        manager.addShapedRecipe(rl("nano_circuit"),
                ModItems.NANO_CIRCUIT.get().getDefaultInstance(),
                "CPC", "S#S", "CPC",
                'C', IC2Items.GLASSFIBER_CABLE,
                'S', IC2Items.TOOL_BOX_CARBON,
                '#', IC2Items.COMPLEX_CIRCUIT,
                'P', IC2Items.CARBON_PLATE);

        manager.addShapedRecipe(rl("spectral_crafting_component"),
                ModItems.SPECTRAL_CRAFTING_COMPONENT.get().getDefaultInstance(),
                "PTP", "PPP",
                'T', IC2Items.TOOL_BOX_IRIDIUM,
                'P', ModItems.SPECTRAL_PLATE.get());

        manager.addShapedRecipe(rl("spectral_overclocker"),
                ModItems.SPECTRAL_OVERCLOCKER_UPGRADE.get().getDefaultInstance(),
                "PCP", "PUP", "PCP",
                'U', IC2Items.QUANTUM_OVERCLOCKER_UPGRADE,
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                'P', ModItems.SPECTRAL_PLATE.get());

        manager.addShapedRecipe(rl("spectral_plate"),
                ModItems.SPECTRAL_PLATE.get().getDefaultInstance(),
                "SAS", "AIA", "SAS",
                'I', IC2Items.PLATE_IRIDIUM,
                'S', ModItems.SPECTRALIUM.get(),
                'A', ModItems.HIGHLY_ADVANCED_ALLOY.get());

        manager.addShapedRecipe(rl("nano_drill"),
                ModItems.NANO_DRILL.get().getDefaultInstance(),
                "PCP", "CTC", "PBP",
                'P', IC2Items.CARBON_PLATE,
                'T', IC2Items.DRILL_ADVANCED,
                'B', IC2Items.TOOL_BOX_CARBON,
                'C', ModItems.NANO_CIRCUIT.get());

        manager.addShapedRecipe(rl("quantum_drill"),
                ModItems.QUANTUM_DRILL.get().getDefaultInstance(),
                "PCP", "CTC", "PBP",
                'P', IC2Items.PLATE_IRIDIUM,
                'T', ModItems.NANO_DRILL.get(),
                'B', IC2Items.TOOL_BOX_IRIDIUM,
                'C', ModItems.QUANTUM_CIRCUIT.get());

        manager.addShapedRecipe(rl("spectral_drill"),
                ModItems.SPECTRAL_DRILL.get().getDefaultInstance(),
                "PCP", "CTC", "PBP",
                'P', ModItems.SPECTRAL_PLATE.get(),
                'T', ModItems.QUANTUM_DRILL.get(),
                'B', ModItems.SPECTRAL_CRAFTING_COMPONENT.get(),
                'C', ModItems.SPECTRAL_CIRCUIT.get());

        manager.addShapedRecipe(rl("fly_module"),
                ModItems.FLY_MODULE.get().getDefaultInstance(),
                "CMC", "#E#", "PJP",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                'M', IC2Items.BASE_MODULE,
                'J', IC2Items.JETPACK_NUCLEAR_COMPACT,
                'E', Items.ELYTRA,
                'P', ModItems.SPECTRAL_PLATE.get(),
                '#', ModItems.SPECTRAL_CRAFTING_COMPONENT.get());

        manager.addShapedRecipe(rl("spectral_crystal"),
                ModItems.SPECTRAL_CRYSTAL.get().getDefaultInstance(),
                "OCO", "P#P", "OCO",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                '#', IC2Items.UESC,
                'P', ModItems.SPECTRAL_PLATE.get(),
                'O', ModItems.SPECTRALIUM.get());

        manager.addShapedRecipe(rl("singularity_crystal"),
                ModItems.SINGULARITY_CRYSTAL.get().getDefaultInstance(),
                "P#P", "#C#", "P#P",
                'C', ModBlocks.NEUTRON_CABLE.get(),
                '#', ModItems.SPECTRAL_CRYSTAL.get(),
                'P', ModItems.SINGULARITY_PLATE.get());

        manager.addShapedRecipe(rl("compressed_iridium_rotor"),
                ModItems.COMPRESSED_IRIDIUM_ROTOR.get().getDefaultInstance(),
                "IPI", "PRP", "IPI",
                'I', new ItemStack(IC2Items.PLATE_IRIDIUM, 3),
                'P', IC2Items.PLASMA_CORE,
                'R', IC2Items.IRIDIUM_ROTOR);

        manager.addShapedRecipe(rl("uum_rotor"),
                ModItems.UUM_ROTOR.get().getDefaultInstance(),
                "MMM", "MRM", "MMM",
                'M', new ItemStack(IC2Items.UUMATTER, 4),
                'R', IC2Items.CARBON_ROTOR);

        manager.addShapedRecipe(rl("spectral_rotor"),
                ModItems.SPECTRAL_ROTOR.get().getDefaultInstance(),
                "MMM", "MRM", "MMM",
                'M', ModItems.SPECTRALIUM.get(),
                'R', ModItems.COMPRESSED_IRIDIUM_ROTOR.get());

        manager.addShapedRecipe(rl("photon_rotor"),
                ModItems.PHOTON_ROTOR.get().getDefaultInstance(),
                "MMM", "MRM", "MMM",
                'M', ModItems.SPECTRAL_PLATE.get(),
                'R', ModItems.SPECTRAL_ROTOR.get());

        manager.addShapedRecipe(rl("neutron_rotor"),
                ModItems.NEUTRON_ROTOR.get().getDefaultInstance(),
                "MMM", "MRM", "MMM",
                'M', ModItems.DARK_MATTER.get(),
                'R', ModItems.PHOTON_ROTOR.get());

        manager.addShapedRecipe(rl("singularity_rotor"),
                ModItems.SINGULARITY_ROTOR.get().getDefaultInstance(),
                "MMM", "MRM", "MMM",
                'M', ModItems.SINGULARITY_PLATE.get(),
                'R', ModItems.NEUTRON_ROTOR.get());

        manager.addShapedRecipe(rl("ies_transporter"),
                new ItemStack(ModBlocks.IES_TRANSPORTER.get()),
                "SHS", "S#S", "SHS",
                'S', ModItems.SINGULARIUM.get(),
                'H', Items.HOPPER,
                '#', ModBlocks.IUTESU.get());

        manager.addShapedRecipe(rl("ies_splitter"),
                new ItemStack(ModBlocks.IES_SPLITTER.get()),
                "SHS", "S#S", "SHS",
                'S', ModItems.SINGULARIUM.get(),
                'H', Items.COMPARATOR,
                '#', ModBlocks.IUTESU.get());

        manager.addShapedRecipe(rl("ies_output"),
                new ItemStack(ModBlocks.IES_OUTPUT.get()),
                "SHS", "S#S", "SHS",
                'S', ModItems.SINGULARIUM.get(),
                'H', ModBlocks.ADVANCED_ADJUSTABLE_TRANSFORMER.get(),
                '#', ModBlocks.IUTESU.get());

        manager.addShapedRecipe(rl("quantum_quarry"),
                new ItemStack(ModBlocks.QUANTUM_QUARRY.get()),
                "CPC", "M#M", "ADA",
                'C', IC2Items.GLASSFIBER_CABLE,
                'P', IC2Items.PESD,
                'A', IC2Items.PLATE_ADVANCED_ALLOY,
                'M', IC2Blocks.STABILIZED_MACHINE_BLOCK,
                '#', IC2Blocks.MINER,
                'D', ModItems.QUANTUM_DRILL.get());

        manager.addShapedRecipe(rl("iridium_fabricator"),
                new ItemStack(ModBlocks.IRIDIUM_FABRICATOR.get()),
                "OOO", "M#M", "OOO",
                'O', IC2Items.ORE_IRIDIUM,
                '#', IC2Blocks.MASS_FABRICATOR,
                'M', ModBlocks.QUANTUM_MACHINE_CASING.get());

        manager.addShapedRecipe(rl("spectralium_fabricator"),
                new ItemStack(ModBlocks.SPECTRALIUM_FABRICATOR.get()),
                "PCP", "M#M", "PCP",
                'C', ModItems.HIGHLY_ADVANCED_ALLOY.get(),
                'P', ModItems.QUANTUM_CIRCUIT.get(),
                '#', ModBlocks.IRIDIUM_FABRICATOR.get(),
                'M', ModBlocks.QUANTUM_MACHINE_CASING.get());

        manager.addShapedRecipe(rl("black_hole_controller"),
                new ItemStack(ModBlocks.BLACK_HOLE_CONTROLLER.get()),
                "CBC", "PMP", "C#C",
                'P', ModItems.HIGHLY_ADVANCED_ALLOY.get(),
                'B', ModItems.SPECTRAL_CRYSTAL.get(),
                '#', ModBlocks.SPECTRALIUM_FABRICATOR.get(),
                'C', ModBlocks.PHOTON_CABLE.get(),
                'M', ModBlocks.SPECTRAL_MACHINE_CASING.get());

        manager.addShapedRecipe(rl("black_hole_stabilizer"),
                new ItemStack(ModBlocks.BLACK_HOLE_STABILIZER.get()),
                "C#C", "CBC", "CTC",
                'C', ModBlocks.NEUTRON_CABLE.get(),
                'B', ModItems.SINGULARITY_CRYSTAL.get(),
                '#', ModBlocks.BLACK_HOLE_CONTROLLER.get(),
                'T', ModBlocks.IUV_TRANSFORMER.get());

        manager.addShapedRecipe(rl("white_hole_controller"),
                new ItemStack(ModBlocks.WHITE_HOLE_CONTROLLER.get()),
                "C#C", "CSC", "CBC",
                'C', ModItems.SINGULARITY_PLATE.get(),
                'B', ModItems.SINGULARITY_CRYSTAL.get(),
                '#', ModBlocks.BLACK_HOLE_CONTROLLER.get(),
                'S', ModItems.INVERTED_SINGULARITY.get());

        manager.addShapedRecipe(rl("white_hole_stabilizer"),
                new ItemStack(ModBlocks.WHITE_HOLE_STABILIZER.get()),
                "C#C", "CTC", "CBC",
                'C', ModItems.SINGULARITY_PLATE.get(),
                'B', ModItems.SINGULARITY_CRYSTAL.get(),
                '#', ModBlocks.WHITE_HOLE_CONTROLLER.get(),
                'T', ModBlocks.IUTESU.get());

        manager.addShapedRecipe(rl("spectral_macerator"),
                new ItemStack(ModBlocks.SPECTRAL_MACERATOR.get()),
                " C ", "PMP", " # ",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                'M', ModBlocks.SPECTRAL_MACHINE_CASING.get(),
                '#', IC2Blocks.COLOSSAL_MACERATOR);

        manager.addShapedRecipe(rl("spectral_compressor"),
                new ItemStack(ModBlocks.SPECTRAL_COMPRESSOR.get()),
                " C ", "PMP", " # ",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                'M', ModBlocks.SPECTRAL_MACHINE_CASING.get(),
                '#', IC2Blocks.COLOSSAL_COMPRESSOR);

        manager.addShapedRecipe(rl("spectral_furnace"),
                new ItemStack(ModBlocks.SPECTRAL_FURNACE.get()),
                " C ", "PMP", " # ",
                'C', ModItems.SPECTRAL_CIRCUIT.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                'M', ModBlocks.SPECTRAL_MACHINE_CASING.get(),
                '#', IC2Blocks.COLOSSAL_FURNACE);

        manager.addShapedRecipe(rl("singularium_furnace"),
                new ItemStack(ModBlocks.SINGULARIUM_FURNACE.get()),
                "PCP", "P#P", "PCP",
                'C', ModItems.SINGULARITY_CIRCUIT.get(),
                'P', ModItems.SINGULARITY_PLATE.get(),
                '#', ModBlocks.SPECTRAL_FURNACE.get());

        manager.addShapedRecipe(rl("singularium_macerator"),
                new ItemStack(ModBlocks.SINGULARIUM_MACERATOR.get()),
                "PCP", "P#P", "PCP",
                'C', ModItems.SINGULARITY_CIRCUIT.get(),
                'P', ModItems.SINGULARITY_PLATE.get(),
                '#', ModBlocks.SPECTRAL_MACERATOR.get());

        manager.addShapedRecipe(rl("singularium_compressor"),
                new ItemStack(ModBlocks.SINGULARIUM_COMPRESSOR.get()),
                "PCP", "P#P", "PCP",
                'C', ModItems.SINGULARITY_CIRCUIT.get(),
                'P', ModItems.SINGULARITY_PLATE.get(),
                '#', ModBlocks.SPECTRAL_COMPRESSOR.get());

        manager.addShapedRecipe(rl("singularium_extractor"),
                new ItemStack(ModBlocks.SINGULARIUM_EXTRACTOR.get()),
                "PCP", "P#P", "PCP",
                'C', ModItems.SINGULARITY_CIRCUIT.get(),
                'P', ModItems.SINGULARITY_PLATE.get(),
                '#', IC2Blocks.COLOSSAL_EXTRACTOR);

        manager.addShapedRecipe(rl("nano_machine_casing"),
                new ItemStack(ModBlocks.NANO_MACHINE_CASING.get()),
                "CPC", "TMT", "CPC",
                'P', ModItems.HIGHLY_ADVANCED_ALLOY.get(),
                'C', IC2Items.CARBON_PLATE,
                'T', IC2Items.TOOL_BOX_CARBON,
                'M', IC2Blocks.STABILIZED_MACHINE_BLOCK);

        manager.addShapedRecipe(rl("quantum_machine_casing"),
                new ItemStack(ModBlocks.QUANTUM_MACHINE_CASING.get()),
                "CPC", "TMT", "CPC",
                'P', IC2Items.PLATE_IRIDIUM,
                'C', IC2Items.UUMATTER,
                'T', IC2Items.TOOL_BOX_IRIDIUM,
                'M', ModBlocks.NANO_MACHINE_CASING.get());

        manager.addShapedRecipe(rl("spectral_machine_casing"),
                new ItemStack(ModBlocks.SPECTRAL_MACHINE_CASING.get()),
                "CPC", "TMT", "CPC",
                'P', ModItems.SPECTRAL_PLATE.get(),
                'C', ModItems.SPECTRALIUM.get(),
                'T', ModItems.SPECTRAL_CRAFTING_COMPONENT.get(),
                'M', ModBlocks.QUANTUM_MACHINE_CASING.get());

        manager.addShapedRecipe(rl("advanced_lv_solar_panel"),
                new ItemStack(ModBlocks.ADVANCED_LV_PANEL.get()),
                "ABA", "M#M", "ATA",
                'A', IC2Items.PLATE_ADVANCED_ALLOY,
                'B', IC2Items.ENERGY_CRYSTAL,
                'M', IC2Blocks.ADVANCED_MACHINE_BLOCK,
                '#', IC2Blocks.SOLAR_PANEL_LV,
                'T', IC2Blocks.TRANSFORMER_MV);

        manager.addShapedRecipe(rl("advanced_mv_solar_panel"),
                new ItemStack(ModBlocks.ADVANCED_MV_PANEL.get()),
                "ABA", "M#M", "ATA",
                'A', IC2Items.PLATE_ADVANCED_ALLOY,
                'B', IC2Items.LAPATRON_CRYSTAL,
                'M', IC2Blocks.ADVANCED_MACHINE_BLOCK,
                '#', ModBlocks.ADVANCED_LV_PANEL.get(),
                'T', IC2Blocks.SOLAR_PANEL_MV);

        manager.addShapedRecipe(rl("advanced_hv_solar_panel"),
                new ItemStack(ModBlocks.ADVANCED_HV_PANEL.get()),
                "ABA", "M#M", "ATA",
                'A', ModItems.HIGHLY_ADVANCED_ALLOY.get(),
                'B', IC2Items.LAPATRON_CRYSTAL,
                'M', IC2Blocks.STABILIZED_MACHINE_BLOCK,
                '#', ModBlocks.ADVANCED_MV_PANEL.get(),
                'T', IC2Blocks.SOLAR_PANEL_HV);

        manager.addShapedRecipe(rl("advanced_ev_solar_panel"),
                new ItemStack(ModBlocks.ADVANCED_EV_PANEL.get()),
                "ABA", "M#M", "ATA",
                'A', ModItems.HIGHLY_ADVANCED_ALLOY.get(),
                'B', IC2Items.GLOWTRONIC_CRYSTAL,
                'M', ModBlocks.NANO_MACHINE_CASING.get(),
                '#', ModBlocks.ADVANCED_HV_PANEL.get(),
                'T', IC2Blocks.TRANSFORMER_EV);

        manager.addShapedRecipe(rl("advanced_luv_solar_panel"),
                new ItemStack(ModBlocks.ADVANCED_LUV_PANEL.get()),
                "QBQ", "M#M", "ATA",
                'A', ModItems.HIGHLY_ADVANCED_ALLOY.get(),
                'Q', ModItems.MOON_MATTER.get(),
                'B', IC2Items.UESC,
                'M', ModBlocks.QUANTUM_MACHINE_CASING.get(),
                '#', ModBlocks.ADVANCED_EV_PANEL.get(),
                'T', ModBlocks.UV_TRANSFORMER.get());

        manager.addShapedRecipe(rl("advanced_uv_solar_panel"),
                new ItemStack(ModBlocks.ADVANCED_UV_PANEL.get()),
                "QBQ", "M#M", "ATA",
                'Q', ModItems.SPECTRALIUM.get(),
                'A', ModItems.SPECTRAL_PLATE.get(),
                'B', ModItems.SPECTRAL_CRYSTAL.get(),
                'M', ModBlocks.SPECTRAL_MACHINE_CASING.get(),
                '#', ModBlocks.ADVANCED_LUV_PANEL.get(),
                'T', ModBlocks.UTESU.get());

        manager.addShapedRecipe(rl("uv_transformer"),
                new ItemStack(ModBlocks.UV_TRANSFORMER.get()),
                "ABA", "M#U", "ABA",
                'A', IC2Items.PLATE_ADVANCED_ALLOY,
                'B', IC2Items.PESD,
                '#', IC2Blocks.TRANSFORMER_IV,
                'U', ModItems.UTEC.get(),
                'M', ModBlocks.QUANTUM_MACHINE_CASING.get());

        manager.addShapedRecipe(rl("huv_transformer"),
                new ItemStack(ModBlocks.HUV_TRANSFORMER.get()),
                "ABA", "M#U", "ABA",
                'A', IC2Items.PLATE_IRIDIUM,
                'B', ModItems.SPECTRAL_CRYSTAL.get(),
                '#', ModBlocks.UV_TRANSFORMER.get(),
                'U', ModItems.UTEC.get(),
                'M', ModBlocks.SPECTRAL_MACHINE_CASING.get());

        manager.addShapedRecipe(rl("iuv_transformer"),
                new ItemStack(ModBlocks.IUV_TRANSFORMER.get()),
                "ABA", "M#U", "ABA",
                'A', ModItems.SPECTRAL_PLATE.get(),
                'B', ModItems.SINGULARITY_CRYSTAL.get(),
                '#', ModBlocks.HUV_TRANSFORMER.get(),
                'U', ModItems.SPECTRAL_CRYSTAL.get(),
                'M', ModItems.DARK_MATTER.get());

        manager.addShapedRecipe(rl("advanced_adj_transformer"),
                new ItemStack(ModBlocks.ADVANCED_ADJUSTABLE_TRANSFORMER.get()),
                "T", "B", "#",
                'B', ModItems.SPECTRAL_CRYSTAL.get(),
                'T', IC2Blocks.TRANSFORMER_ADJUSTABLE,
                '#', ModBlocks.HUV_TRANSFORMER.get());

        manager.addShapedRecipe(rl("utesu"),
                new ItemStack(ModBlocks.UTESU.get()),
                "QPQ", "###", "QPQ",
                'Q', ModBlocks.QUANTUM_MACHINE_CASING.get(),
                'P', ModItems.HIGHLY_ADVANCED_ALLOY.get(),
                '#', ModItems.UTEC.get());

        manager.addShapedRecipe(rl("hutesu"),
                new ItemStack(ModBlocks.HUTESU.get()),
                "QPQ", "#M#", "QPQ",
                'Q', ModBlocks.SPECTRAL_MACHINE_CASING.get(),
                'M', ModBlocks.UTESU.get(),
                'P', ModItems.SPECTRAL_PLATE.get(),
                '#', ModItems.SPECTRAL_CRYSTAL.get());

        manager.addShapedRecipe(rl("iutesu"),
                new ItemStack(ModBlocks.IUTESU.get()),
                "QPQ", "PMP", "QPQ",
                'Q', ModBlocks.NEUTRON_CABLE.get(),
                'M', ModBlocks.HUTESU.get(),
                'P', ModItems.SINGULARITY_CRYSTAL.get());
    }

    public static void initMachineRecipes() {
        IC2.RECIPES.get(true).extractor.registerListener(r -> {
            r.addSimpleRecipe(rl("moon_matter_from_uum"), new ItemStack(ModItems.MOON_MATTER.get()), IC2Items.UUMATTER.getDefaultInstance());
            r.addSimpleRecipe(rl("inversed_singularity"), new ItemStack(ModItems.INVERTED_SINGULARITY.get()), new ItemStack(ModItems.SINGULARITY.get(), 16));
            r.addSimpleRecipe(rl("singularium"), new ItemStack(ModItems.SINGULARIUM.get()), new ItemStack(ModItems.DARK_MATTER.get(), 4));
        });

        IC2.RECIPES.get(true).macerator.registerListener(r -> r.addSimpleRecipe(rl("singularity_shard"), new ItemStack(ModItems.SINGULARITY_SHARD.get()), new ItemStack(ModItems.DARK_MATTER.get(), 64)));

        IC2.RECIPES.get(true).compressor.registerListener(r -> {
            r.addSimpleRecipe(rl("dark_matter"), new ItemStack(ModItems.DARK_MATTER.get()), new ItemStack(ModItems.DARK_MATTER_SHARD.get(), 9));
            r.addSimpleRecipe(rl("highly_advanced_alloy"), new ItemStack(ModItems.HIGHLY_ADVANCED_ALLOY.get()), new ItemStack(ModItems.RAW_HIGHLY_ADVANCED_ALLOY.get()));
            r.addSimpleRecipe(rl("neutron_cable"), new ItemStack(ModBlocks.NEUTRON_CABLE.get()), new ItemStack(ModItems.DARK_MATTER.get()));
            r.addSimpleRecipe(rl("photon_cable"), new ItemStack(ModBlocks.PHOTON_CABLE.get()), new ItemStack(ModItems.SPECTRAL_PLATE.get()));
            r.addSimpleRecipe(rl("singularity"), new ItemStack(ModBlocks.SINGULARITY.get()), new ItemStack(ModItems.SINGULARITY_SHARD.get(), 9));
        });
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(IEMod.MOD_ID, path + "_1");
    }
}
