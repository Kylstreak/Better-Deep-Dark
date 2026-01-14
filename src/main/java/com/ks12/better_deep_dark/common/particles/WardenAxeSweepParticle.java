package com.ks12.better_deep_dark.common.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class WardenAxeSweepParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteSet;
    protected WardenAxeSweepParticle(ClientWorld clientWorld, double x, double y, double z, double d, SpriteProvider spriteSet) {
        super(clientWorld, x, y, z, 0.0, 0.0, 0.0);
        this.spriteSet = spriteSet;
        this.maxAge = 4;
        float f = this.random.nextFloat() * 0.6F + 0.4F;
        this.red = f;
        this.green = f;
        this.blue = f;
        this.scale = 1.0F - (float) d * 0.5F;
        this.setSpriteForAge(spriteSet);
    }

    @Override
    protected int getBrightness(float tint) {
        return 15728880;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge) {
            this.markDead();
        }
        else {
            this.setSpriteForAge(spriteSet);
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteSet;
        public Factory(SpriteProvider spriteSet) {
            this.spriteSet = spriteSet;
        }
        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new WardenAxeSweepParticle(world, x, y, z, velocityX, spriteSet);
        }
    }
}
