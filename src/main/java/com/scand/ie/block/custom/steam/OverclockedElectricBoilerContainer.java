package com.scand.ie.block.custom.steam;

import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.components.simple.ChargebarComponent;
import ic2.core.inventory.gui.components.simple.TankComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class OverclockedElectricBoilerContainer extends ContainerComponent<OverclockedElectricBoilerTile> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("ie", "textures/gui/overclocked_electric_boiler.png");

    public static final Box2i WATER_TANK = new Box2i(61, 15, 16, 58);
    public static final Box2i STEAM_TANK = new Box2i(103, 15, 16, 58);
    public static final Vec2i TANK_POS = new Vec2i(176, 31);

    public static final Vec2i CHARGE_POS = new Vec2i(176, 0);
    public OverclockedElectricBoilerContainer(OverclockedElectricBoilerTile key, Player player, int id) {
        super(key, player, id);
        this.addSlot(FilterSlot.createFluidDrainSlot(key, 0, 38, 19, new Fluid[]{Fluids.WATER}));
        this.addSlot(FilterSlot.createFluidOutputSlot(key, 1, 38, 53));
        this.addComponent(new TankComponent(WATER_TANK, TANK_POS, key.waterTank));
        this.addComponent(new TankComponent(STEAM_TANK, TANK_POS, key.steamTank));
        this.addComponent(new ChargebarComponent(key.getChargeBox(), key, CHARGE_POS, true));


        this.addPlayerInventory(player.getInventory());

    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }
}
