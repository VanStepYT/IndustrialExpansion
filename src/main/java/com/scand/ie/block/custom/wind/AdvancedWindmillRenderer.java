package com.scand.ie.block.custom.wind;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import ic2.core.block.generators.tiles.WindmillTileEntity;
import ic2.core.block.rendering.models.RotorModel;
import ic2.core.block.rendering.tile.WindmillRenderer;
import ic2.core.item.misc.WindmillBladeItem;
import ic2.core.utils.collection.CollectionUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.Objects;

import static ic2.core.block.rendering.tile.WindmillRenderer.getMaterial;

public class AdvancedWindmillRenderer implements BlockEntityRenderer<AdvancedWindmillTileEntity> {
   Int2ObjectMap<RotorModel> models = new Int2ObjectOpenHashMap();
    static final Map<ResourceLocation, Material> MATERIALS = CollectionUtils.createMap();

    public AdvancedWindmillRenderer(BlockEntityRendererProvider.Context context) {
    }

    public boolean shouldRenderOffScreen(WindmillTileEntity p_188185_1_) {
        return true;
    }

    @Override
    public void render(AdvancedWindmillTileEntity tile, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ResourceLocation location = tile.getTexture();
        int radius = tile.getRadius();
        if (radius > 0 && location != null) {
            tile.rotation = (tile.rotation + 4) % 360.0F;
            RotorModel model = (RotorModel)this.models.computeIfAbsent(radius, RotorModel::new);
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5, 0.5, 0.5);
            matrixStackIn.mulPose(new Quaternion(0.0F, -tile.getFacing().toYRot() + 90.0F, 0.0F, true));
            matrixStackIn.mulPose(new Quaternion(tile.rotation, 0.0F, 0.0F, true));
            matrixStackIn.translate(-0.25, 0.0, 0.0);
            Material var10002 = getMaterial(location);
            Objects.requireNonNull(model);
            model.renderToBuffer(matrixStackIn, var10002.buffer(bufferIn, model::renderType), LevelRenderer.getLightColor(tile.getLevel(), tile.getBlockPos().relative(tile.getFacing())), combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
    }

    public static Material getMaterial(ResourceLocation location) {
        return (Material)MATERIALS.computeIfAbsent(location, AdvancedWindmillRenderer::createMaterial);
    }

    private static Material createMaterial(ResourceLocation location) {
        return new Material(WindmillBladeItem.ROTOR_SHEET, location);
    }
}
