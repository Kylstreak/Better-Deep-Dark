package com.ks12.better_deep_dark.mixin;

import com.ks12.better_deep_dark.BetterDeepDark;
import com.ks12.better_deep_dark.items.tools.WardenAxe;
import com.ks12.better_deep_dark.network.ClientRaycastEntity;
import com.ks12.better_deep_dark.registry.ModItems;
import com.ks12.better_deep_dark.registry.ModParticles;
import com.ks12.better_deep_dark.registry.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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

    @Shadow public abstract ActionResult interact(Entity entity, Hand hand);

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @ModifyArg(method = "spawnSweepAttackParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"), index = 0)
    private ParticleEffect adjustSweepParticle(ParticleEffect particle) {
        return this.getMainHandStack().isOf(ModItems.WARDEN_SWORD) ? ModParticles.WARDEN_SWORD_SWEEP : particle;
    }

    @Inject(at = @At("HEAD"), method = "attack")
    private void axeCombatModification(CallbackInfo ci) {
        ItemStack itemInstance;
        if ((itemInstance = this.getMainHandStack()).isOf(ModItems.WARDEN_AXE)) {
            if (getAttackCooldownProgress(0.5f) > 0.9f) {
                boolean crit = !this.isOnGround() && this.fallDistance > 0.0f && !this.isClimbing() && !this.isTouchingWater() && !this.hasVehicle();
                if (((PlayerEntity) (Object) this) instanceof ServerPlayerEntity)
                    ClientRaycastEntity.sendRaycastRequest((ServerPlayerEntity) (Object) this).thenAccept(entityId -> {
                        if (entityId.equals(-1)) return;

                        WardenAxe axe = (WardenAxe) this.getMainHandStack().getItem();
                        double damageDealt = itemInstance.getNbt().getDouble(WardenAxe.damageDealtTag) + 1;
                        Entity target = this.getWorld().getEntityById(entityId);

                        itemInstance.getOrCreateNbt().putDouble(WardenAxe.damageDealtTag, damageDealt);

                        if (target instanceof LivingEntity livingTarget) {
                            if (crit) {
                                axe.criticalAttackParticle(this.getWorld(), this);
                                if (damageDealt >= WardenAxe.damageStackThreshold) {
                                    axe.doSonicBlast(this, livingTarget, damageDealt * 0.3f);
                                    itemInstance.getOrCreateNbt().putDouble(WardenAxe.damageDealtTag, 0);
                                }
                            } else {
                                axe.cleaveAttackParticle(this.getWorld(), this);
                            }
                            this.getWorld().playSound(null, BlockPos.ofFloored(this.getPos()), ModSounds.SONIC_CHARGE_SOUND, SoundCategory.PLAYERS, 3.0f, (float) (0.5 + (damageDealt * 0.2)));
                        }
                    });
            }
        }
    }
}
