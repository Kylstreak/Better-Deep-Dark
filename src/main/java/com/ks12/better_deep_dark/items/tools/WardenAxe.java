package com.ks12.better_deep_dark.items.tools;

import com.ks12.better_deep_dark.BetterDeepDark;
import com.ks12.better_deep_dark.registry.ModParticles;
import com.ks12.better_deep_dark.registry.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WardenAxe extends AxeItem {
    public static final double damageStackThreshold = 5d;
    public static final String damageDealtTag = "damage_dealt";
    public WardenAxe() {
        super(WardenToolMaterial.INSTANCE, 5, -3, new FabricItemSettings());
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt;
        if ((nbt = stack.getNbt()) != null) {
            super.appendTooltip(stack, world, tooltip, context);
            tooltip.add(Text.literal("Damage dealt: %s".formatted(((int) nbt.getDouble(damageDealtTag)))).formatted(Formatting.BLUE));
        }
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        stack.getOrCreateNbt().putDouble(damageDealtTag, 0);
        return stack;
    }

    public void criticalAttackParticle(World world, LivingEntity player) {
        double d = -MathHelper.sin(player.getYaw() * ((float) Math.PI / 180));
        double e = MathHelper.cos(player.getYaw() * ((float) Math.PI / 180));

        if (world instanceof ServerWorld) {
            ((ServerWorld) world).spawnParticles(ModParticles.WARDEN_AXE_SWEEP, player.getX() + d, player.getBodyY(0.5), player.getZ() + e, 0, d, 0.0, e, 0.0);
        }
    }

    public void cleaveAttackParticle(World world, LivingEntity player) {
        double d = -MathHelper.sin(player.getYaw() * ((float) Math.PI / 180));
        double e = MathHelper.cos(player.getYaw() * ((float) Math.PI / 180));

        if (world instanceof ServerWorld) {
            ((ServerWorld) world).spawnParticles(ModParticles.WARDEN_SWORD_SWEEP, player.getX() + d, player.getBodyY(0.5), player.getZ() + e, 0, d, 0.0, e, 0.0);
        }
    }

    public void doSonicBlast(LivingEntity player, LivingEntity target, double knockbackMultiplier) {
        World world = player.getWorld();

        float lookX = MathHelper.sin(player.getYaw() * ((float) Math.PI / 180));
        double lookZ = -MathHelper.cos(player.getYaw() * ((float) Math.PI / 180));

        target.velocityDirty = true;
        Vec3d vec3d = target.getVelocity();
        Vec3d vec3d2 = new Vec3d(lookX, 0.0, lookZ).normalize().multiply(knockbackMultiplier);
        target.setVelocity(vec3d.x / 2.0 - vec3d2.x, target.isOnGround() ? Math.min(0.4, vec3d.y / 2.0 + knockbackMultiplier) : vec3d.y, vec3d.z / 2.0 - vec3d2.z);
        target.velocityModified = true;

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
}
