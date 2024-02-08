package com.scand.ie.ModItems;

import com.scand.ie.IEMod;
import com.scand.ie.block.ModBlocks;
import ic2.core.block.base.misc.color.IColorListener;
import ic2.core.item.block.CableItem;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.utils.plugins.IRegistryProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static ic2.core.platform.registries.IC2Items.COLORABLE;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IEMod.MOD_ID);

    public static final RegistryObject<Item> MOON_MATTER = ITEMS.register("moon_matter",
            ()-> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> HIGHLY_ADVANCED_ALLOY = ITEMS.register("highly_advanced_alloy",
            ()-> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> RAW_HIGHLY_ADVANCED_ALLOY = ITEMS.register("raw_highly_advanced_alloy",
            ()-> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> UTEC = ITEMS.register("utec",
            ()-> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static Item SPECTRAL_CABLE;
    private static void registerSomeCables(){
        SPECTRAL_CABLE = registerItem(new CableItem("spectral_cable", ModBlocks.SPECTRAL_CABLE.defaultBlockState(), "spectral"));

    }

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
