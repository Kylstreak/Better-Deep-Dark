package com.ks12.better_deep_dark.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class ModUtil {

    public static BlockHitResult rayCast(PlayerEntity player, double maxDistance) {
        Vec3d cameraPos = player.getCameraPosVec(1.0f);
        Vec3d rot = player.getRotationVec(1.0f);
        Vec3d rayCastContext = cameraPos.add(rot.x * maxDistance, rot.y * maxDistance, rot.z * maxDistance);
        return player.getWorld().raycast(new RaycastContext(cameraPos, rayCastContext, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
    }
}
