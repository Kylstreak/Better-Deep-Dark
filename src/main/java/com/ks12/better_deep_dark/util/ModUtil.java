package com.ks12.better_deep_dark.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class ModUtil {

    public static BlockHitResult rayCastBlock(PlayerEntity player, double maxDistance) {
        Vec3d cameraPos = player.getCameraPosVec(1.0f);
        Vec3d rot = player.getRotationVec(1.0f);
        Vec3d rayCastContext = cameraPos.add(rot.x * maxDistance, rot.y * maxDistance, rot.z * maxDistance);
        return player.getWorld().raycast(new RaycastContext(cameraPos, rayCastContext, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
    }

    public static HitResult raycastEntity(PlayerEntity player, double maxDistance) {
        Entity cameraEntity = MinecraftClient.getInstance().cameraEntity;
        if (cameraEntity != null) {
            Vec3d cameraPos = player.getCameraPosVec(1.0f);
            Vec3d rot = player.getRotationVec(1.0f);
            Vec3d rayCastContext = cameraPos.add(rot.x * maxDistance, rot.y * maxDistance, rot.z * maxDistance);
            Box box = cameraEntity.getBoundingBox().stretch(rot.multiply(maxDistance)).expand(1d, 1d, 1d);
            return ProjectileUtil.raycast(cameraEntity, cameraPos, rayCastContext, box, (entity -> /* any custom parameters here */ !entity.isSpectator() && entity.canHit()), maxDistance);
        }
        return null;
    }
}
