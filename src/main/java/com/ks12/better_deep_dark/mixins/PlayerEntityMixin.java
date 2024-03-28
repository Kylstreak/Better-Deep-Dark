package com.ks12.better_deep_dark.mixins;

import com.ks12.better_deep_dark.items.ModItems;
import com.ks12.better_deep_dark.items.tools.WardenAxe;
import com.ks12.better_deep_dark.network.ClientRaycastEntity;
import com.ks12.better_deep_dark.network.ModNetworkingConstants;
import com.ks12.better_deep_dark.particles.ModParticle;
import com.ks12.better_deep_dark.player.PlayerAttributes;
import com.ks12.better_deep_dark.sounds.ModSounds;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow public abstract void attack(Entity target);

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow protected abstract void damageArmor(DamageSource source, float amount);

    @Inject(at = @At("TAIL"), method = "createPlayerAttributes", cancellable = true)
    private static void createPlayerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
//        cir.cancel();
//        cir.setReturnValue(LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1f).add(EntityAttributes.GENERIC_ATTACK_SPEED).add(EntityAttributes.GENERIC_LUCK).add(PlayerAttributes.DAMAGE_DEALT_WITH_WARDEN_AXE));
        cir.setReturnValue(cir.getReturnValue().add(PlayerAttributes.DAMAGE_DEALT_WITH_WARDEN_AXE));
    }

    @Inject(at = @At("HEAD"), method = "spawnSweepAttackParticles", cancellable = true)
    private void spawnSweepAttackParticles(CallbackInfo ci) {
        PlayerEntity instance = (PlayerEntity) (Object) this;

        if (instance.getMainHandStack().isOf(ModItems.WARDEN_SWORD)) {
            ci.cancel();
            double d = -MathHelper.sin(instance.getYaw() * ((float) Math.PI / 180));
            double e = MathHelper.cos(instance.getYaw() * ((float) Math.PI / 180));
            if (instance.getWorld() instanceof ServerWorld) {
                ((ServerWorld) instance.getWorld()).spawnParticles(ModParticle.WARDEN_SWORD_SWEEP, instance.getX() + d, instance.getBodyY(0.5), instance.getZ() + e, 0, d, 0.0, e, 0.0);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "attack")
    private void attack(CallbackInfo ci) {
        PlayerEntity instance = (PlayerEntity) (Object) this;

        if (instance.getMainHandStack().isOf(ModItems.WARDEN_AXE)) {
            boolean crit = instance.getAttackCooldownProgress(0.5f) > 0.9f && !instance.isOnGround() && instance.fallDistance > 0.0f && !instance.isClimbing() && !instance.isTouchingWater() && !instance.hasVehicle();
            if (instance instanceof ServerPlayerEntity) ClientRaycastEntity.sendRaycastRequest((ServerPlayerEntity) instance).thenAccept(entityId -> {
                if (entityId.equals(-1)) return;
                System.out.println(entityId);

                WardenAxe axe = (WardenAxe) instance.getMainHandStack().getItem();
                double damageDealt = instance.getAttributeBaseValue(PlayerAttributes.DAMAGE_DEALT_WITH_WARDEN_AXE) + 1;
                Entity target = instance.getWorld().getEntityById(entityId);

                instance.getAttributeInstance(PlayerAttributes.DAMAGE_DEALT_WITH_WARDEN_AXE).setBaseValue(damageDealt);

                if (target instanceof LivingEntity livingTarget) {
                    if (crit) {
                        axe.criticalAttackParticle(instance.getWorld(), instance);
                        if (damageDealt >= axe.damageStackThreshold) axe.doSonicBlast(instance, livingTarget,damageDealt * 0.3f);
                    } else {
                        axe.cleaveAttackParticle(instance.getWorld(), instance);
                    }
                    instance.getWorld().playSound(null, BlockPos.ofFloored(instance.getPos()), ModSounds.SONIC_CHARGE_SOUND, SoundCategory.PLAYERS, 3.0f, (float)(0.5 + (damageDealt * 0.2)));
                }
            });
        }
    }
}
