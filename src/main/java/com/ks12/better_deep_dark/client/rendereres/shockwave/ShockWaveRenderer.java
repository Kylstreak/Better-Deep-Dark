package com.ks12.better_deep_dark.client.rendereres.shockwave;

import com.ks12.better_deep_dark.network.ModNetworkingConstants;
import com.ks12.better_deep_dark.util.RgbHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.RunArgs;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class ShockWaveRenderer {

    public static void registerClientHooks() {

        ClientPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.RENDER_SHOCKWAVE_PACKET_ID, (minecraftClient, clientPlayNetworkHandler, packetByteBuf, packetSender) -> {
            Vec3d center = packetByteBuf.readBlockPos().toCenterPos();
            float maxRadius = packetByteBuf.readFloat();
            int durationTicks = packetByteBuf.readInt();

            minecraftClient.execute(()-> {
                ClientShockWaves.ACTIVE.add(new ShockWaveInstance(center, maxRadius, durationTicks));
            });
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.isPaused()) return;

            for (ShockWaveInstance s : ClientShockWaves.ACTIVE) {
                s.tick();
            }

            ClientShockWaves.ACTIVE.removeIf(ShockWaveInstance::done);
        });

        WorldRenderEvents.LAST.register(ctx -> {
            for (ShockWaveInstance instance : ClientShockWaves.ACTIVE) {
                renderShockwave(instance, ctx.matrixStack(), ctx.camera().getPos(), ctx.tickDelta());
            }
        });
    }

    public static void renderShockwave(ShockWaveInstance instance, MatrixStack matrices, Vec3d cameraPos, float deltaTick) {
        float radius = instance.radius(deltaTick);
        float alpha = instance.alpha(deltaTick);

        if (alpha <= 0.01f || radius <= 0.01f) return;

        int latSteps = 16; // TODO: testing value only implement client settings
        int lonSteps = 24; // TODO: testing value only implement client settings

        matrices.push();

        double x = instance.center.x - cameraPos.x;
        double y = instance.center.y - cameraPos.y;
        double z = instance.center.z - cameraPos.z;
        matrices.translate(x, y, z);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);

        RenderSystem.setShader(GameRenderer::getPositionColorProgram);

        Matrix4f mat = matrices.peek().getPositionMatrix();

        Tessellator tes = Tessellator.getInstance();
        BufferBuilder bb = tes.getBuffer();

        bb.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);

        float rC = RgbHelper.normalize(41.0f);
        float gC = RgbHelper.normalize(223.0f);
        float bC = RgbHelper.normalize(235.0f);

        for (int i = 0; i < latSteps; i++) {
            float v0 = (float) i / latSteps;
            float v1 = (float) (i + 1) / latSteps;

            float lat0 = (v0 - 0.5f) * (float) Math.PI;
            float lat1 = (v1 - 0.5f) * (float) Math.PI;

            float y0 = (float) Math.sin(lat0);
            float y1 = (float) Math.sin(lat1);

            float cos0 = (float) Math.cos(lat0);
            float cos1 = (float) Math.cos(lat1);

            for (int j = 0; j < lonSteps; j++) {
                float u0 = (float) j / lonSteps;
                float u1 = (float) (j + 1) / lonSteps;

                float lon0 = u0 * ((float) Math.PI * 2f);
                float lon1 = u1 * ((float) Math.PI * 2f);

                float x0 = cos0 * (float) Math.cos(lon0);
                float z0 = cos0 * (float) Math.sin(lon0);

                float x1 = cos0 * (float) Math.cos(lon1);
                float z1 = cos0 * (float) Math.sin(lon1);

                float x2 = cos1 * (float) Math.cos(lon0);
                float z2 = cos1 * (float) Math.sin(lon0);

                float x3 = cos1 * (float) Math.cos(lon1);
                float z3 = cos1 * (float) Math.sin(lon1);

                float px0 = x0 * radius, py0 = y0 * radius, pz0 = z0 * radius;
                float px1 = x1 * radius, py1 = y0 * radius, pz1 = z1 * radius;
                float px2 = x2 * radius, py2 = y1 * radius, pz2 = z2 * radius;
                float px3 = x3 * radius, py3 = y1 * radius, pz3 = z3 * radius;

                bb.vertex(mat, px0, py0, pz0).color(rC, gC, bC, alpha).next();
                bb.vertex(mat, px2, py2, pz2).color(rC, gC, bC, alpha).next();
                bb.vertex(mat, px1, py1, pz1).color(rC, gC, bC, alpha).next();

                bb.vertex(mat, px1, py1, pz1).color(rC, gC, bC, alpha).next();
                bb.vertex(mat, px2, py2, pz2).color(rC, gC, bC, alpha).next();
                bb.vertex(mat, px3, py3, pz3).color(rC, gC, bC, alpha).next();
            }
        }

        tes.draw();

        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();

        matrices.pop();
    }
}
