package com.scand.ie.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.scand.ie.IEMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class QuantumQuarryScreen extends AbstractContainerScreen<QuantumQuarryMenu> {
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(IEMod.MOD_ID,"textures/gui/quantum_quarry_gui.png");

    public QuantumQuarryScreen(QuantumQuarryMenu menu, Inventory inv, Component comp) {
        super(menu, inv, comp);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        renderEUBar(pPoseStack, x, y);
    }

    private void renderEUBar(PoseStack pPoseStack, int x, int y){
        if(menu.isCrafting()){
            blit(pPoseStack, x+156, y+77-
                    menu.getScaledEU(), 176, 0, 8, menu.getScaledEU());
        }
    }


    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
