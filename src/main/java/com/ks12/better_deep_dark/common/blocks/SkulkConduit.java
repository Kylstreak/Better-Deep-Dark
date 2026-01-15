package com.ks12.better_deep_dark.common.blocks;

import com.ks12.better_deep_dark.registry.ModBlocks;
import com.ks12.better_deep_dark.registry.ModSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkulkConduit extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    public static final int DELAY_TICKS = 2;
    public static final BooleanProperty SHOULD_ACTIVATE = BooleanProperty.of("should_be_active");

    public SkulkConduit() {
        super(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)
                .luminance((state) -> state.get(ACTIVE) ? 15 : 0));
        setDefaultState(getDefaultState().with(ACTIVE, false)
                .with(SHOULD_ACTIVATE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
        builder.add(SHOULD_ACTIVATE);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) return;

        boolean desired = shouldBeActive(world, pos);
        if (state.get(SHOULD_ACTIVATE) == desired) return;

        world.setBlockState(pos, state.with(SHOULD_ACTIVATE, desired), Block.NOTIFY_ALL);
        world.scheduleBlockTick(pos, this, DELAY_TICKS);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, state.with(ACTIVE, state.get(SHOULD_ACTIVATE)), Block.NOTIFY_ALL);
    }

    private static boolean shouldBeActive(World world, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockPos nPos = pos.offset(dir);
            BlockState ns = world.getBlockState(nPos);
            if (ns.isOf(ModBlocks.SKULK_CONDUIT) && ns.get(ACTIVE)) {
                return true;
            }
        }
        return false;
    }

    public void activateFirst(World world, BlockPos pos, boolean activate) {
        world.setBlockState(pos, world.getBlockState(pos).with(SHOULD_ACTIVATE, activate));
        if (!world.isClient) {
            if (activate && !world.getBlockState(pos).get(ACTIVE)) {
                world.playSound(null, pos, ModSounds.CONDUIT_ACTIVATE_SOUND, SoundCategory.BLOCKS, 0.5f, 1f);
                world.playSound(null, pos, ModSounds.CONDUIT_CRACK_SOUND, SoundCategory.BLOCKS, 1.5f, 1f);
            }
            world.scheduleBlockTick(pos, this, 0);
        }
    }

    public static Map<BlockPos, BlockState> collectAllInNetwork(World world, BlockPos source) {
        Map<BlockPos, BlockState> network = new HashMap<>();
        collectNetwork(world, source, network);
        return network;
    }

    private static void collectNetwork(World world, BlockPos pos, Map<BlockPos, BlockState> out) {
        if (out.size() >= 2048) return;
        BlockState state = world.getBlockState(pos);
        if (!state.isOf(ModBlocks.SKULK_CONDUIT)) return;
        if (out.putIfAbsent(pos, state) != null) return;

        for (Direction dir : Direction.values()) {
            collectNetwork(world, pos.offset(dir), out);
        }
    }
}
