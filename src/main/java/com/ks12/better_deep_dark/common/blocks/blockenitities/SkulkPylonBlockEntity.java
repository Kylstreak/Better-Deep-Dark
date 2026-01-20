package com.ks12.better_deep_dark.common.blocks.blockenitities;

import com.ks12.better_deep_dark.common.blocks.SkulkPylon;
import com.ks12.better_deep_dark.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SkulkPylonBlockEntity extends BlockEntity {

    public float animTime;
    public boolean portalActivated = false;
    public boolean canActivatePortal = false;

    public SkulkPylonBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SKULK_PYLON_BLOCK_ENTITY, pos, state);
    }

    public boolean isActive() {
        return getCachedState().get(SkulkPylon.ACTIVE) && animTime >= 1.0f;
    }

    public void setCanActivatePortal(boolean canActivatePortal) {
        this.canActivatePortal = canActivatePortal;
    }

    public static void tick(World world, BlockPos pos, BlockState state, SkulkPylonBlockEntity be) {
        if (!world.isClient()) return;

        boolean active = state.get(SkulkPylon.ACTIVE);
        float speed = 0.09f;

        be.animTime = MathHelper.clamp(be.animTime + (active ? speed : -speed), 0f, 1f);

    }
}
