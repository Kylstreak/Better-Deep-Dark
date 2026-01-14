package com.ks12.better_deep_dark.common.tools.items;

import com.ks12.better_deep_dark.BetterDeepDark;
import com.ks12.better_deep_dark.registry.ModSounds;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WardenSword extends SwordItem {
    public static final double WARDENS_CRY_POWER = 8;
    public static final double WARDENS_CRY_RANGE = 5;
    public static final String damageDealtTag = "damage_dealt";

    public WardenSword() {super(WardenToolMaterial.INSTANCE, 3, -2.5f, new Item.Settings().fireproof());}

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

    public void doWardensCry(LivingEntity player) {
        World world = player.getWorld();

        BetterDeepDark.LOGGER.warn("attempting wardens cry");

        if (world instanceof ServerWorld) {
            int xN = MathHelper.floor(player.getX() - WARDENS_CRY_RANGE - 1.0d);
            int xP = MathHelper.floor(player.getX() + WARDENS_CRY_RANGE + 1.0d);
            int yN = MathHelper.floor(player.getY() - WARDENS_CRY_RANGE - 1.0d);
            int yP = MathHelper.floor(player.getY() + WARDENS_CRY_RANGE + 1.0d);
            int zN = MathHelper.floor(player.getZ() - WARDENS_CRY_RANGE - 1.0d);
            int zP = MathHelper.floor(player.getZ() + WARDENS_CRY_RANGE + 1.0d);

            List<Entity> entitiesInBound = world.getOtherEntities(player, new Box(xN, yN, zN, xP, yP, zP));

            Vec3d pos = player.getPos();

            for (Entity entity : entitiesInBound) {
                if (entity instanceof LivingEntity livingEntity) {
                    Vec3d entityPos = livingEntity.getPos();
                    Vec3d delta = entityPos.subtract(pos);

                    Vec3d horizontal = new Vec3d(delta.x, 0.0d, delta.z);
                    double hDist = Math.max(horizontal.length(), 1.0d);

                    Vec3d dir = horizontal.multiply(1.0 / hDist);

                    double falloff = 1.0d - MathHelper.clamp(hDist / WARDENS_CRY_RANGE, 0.0, 1.0);

                    double strength = WARDENS_CRY_POWER * falloff;

                    double yBoost = 0.85 + 0.25 * falloff;

                    Vec3d velocity = new Vec3d(dir.x * strength, yBoost, dir.z * strength);

                    livingEntity.setVelocity(velocity);


                    livingEntity.velocityModified = true;
                    livingEntity.velocityDirty = true;
                }
            }

            world.playSound(null, BlockPos.ofFloored(player.getPos()), ModSounds.SONIC_BLAST_SOUND, SoundCategory.BLOCKS, 3.0f, 1f);
        }
    }
}
