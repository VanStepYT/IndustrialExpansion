package com.scand.ie.ModItems;

import com.scand.ie.IEMod;
import com.scand.ie.ModItems.custom.*;
import com.scand.ie.armor.SpectralArmorItem;
import com.scand.ie.block.ModBlocks;
import com.scand.ie.block.custom.solar_reactor.SolarReactorComponent;
import com.scand.ie.block.custom.solar_reactor.SolarReactorCooler;
import com.scand.ie.block.custom.solar_reactor.SolarReactorPreventer;
import com.scand.ie.tools.*;
import ic2.core.block.base.misc.color.IColorListener;
import ic2.core.item.base.IC2SimpleItem;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.item.block.CableItem;
import ic2.core.item.misc.BatteryItem;
import ic2.core.item.misc.WindmillBladeItem;
import ic2.core.item.reactor.ReactorHeatStorageItem;
import ic2.core.item.reactor.ReactorHeatVentItem;
import ic2.core.item.reactor.ReactorUraniumRod;
import ic2.core.item.reactor.urantypes.StandardUranium;
import ic2.core.item.wearable.armor.electric.QuantumSuit;
import ic2.core.item.wearable.base.IC2ModularElectricArmor;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Items;
import ic2.core.utils.plugins.IRegistryProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.w3c.dom.ls.LSParserFilter;

