package com.ks12.better_deep_dark.mixins;

import com.ks12.better_deep_dark.items.ModItems;
import com.ks12.better_deep_dark.items.tools.WardenAxe;
import com.ks12.better_deep_dark.particles.ModParticle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow public abstract void attack(Entity target);

    @Inject(at = @At("HEAD"), method = "spawnSweepAttackParticles()V", cancellable = true)
    private void spawnSweepAttackParticles(CallbackInfo ci) {
        PlayerEntity instance = (PlayerEntity)(Object) this;

        if (instance.getMainHandStack().isOf(ModItems.WARDEN_SWORD)) {
            ci.cancel();
            double d = -MathHelper.sin(instance.getYaw() * ((float) Math.PI / 180));
            double e = MathHelper.cos(instance.getYaw() * ((float) Math.PI / 180));
            if (instance.getWorld() instanceof ServerWorld) {
                ((ServerWorld)instance.getWorld()).spawnParticles(ModParticle.WARDEN_SWORD_SWEEP, instance.getX() + d, instance.getBodyY(0.5), instance.getZ() + e, 0, d, 0.0, e, 0.0);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "attack")
    private void attack(CallbackInfo ci) {
        PlayerEntity instance = (PlayerEntity)(Object) this;

        if (instance.getMainHandStack().isOf(ModItems.WARDEN_AXE)) {
            WardenAxe axe = (WardenAxe) instance.getMainHandStack().getItem();

            HitResult hitResult = MinecraftClient.getInstance().crosshairTarget;

            if (hitResult != null && hitResult.getType() == HitResult.Type.ENTITY) {
                Entity target = ((EntityHitResult) hitResult).getEntity();
                if (target instanceof LivingEntity livingTarget) {
                    boolean crit = instance.getAttackCooldownProgress(0.5f) > 0.9f && !instance.isOnGround() && instance.fallDistance > 0.0f && !instance.isClimbing() && !instance.isTouchingWater() && !instance.hasVehicle();
                    if (crit) {
                        axe.criticalAttackParticle(instance.getWorld(), instance);
                        axe.shouldCrit = true;
                        axe.damageStacked += 0.5;
                    }
                    else {
                        axe.cleaveAttackParticle(instance.getWorld(), instance);
                        axe.shouldCrit = false;
                        axe.damageStacked += 0.5;
                    }
                    System.out.println(axe.damageStacked);
                }
            }
        }
    }
}
