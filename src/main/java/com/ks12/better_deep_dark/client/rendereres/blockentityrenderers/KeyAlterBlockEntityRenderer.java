package com.ks12.better_deep_dark.client.rendereres.blockentityrenderers;

import com.ks12.better_deep_dark.common.blocks.blockenitities.KeyAlterBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class KeyAlterBlockEntityRenderer implements BlockEntityRenderer<KeyAlterBlockEntity> {
    public KeyAlterBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(KeyAlterBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        if (entity.getWorld() == null) return;

        ItemStack stack = entity.getStack();
        if (stack.isEmpty()) return;

        matrices.push();

        // Put it somewhere sane first; you can raise it later
        matrices.translate(0.5, 1.2, 0.5);

        float t = entity.getWorld().getTime() + tickDelta;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(t * 4.0f));
        matrices.scale(1.0f, 1.0f, 1.0f);

        // IMPORTANT: compute correct packed light for where the item is
        int packedLight = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());

        MinecraftClient.getInstance().getItemRenderer().renderItem(
                stack,
                ModelTransformationMode.GROUND,
                packedLight,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                0
        );

        matrices.pop();
    }
}
