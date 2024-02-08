package com.scand.ie.armor;

import ic2.api.events.ArmorSlotEvent;
import ic2.api.items.armor.IArmorModule;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.item.wearable.armor.electric.QuantumSuit;
import ic2.core.item.wearable.base.IC2ModularElectricArmor;
import ic2.core.item.wearable.material.IC2ArmorMaterial;
import ic2.core.platform.registries.IC2Items;
import ic2.core.utils.collection.CollectionUtils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SpectralArmorItem extends IC2ModularElectricArmor {
    public SpectralArmorItem(String itemName, EquipmentSlot slot) {
        super(itemName, slot, (new PropertiesBuilder()).rarity(Rarity.RARE));
        this.addSlotType(IArmorModule.ModuleType.BATTERY, 2);
        this.addSlotType(IArmorModule.ModuleType.CHARGER, 2);
        switch (slot) {
            case CHEST:
                this.addSlotType(IArmorModule.ModuleType.GENERIC, 2);
                this.addSlotType(IArmorModule.ModuleType.STORAGE, 3);
                this.addSlotType(IArmorModule.ModuleType.BACK_SLOT, 2);
                break;
            case FEET:
                this.addSlotType(IArmorModule.ModuleType.GENERIC, 2);
                this.addSlotType(IArmorModule.ModuleType.MOVEMENT, 3);
                break;
            case HEAD:
                this.addSlotType(IArmorModule.ModuleType.GENERIC, 4);
                break;
            case LEGS:
                this.addSlotType(IArmorModule.ModuleType.GENERIC, 2);
                this.addSlotType(IArmorModule.ModuleType.STORAGE, 3);
                this.addSlotType(IArmorModule.ModuleType.MOVEMENT, 2);
        }

        MinecraftForge.EVENT_BUS.post(new ArmorSlotEvent(this, "Spectral Suit", slot, this.types));
    }

    public ItemStack createDefaultArmor() {
        ItemStack stack = new ItemStack(this);
        Map<IArmorModule.ModuleType, List<ItemStack>> types = CollectionUtils.createLinkedMap();
        types.put(IArmorModule.ModuleType.BATTERY, ObjectArrayList.wrap(new ItemStack[]{new ItemStack(IC2Items.GLOWTRONIC_CRYSTAL)}));
        switch (this.slot) {
            case CHEST:
                types.put(IArmorModule.ModuleType.GENERIC, ObjectArrayList.wrap(new ItemStack[]{new ItemStack(IC2Items.EXTINGUISHING_MODULE),new ItemStack(IC2Items.PROTECTION_MODULE)}));
                break;
            case FEET:
                types.put(IArmorModule.ModuleType.MOVEMENT, ObjectArrayList.wrap(new ItemStack[]{new ItemStack(IC2Items.JUMP_BOOST_MODULE)}));
                break;
            case HEAD:
                types.put(IArmorModule.ModuleType.GENERIC, ObjectArrayList.wrap(new ItemStack[]{new ItemStack(IC2Items.AUTO_FEED_MODULE), new ItemStack(IC2Items.AIR_REFILL_MODULE), new ItemStack(IC2Items.PROTECTION_MODULE)}));
                break;
            case LEGS:
                types.put(IArmorModule.ModuleType.MOVEMENT, ObjectArrayList.wrap(new ItemStack[]{new ItemStack(IC2Items.SPEED_MODULE)}));
        }

        setAndInstallTypes(stack, types);
        return stack;
    }

    public double getDamageAbsorptionRatio(ItemStack stack) {
        return this.slot == EquipmentSlot.FEET ? 1.1 : 1.0;
    }

    public int getEnergyPerDamage(ItemStack stack) {
        return 300;
    }

    public boolean isFullyAbsorbingFallDamage(ItemStack stack) {
        return true;
    }

    public String getTextureFolder() {
        return "armor/spectral";
    }

    public String getTextureName() {
        switch (this.slot) {
            case CHEST:
                return "chest";
            case FEET:
                return "boots";
            case HEAD:
                return "helmet";
            case LEGS:
                return "legs";
            default:
                return "";
        }
    }

    public String getArmorTexture() {
        return "ie:textures/models/armor/spectral";
    }


}