import static ic2.core.platform.registries.IC2Items.COLORABLE;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IEMod.MOD_ID);

    public static final RegistryObject<Item> MOON_MATTER = ITEMS.register("moon_matter",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> HIGHLY_ADVANCED_ALLOY = ITEMS.register("highly_advanced_alloy",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> EXTREMELY_ADVANCED_ALLOY = ITEMS.register("extremely_advanced_alloy",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> RAW_HIGHLY_ADVANCED_ALLOY = ITEMS.register("raw_highly_advanced_alloy",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> DARK_MATTER_SHARD = ITEMS.register("dark_matter_shard",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> SINGULARIUM = ITEMS.register("singularium",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> SINGULARITY_PLATE = ITEMS.register("singularity_plate",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> SINGULARITY_CIRCUIT = ITEMS.register("singularity_circuit",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> DARK_MATTER = ITEMS.register("dark_matter",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> UTEC = ITEMS.register("utec",
            ()->new BatteryItem("utec",75000000, 75000, 5, false,
                    "utec", false ));



    public static final RegistryObject<Item> SPECTRAL_SUIT_CHESTPLATE = ITEMS.register("spectral_suit_chestplate",
            ()->new SpectralArmorItem("spectral_suit_chestplate", EquipmentSlot.CHEST));
    public static final RegistryObject<Item> SPECTRAL_SUIT_LEGGINGS = ITEMS.register("spectral_suit_leggings",
            ()->new SpectralArmorItem("spectral_suit_leggings", EquipmentSlot.LEGS));
    public static final RegistryObject<Item> SPECTRAL_SUIT_BOOTS = ITEMS.register("spectral_suit_boots",
            ()->new SpectralArmorItem("spectral_suit_boots", EquipmentSlot.FEET));
    public static final RegistryObject<Item> SPECTRAL_SUIT_HELMET = ITEMS.register("spectral_suit_helmet",
            ()->new SpectralArmorItem("spectral_suit_helmet", EquipmentSlot.HEAD));

    public static final RegistryObject<Item> SPECTRAL_CIRCUIT = ITEMS.register("spectral_circuit",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> QUANTUM_CIRCUIT = ITEMS.register("quantum_circuit",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> NANO_CIRCUIT = ITEMS.register("nano_circuit",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> SPECTRAL_CRAFTING_COMPONENT = ITEMS.register("spectral_crafting_component",
            ()->new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> SPECTRAL_OVERCLOCKER_UPGRADE = ITEMS.register("spectral_overclocker_upgrade",
            SpectralOverclockerUpgrade::new);
    public static final RegistryObject<Item> SPECTRAL_PLATE = ITEMS.register("spectral_plate",
            ()->new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> SPECTRALIUM = ITEMS.register("spectralium",
            ()->new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> SINGULARITY_SHARD = ITEMS.register("singularity_shard",
            ()->new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> SINGULARITY = ITEMS.register("singularity",
            ()->new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> INVERTED_SINGULARITY = ITEMS.register("inverted_singularity",
            ()->new Item(new Item.Properties().tab(IEMod.IE)));

    public static final RegistryObject<Item> NANO_DRILL = ITEMS.register("nano_drill",
            NanoDrill::new);
    public static final RegistryObject<Item> QUANTUM_DRILL = ITEMS.register("quantum_drill",
            QuantumDrill::new);

    public static final RegistryObject<Item> SPECTRAL_DRILL = ITEMS.register("spectral_drill",
            SpectralDrill::new);

    public static final RegistryObject<Item> NANO_WRENCH = ITEMS.register("nano_wrench",
            NanoWrench::new);
    public static final RegistryObject<Item> QUANTUM_WRENCH = ITEMS.register("quantum_wrench",
            QuantumWrench::new);




    public static final RegistryObject<Item> DENSE_CARBON_PLATE = ITEMS.register("dense_carbon_plate",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> DENSE_IRIDIUM_PLATE = ITEMS.register("dense_iridium_plate",
            ()-> new Item(new Item.Properties().tab(IEMod.IE)));



    public static final RegistryObject<Item> FLY_MODULE = ITEMS.register("fly_module",
            ()->new FlyModule("armor/modules","fly"));

    public static final RegistryObject<Item> SPECTRAL_CRYSTAL = ITEMS.register("spectral_crystal",
            ()->new BatteryItem("spectral_crystal",100000000, 95000, 6, true,
                    "spectral", true, 100000));

    public static final RegistryObject<Item> SINGULARITY_CRYSTAL = ITEMS.register("singularity_crystal",
            ()->new BatteryItem("singularity_crystal",1000000000, 950000, 7, true,
                    "singularity", true, 10000000));


    public static final RegistryObject<Item> COOLANT_CELL_180K= ITEMS.register("heat_storage_eighteen",
                    ()-> new ReactorHeatStorageItem("heat_storage_eighteen", 180000,
                            "reactor/cells", "180k_coolant", 59));

    public static final RegistryObject<Item> IRIDIUM_HEAT_VENT = ITEMS.register("heat_vent_iridium",
            ()->new ReactorUnbreakableHeatVent("heat_vent_iridium", new ReactorHeatVentItem.VentProperty(
                    ReactorHeatVentItem.VentType.HEAT, 128, 4, 10000,
                    "reactor/vents", "iridium_heat", 62)));
    public static final RegistryObject<Item> IRIDIUM_ELECTRIC_VENT = ITEMS.register("electric_vent_iridium",
            ()->new ReactorUnbreakableHeatVent("electric_vent_iridium", new ReactorHeatVentItem.VentProperty(
                    ReactorHeatVentItem.VentType.ELECTRIC, 256, 8, 10000,
                    "reactor/vents", "iridium_electric", 63)));
    public static final RegistryObject<Item> IRIDIUM_STEAM_VENT = ITEMS.register("steam_vent_iridium",
            ()->new ReactorUnbreakableHeatVent("steam_vent_iridium", new ReactorHeatVentItem.VentProperty(
                    ReactorHeatVentItem.VentType.STEAM, 128, 4, 10000,
                    "reactor/vents", "iridium_steam", 64)));

    public static final RegistryObject<Item> COOLANT_CELL_540K= ITEMS.register("heat_storage_fifty",
            ()-> new ReactorHeatStorageItem("c", 540000,
                            "reactor/cells", "540k_coolant", 60));

    public static final RegistryObject<Item> COOLANT_CELL_IRIDIUM= ITEMS.register("heat_storage_iridium",
            ()-> new ReactorUnbreakableHeatStorage("heat_storage_iridium", 110,
                            "reactor/cells", "iridium_coolant", 61));


    public static final RegistryObject<Item> SOLAR_REACTOR_READER = ITEMS.register(
            "solar_reactor_reader", ()->new SolarReactorReader("solar_reactor_reader", "reader"));


    public static final RegistryObject<Item> SOLAR_COMPONENT_BASIC_DAY = ITEMS.register(
            "basic_day_component", ()->new SolarReactorComponent("basic_day_component", 400,400,1000,true,"basic"));
    public static final RegistryObject<Item> SOLAR_COMPONENT_ADVANCED_DAY = ITEMS.register(
            "advanced_day_component", ()->new SolarReactorComponent("advanced_day_component", 500,300,2000,true,"advanced"));
    public static final RegistryObject<Item> SOLAR_COMPONENT_OVERCLOCKED_DAY = ITEMS.register(
            "overclocked_day_component", ()->new SolarReactorComponent("overclocked_day_component", 800,750,500,true,"overclocked"));

    public static final RegistryObject<Item> SOLAR_COMPONENT_BASIC_NIGHT = ITEMS.register(
            "basic_night_component", ()->new SolarReactorComponent("basic_night_component", 200,200,1000,false,"basic"));
    public static final RegistryObject<Item> SOLAR_COMPONENT_ADVANCED_NIGHT = ITEMS.register(
            "advanced_night_component", ()->new SolarReactorComponent("advanced_night_component", 250,150,2000,false,"advanced"));
    public static final RegistryObject<Item> SOLAR_COMPONENT_OVERCLOCKED_NIGHT = ITEMS.register(
            "overclocked_night_component", ()->new SolarReactorComponent("overclocked_night_component", 500,400,500,false,"overclocked"));

    public static final RegistryObject<Item> SOLAR_COOLER_BASIC = ITEMS.register(
            "basic_solar_cooler", ()->new SolarReactorCooler("basic_solar_cooler", 350,2000,"basic"));
    public static final RegistryObject<Item> SOLAR_COOLER_ADVANCED = ITEMS.register(
            "advanced_solar_cooler", ()->new SolarReactorCooler("advanced_solar_cooler", 400,4000,"advanced"));
    public static final RegistryObject<Item> SOLAR_COOLER_OVERCLOCKED = ITEMS.register(
            "overclocked_solar_cooler", ()->new SolarReactorCooler("overclocked_solar_cooler", 600,3000,"overclocked"));


    public static final RegistryObject<Item> SOLAR_PREVENTER = ITEMS.register(
            "solar_preventer", ()->new SolarReactorPreventer("solar_preventer", 3,"basic"));


    public static final RegistryObject<Item> SILICON = ITEMS.register(
            "silicon", ()->new IC2SimpleItem("silicon", "reactor/solar","silicon"));

    public static final RegistryObject<Item> HYDROGEN_CELL = ITEMS.register("hydrogen_cell",
            ()->new Item(new Item.Properties().tab(IEMod.IE)));
    public static final RegistryObject<Item> HELIUM_CELL = ITEMS.register("helium_cell",
            ()->new Item(new Item.Properties().tab(IEMod.IE)));

    //String itemName, String type, int radius,
    // float effectiveness, ResourceLocation texture, PropertiesBuilder properties
    public static final RegistryObject<Item> COMPRESSED_IRIDIUM_ROTOR = ITEMS.register(
            "compressed_iridium_rotor", () -> new WindmillBladeItem("compressed_iridium_rotor", "compressed_iridium",
                    10,2f,new ResourceLocation(IEMod.MOD_ID, "models/windmill/compressed_iridium_rotor"),
                    new PropertiesBuilder().maxDamage(0)));

    public static final RegistryObject<Item> UUM_ROTOR = ITEMS.register(
            "uum_rotor", () -> new WindmillBladeItem("uum_rotor", "uum",
                    10,0.25f,new ResourceLocation(IEMod.MOD_ID, "models/windmill/uum_rotor"),
                    new PropertiesBuilder().maxDamage(0)));

    public static final RegistryObject<Item> SPECTRAL_ROTOR = ITEMS.register(
            "spectral_rotor", () -> new WindmillBladeItem("spectral_rotor", "spectral",
                    10,4f,new ResourceLocation(IEMod.MOD_ID, "models/windmill/spectral_rotor"),
                    new PropertiesBuilder().maxDamage(0)));

    public static final RegistryObject<Item> PHOTON_ROTOR = ITEMS.register(
            "photon_rotor", () -> new WindmillBladeItem("photon_rotor", "photon",
                    10,8f,new ResourceLocation(IEMod.MOD_ID, "models/windmill/photon_rotor"),
                    new PropertiesBuilder().maxDamage(0)));

    public static final RegistryObject<Item> NEUTRON_ROTOR = ITEMS.register(
            "neutron_rotor", () -> new WindmillBladeItem("neutron_rotor", "neutron",
                    10,16f,new ResourceLocation(IEMod.MOD_ID, "models/windmill/neutron_rotor"),
                    new PropertiesBuilder().maxDamage(0)));

    public static final RegistryObject<Item> SINGULARITY_ROTOR = ITEMS.register(
            "singularity_rotor", () -> new WindmillBladeItem("singularity_rotor", "singularity",
                    10,32f,new ResourceLocation(IEMod.MOD_ID, "models/windmill/singularity_rotor"),
                    new PropertiesBuilder().maxDamage(0)));




    public static final RegistryObject<Item> URANIUM_ROD_IRIDIUM_SINGLE =
            ITEMS.register("uranium_rod_iridium_single",
                    ()-> new ReactorUraniumRod("uranium_rod_iridium_single",
                            "reactor/cells/uranium/single", "iridium",
                            IridiumUranium.INSTANCE, 1, 65));

    public static final RegistryObject<Item> URANIUM_ROD_IRIDIUM_DUAL =
            ITEMS.register("uranium_rod_iridium_dual",
                    ()-> new ReactorUraniumRod("uranium_rod_iridium_dual",
                            "reactor/cells/uranium/dual", "iridium",
                            IridiumUranium.INSTANCE, 2, 66));
    public static final RegistryObject<Item> URANIUM_ROD_IRIDIUM_QUAD =
            ITEMS.register("uranium_rod_iridium_quad",
                    ()-> new ReactorUraniumRod("uranium_rod_iridium_quad",
                            "reactor/cells/uranium/quad", "iridium",
                            IridiumUranium.INSTANCE, 4, 67));
    public static final RegistryObject<Item> URANIUM_ROD_IRIDIUM_OCTO =
            ITEMS.register("uranium_rod_iridium_octo",
                    ()-> new ReactorUraniumRod("uranium_rod_iridium_octo",
                            "reactor/cells/uranium/octo", "iridium",
                            IridiumUranium.INSTANCE, 8, 68));

    public static final RegistryObject<Item> URANIUM_ROD_IRIDIUM_HEXODECIMAL =
            ITEMS.register("uranium_rod_iridium_hexodecimal",
                    ()-> new ReactorUraniumRod("uranium_rod_iridium_hexodecimal",
                            "reactor/cells/uranium/hexodecimal", "iridium",
                            IridiumUranium.INSTANCE, 16, 69));

    public static <T extends Item> T registerItem(T item, ResourceLocation id) {
        ForgeRegistries.ITEMS.register(id, item);
        if (item instanceof IColorListener && ((IColorListener)item).needsColoring()) {
            COLORABLE.add(item);
        }

        return item;
    }

    public static <T extends Item & IRegistryProvider> T registerItem(T item) {
        return registerItem(item, ((IRegistryProvider)item).getRegistryName());
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
        //registerSomeCables();
    }

}
