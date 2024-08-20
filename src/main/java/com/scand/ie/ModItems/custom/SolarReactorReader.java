package com.scand.ie.ModItems.custom;

import com.scand.ie.block.ModBlocks;
import com.scand.ie.block.custom.solar_reactor.SolarReactorTile;
import ic2.core.item.base.IC2SimpleItem;
import ic2.core.item.tool.EUReaderTool;
import ic2.core.wiki.components.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class SolarReactorReader extends IC2SimpleItem {
    public SolarReactorReader(String itemName, String textureName) {
        super(itemName, null, "reactor/solar/tools", textureName);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {

        if(ctx.getLevel().isClientSide){
            return InteractionResult.PASS;
        }

        if(ctx.getPlayer().getLevel().getBlockState(ctx.getClickedPos()).getBlock() == ModBlocks.SOLAR_REACTOR.get()){

            SolarReactorTile tile = (SolarReactorTile) ctx.getLevel().getBlockEntity(ctx.getClickedPos());

            ctx.getPlayer().displayClientMessage(
                    this.translate("tooltip.ie.solar_production").append(String.valueOf(tile.generation)),
                    false);

            ctx.getPlayer().displayClientMessage(
                    this.translate("tooltip.ie.solar_heat").append(String.valueOf(tile.heat)),
                    false);

            ctx.getPlayer().displayClientMessage(
                    this.translate("tooltip.ie.solar_cooling").append(String.valueOf(tile.cooling)),
                    false);

            ctx.getPlayer().displayClientMessage(
                    this.translate("tooltip.ie.solar_heating").append(String.valueOf(tile.heating)),
                    false);

            ctx.getPlayer().displayClientMessage(
                    this.translate(tile.cooling>tile.heating ?
                            "tooltip.ie.solar_stable" : "tooltip.ie.solar_nonstable"
                    ), false);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
