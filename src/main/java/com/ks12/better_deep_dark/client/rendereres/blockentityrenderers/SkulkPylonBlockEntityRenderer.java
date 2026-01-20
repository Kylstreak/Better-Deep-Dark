package com.ks12.better_deep_dark.client.rendereres.blockentityrenderers;

import com.ks12.better_deep_dark.BetterDeepDark;
import com.ks12.better_deep_dark.client.rendereres.RenderUtils;
import com.ks12.better_deep_dark.common.blocks.blockenitities.SkulkPylonBlockEntity;
import com.ks12.better_deep_dark.registry.ModClientModels;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import org.joml.Matrix4f;

public class SkulkPylonBlockEntityRenderer implements BlockEntityRenderer<SkulkPylonBlockEntity> {

    private static final Identifier CRYSTAL_MODEL = ModClientModels.SKULK_PYLON_CRYSTAL_ID;

    public SkulkPylonBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}
    @Override
    public void render(SkulkPylonBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        if (world == null) return;

        float t = world.getTime() + tickDelta;
        float animTime = entity.animTime;
        float k = animTime * animTime;

        float bob = MathHelper.sin(t * 0.10f) * 0.08f * k;
        float spin = t * 0.06f * k;
        float glow = 0.75f + 0.25f * MathHelper.sin(t * 0.4f);

        matrices.push();

        matrices.translate(0.5, 0.0, 0.5);

        drawColoredBeam(matrices, 0.10f, 0.75f + animTime * 0.55f + bob, 0.03f, 41f, 223f, 235f, 0.9f);
        drawColoredBeam(matrices, 0.10f, 0.75f + animTime * 0.55f + bob, 0.10f, 255f, 255f, 255f, 0.25f * glow);

        matrices.translate(0.0, 0.75f + animTime * 0.55f + bob, 0.0);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation(spin));

        matrices.translate(-0.5, 0.0, -0.5);

        renderModel(entity, matrices, vertexConsumers, overlay, world);


        matrices.pop();
    }

    private static void renderModel(SkulkPylonBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int overlay, World world) {
        MinecraftClient client = MinecraftClient.getInstance();
        BlockRenderManager manager = client.getBlockRenderManager();
        BakedModel orbModel = client.getBakedModelManager().getModel(CRYSTAL_MODEL);

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getTranslucent());

        manager.getModelRenderer().render(
                world,
                orbModel,
                entity.getCachedState(),
                entity.getPos(),
                matrices,
                vc,
                false,
                world.random,
                0,
                overlay
        );
    }

    private static void drawColoredBeam(MatrixStack matrices, float y0, float y1, float halfWidth, float r, float g, float b, float a) {
        if (y1 <= y0) return;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(515);
        RenderSystem.depthMask(false);

        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);

        Matrix4f mat = matrices.peek().getPositionMatrix();

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder bb = tess.getBuffer();

        bb.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);

        RenderUtils.createColoredQuad(bb, mat, -halfWidth, y0, 0f, halfWidth, y1, 0f, r, g, b, a);
        RenderUtils.createColoredQuad(bb, mat, 0f, y0, -halfWidth, 0f, y1, halfWidth, r, g, b, a);

        tess.draw();

        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }
}
