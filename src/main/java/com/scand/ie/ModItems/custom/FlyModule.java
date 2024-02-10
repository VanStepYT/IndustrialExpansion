package com.scand.ie.ModItems.custom;

import com.scand.ie.IEMod;
import ic2.api.tiles.IMachine;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.item.upgrades.base.BaseUpgradeItem;
import ic2.core.item.wearable.jetpacks.ElectricJetpack;
import ic2.core.item.wearable.modules.AirRefillModuleItem;
import ic2.core.item.wearable.modules.BaseModuleItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FlyModule extends BaseModuleItem {


    public FlyModule(String textureFolder, String textureName) {
        super("fly_module", (PropertiesBuilder)null, textureFolder, textureName, ModuleType.GENERIC);
    }

    @Override
    public boolean canInstallInArmor(ItemStack stack, ItemStack armor, EquipmentSlot type) {
        return type == EquipmentSlot.CHEST;
    }

    /*@Override
    public void onInstall(ItemStack stack, ItemStack armor, IArmorModuleHolder holder) {
        Player player = (Player) holder;
        player.getAbilities().flying = true;
    }*/


   /* @Override
    public void onTick(ItemStack stack, ItemStack armor, Level world, Player player) {
        if(!player.getAbilities().mayfly){
            player.getAbilities().mayfly = true;
        }
    }*/

    @Override
    public void onUnequipped(ItemStack stack, ItemStack armor, Player entity) {
        IEMod.LOGGER.info("Disable");
        entity.getAbilities().flying = false;
        entity.getAbilities().mayfly = false;
        entity.getAbilities().setFlyingSpeed(0.05f);
        entity.onUpdateAbilities();

        IEMod.LOGGER.info(String.valueOf(entity.getAbilities().mayfly));
    }

    public void onEquipped(ItemStack stack, ItemStack armor, Player entity) {
        IEMod.LOGGER.info("Enable");

        entity.getAbilities().mayfly = true;
        entity.getAbilities().setFlyingSpeed(0.1f);
        entity.onUpdateAbilities();
        IEMod.LOGGER.info(String.valueOf(entity.getAbilities().mayfly));
    }
}
