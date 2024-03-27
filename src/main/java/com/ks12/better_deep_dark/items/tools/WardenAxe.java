package com.ks12.better_deep_dark.items.tools;

import com.ks12.better_deep_dark.particles.ModParticle;
import com.ks12.better_deep_dark.sounds.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.server.tag.ValueLookupTagProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WardenAxe extends AxeItem {
    public boolean shouldCrit = false;
    public double damageStacked = 0d;
    private final double damageStackThreshold = 6d;

    public WardenAxe() {
        super(WardenToolMaterial.INSTANCE, 5, -3, new FabricItemSettings());
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            World world = player.getWorld();
            if (shouldCrit) {
                if (damageStacked >= damageStackThreshold) {
                    double knockbackMultiplier = damageStacked * 0.3f;
                    float lookX = MathHelper.sin(player.getYaw() * ((float) Math.PI / 180));
                    double lookZ = -MathHelper.cos(player.getYaw() * ((float) Math.PI / 180));

                    target.velocityDirty = true;
                    Vec3d vec3d = target.getVelocity();
                    Vec3d vec3d2 = new Vec3d(lookX, 0.0, lookZ).normalize().multiply(knockbackMultiplier);
                    target.setVelocity(vec3d.x / 2.0 - vec3d2.x, target.isOnGround() ? Math.min(0.4, vec3d.y / 2.0 + knockbackMultiplier) : vec3d.y, vec3d.z / 2.0 - vec3d2.z);
                    damageStacked = 0;

                    if (world instanceof ServerWorld serverWorld) {
                        Vec3d particleVec = player.getPos().add(0.0, 0.4f, 0.0);
                        Vec3d particleVec2 = target.getPos().add(0.0, 1.6, 0.0).subtract(particleVec);
                        Vec3d particleVec3 = particleVec2.normalize();
                        for (int i = 0; i < MathHelper.floor(particleVec2.length()) + 7; ++i) {
                            Vec3d particleVec4 = particleVec.add(particleVec3.multiply(i));
                            serverWorld.spawnParticles(ParticleTypes.SONIC_BOOM, particleVec4.x, particleVec4.y, particleVec4.z, 1, 0.0, 0.0, 0.0, 0.0);
                        }
                        world.playSound(null, BlockPos.ofFloored(player.getPos()), ModSounds.SONIC_BLAST_SOUND, SoundCategory.BLOCKS, 3.0f, 1f);
                    }
                }
                else {
                    world.playSound(null, BlockPos.ofFloored(player.getPos()), ModSounds.SONIC_CHARGE_SOUND, SoundCategory.PLAYERS, 3.0f, (float)(0.5 + (damageStacked * 0.2)));
                }
            }
            else {
                world.playSound(null, BlockPos.ofFloored(player.getPos()), ModSounds.SONIC_CHARGE_SOUND, SoundCategory.PLAYERS, 3.0f, (float)(0.5 + (damageStacked * 0.2)));
            }
        }
        return true;
    }

    public void criticalAttackParticle(World world, PlayerEntity player) {
        double d = -MathHelper.sin(player.getYaw() * ((float) Math.PI / 180));
        double e = MathHelper.cos(player.getYaw() * ((float) Math.PI / 180));

        if (world instanceof ServerWorld) {
            ((ServerWorld) world).spawnParticles(ModParticle.WARDEN_AXE_SWEEP, player.getX() + d, player.getBodyY(0.5), player.getZ() + e, 0, d, 0.0, e, 0.0);
        }
    }

    public void cleaveAttackParticle(World world, PlayerEntity player) {
        double d = -MathHelper.sin(player.getYaw() * ((float) Math.PI / 180));
        double e = MathHelper.cos(player.getYaw() * ((float) Math.PI / 180));

        if (world instanceof ServerWorld) {
            ((ServerWorld) world).spawnParticles(ModParticle.WARDEN_SWORD_SWEEP, player.getX() + d, player.getBodyY(0.5), player.getZ() + e, 0, d, 0.0, e, 0.0);
        }
    }
}
