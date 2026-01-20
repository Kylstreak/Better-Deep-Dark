package com.ks12.better_deep_dark.client.rendereres;

import com.ks12.better_deep_dark.util.RgbHelper;
import net.minecraft.client.render.BufferBuilder;
import org.joml.Matrix4f;

public class RenderUtils {

    public static void createColoredQuad(BufferBuilder bb, Matrix4f mat, float x0, float y0, float z0, float x1, float y1, float z1, float r, float g, float b, float a) {
        float rNorm = RgbHelper.normalize(r);
        float gNorm = RgbHelper.normalize(g);
        float bNorm = RgbHelper.normalize(b);

        bb.vertex(mat, x0, y0, z0).color(rNorm, gNorm, bNorm, a).next();
        bb.vertex(mat, x0, y1, z0).color(rNorm, gNorm, bNorm, a).next();
        bb.vertex(mat, x1, y1, z1).color(rNorm, gNorm, bNorm, a).next();

        bb.vertex(mat, x1, y1, z1).color(rNorm, gNorm, bNorm, a).next();
        bb.vertex(mat, x1, y0, z1).color(rNorm, gNorm, bNorm, a).next();
        bb.vertex(mat, x0, y0, z0).color(rNorm, gNorm, bNorm, a).next();
    }
}
