package com.ks12.better_deep_dark.mixins;

import com.ks12.better_deep_dark.items.ModItems;
import com.ks12.better_deep_dark.items.tools.WardenAxe;
import com.ks12.better_deep_dark.network.ClientRaycastEntity;
import com.ks12.better_deep_dark.particles.ModParticle;
import com.ks12.better_deep_dark.player.PlayerAttributes;
import com.ks12.better_deep_dark.sounds.ModSounds;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    @ModifyReturnValue(at = @At("RETURN"), method = "createPlayerAttributes")
    private static DefaultAttributeContainer.Builder appendPlayerAttributes(DefaultAttributeContainer.Builder original) {
        return original.add(PlayerAttributes.DAMAGE_DEALT_WITH_WARDEN_AXE);
    }

    @ModifyArg(method = "spawnSweepAttackParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"), index = 0)
    private ParticleEffect adjustSweepParticle(ParticleEffect particle) {
        return this.getMainHandStack().isOf(ModItems.WARDEN_SWORD) ? ModParticle.WARDEN_SWORD_SWEEP : particle;
    }

    @Inject(at = @At("HEAD"), method = "attack")
    private void axeCombatModification(CallbackInfo ci) {

        if (this.getMainHandStack().isOf(ModItems.WARDEN_AXE)) {
            boolean crit = getAttackCooldownProgress(0.5f) > 0.9f && !this.isOnGround() && this.fallDistance > 0.0f && !this.isClimbing() && !this.isTouchingWater() && !this.hasVehicle();
            if (((PlayerEntity)(Object) this) instanceof ServerPlayerEntity) ClientRaycastEntity.sendRaycastRequest((ServerPlayerEntity)(Object) this).thenAccept(entityId -> {
                if (entityId.equals(-1)) return;
                System.out.println(entityId);

                WardenAxe axe = (WardenAxe) this.getMainHandStack().getItem();
                double damageDealt = this.getAttributeValue(PlayerAttributes.DAMAGE_DEALT_WITH_WARDEN_AXE) + 1;
                Entity target = this.getWorld().getEntityById(entityId);

                this.getAttributeInstance(PlayerAttributes.DAMAGE_DEALT_WITH_WARDEN_AXE).setBaseValue(damageDealt);

                if (target instanceof LivingEntity livingTarget) {
                    if (crit) {
                        axe.criticalAttackParticle(this.getWorld(), this);
                        if (damageDealt >= axe.damageStackThreshold) axe.doSonicBlast(this, livingTarget,damageDealt * 0.3f);
                    } else {
                        axe.cleaveAttackParticle(this.getWorld(), this);
                    }
                    this.getWorld().playSound(null, BlockPos.ofFloored(this.getPos()), ModSounds.SONIC_CHARGE_SOUND, SoundCategory.PLAYERS, 3.0f, (float)(0.5 + (damageDealt * 0.2)));
                }
            });
        }
    }
}
