package com.scand.ie.block.custom.solar_reactor;

import com.mojang.blaze3d.vertex.PoseStack;
import ic2.api.tiles.readers.IProgressMachine;
import ic2.core.inventory.gui.components.GuiWidget;
import ic2.core.inventory.gui.components.simple.ProgressComponent;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Set;

public class SolarHeatComponent extends GuiWidget {
    SolarReactorTile machine;
    Vec2i offset;
    boolean vertical;
    public SolarHeatComponent(Box2i box, SolarReactorTile machine, Vec2i offset, boolean vertical) {
        super(box);
        this.machine = machine;
        this.offset = offset;
        this.vertical = vertical;
    }

    protected void addRequests(Set<GuiWidget.ActionRequest> requests) {
       requests.add(ActionRequest.DRAW_BACKGROUND);
    }

   @OnlyIn(Dist.CLIENT)
    public void drawBackground(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
        float heat = this.machine.getHeat();
        if (heat >= 0.0F) {
            Box2i box = this.getBox();
            int width = box.getWidth();
            int height = box.getHeight();
            float lvl = (float)(this.vertical ? height : width) * Math.min(1.0F, heat / this.machine.getMaxHeat());
            if (lvl <= 0.0F) {
                return;
            }

            if (this.vertical) {
                this.gui.drawTextureRegion(matrix, (float)(this.gui.getGuiLeft() + box.getX()),
                        (float)(this.gui.getGuiTop() + box.getY()) + ((float)height - lvl), (float)this.offset.getX(), (float)this.offset.getY() + ((float)height - lvl), (float)width, lvl);
                return;
            }

            this.gui.drawTextureRegion(matrix, (float)(this.gui.getGuiLeft() + box.getX()), (float)(this.gui.getGuiTop() + box.getY()), (float)this.offset.getX(), (float)this.offset.getY(), lvl, (float)height);
        }

    }
}
