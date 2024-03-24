package com.ks12.better_deep_dark.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class Unnamed extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    public Unnamed() {
        super(FabricBlockSettings.copyOf(Blocks.STONE));
        setDefaultState(getDefaultState().with(ACTIVE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockState(pos).get(ACTIVE)) {
            world.setBlockState(pos, state.with(ACTIVE, false));
        }
        else {
            world.setBlockState(pos, state.with(ACTIVE, true));
        }
        return ActionResult.SUCCESS;
    }
}
