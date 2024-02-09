package com.scand.ie.block.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import ic2.core.inventory.gui.IC2Screen;
import ic2.core.inventory.gui.components.GuiWidget;
import ic2.core.utils.helpers.Formatters;
import ic2.core.utils.math.geometry.Box2i;

import java.text.DecimalFormat;
import java.util.Set;

public class IridiumFabricatorComponent extends GuiWidget {

    IridiumFabricatorTile tile;

    public IridiumFabricatorComponent(IridiumFabricatorTile tile) {
        super(Box2i.EMPTY_BOX);
        this.tile = tile;
    }

    @Override
    protected void addRequests(Set<ActionRequest> set) {
        set.add(ActionRequest.DRAW_FOREGROUND);
    }

    @Override
    public void drawForeground(PoseStack matrix, int mouseX, int mouseY) {
        IC2Screen gui = this.gui;
        gui.drawString(matrix, this.translate("ie:gui.progress"), 20,20,4210752);

        float progress = tile.getProgress() / tile.getMaxProgress();
        DecimalFormat format = Formatters.EU_READER_FORMAT;
        gui.drawString(matrix, this.string(format.format(progress * 100.0F) + "%"), 20,30,4210752);
        //gui.drawString(matrix, this.string(tile.getProgress() + "EU"), 30,40,4210752);
    }
}
