package com.ks12.better_deep_dark.client.rendereres.shockwave;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class ShockWaveInstance {
    public final Vec3d center;
    public final float maxRadius;
    public final int durationTicks;
    public int ageTicks;

    public ShockWaveInstance(Vec3d center, float maxRadius, int durationTicks) {
        this.center = center;
        this.maxRadius = maxRadius;
        this.durationTicks = durationTicks;
    }

    public float radius(float tickDelta) {
        float t = (ageTicks + tickDelta) / (float) durationTicks;
        t = MathHelper.clamp(t, 0f, 1f);
        return maxRadius * t;
    }

    public float alpha(float tickDelta) {
        float t = (ageTicks + tickDelta) / (float) durationTicks;
        t = MathHelper.clamp(t, 0f, 1f);
        return 1.0f - t;
    }

    public boolean done() {
        return ageTicks >= durationTicks;
    }

    public void tick() {
        ageTicks++;
    }
}


