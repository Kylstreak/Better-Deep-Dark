package com.ks12.better_deep_dark.blocks;

import com.ks12.better_deep_dark.sounds.ModSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SkulkConduit extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    public static final int DELAY_TICKS = 2;
    public boolean shouldBeActive = false;

    public SkulkConduit() {
        super(FabricBlockSettings.copyOf(Blocks.STONE)
                .luminance((state)-> state.get(ACTIVE) ? 15 : 0));
        setDefaultState(getDefaultState().with(ACTIVE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (state.isOf(ModBlocks.SKULK_CONDUIT)) {
            if (!world.isClient) {
                world.scheduleBlockTick(pos, this, DELAY_TICKS);
                shouldBeActive = world.getBlockState(sourcePos).get(ACTIVE);
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldBeActive) {
            world.setBlockState(pos, state.with(ACTIVE, true));
        }
        else {
            world.setBlockState(pos, state.with(ACTIVE, false));
        }
    }

    public void activateFirst(World world, BlockPos pos, boolean activate) {
        shouldBeActive = activate;
        if (!world.isClient) {
           if (activate && !world.getBlockState(pos).get(ACTIVE)) {
               world.playSound(null, pos, ModSounds.CONDUIT_ACTIVATE_SOUND, SoundCategory.BLOCKS, 0.5f, 1f);
               world.playSound(null, pos, ModSounds.CONDUIT_CRACK_SOUND, SoundCategory.BLOCKS, 1.5f, 1f);
           }
            world.scheduleBlockTick(pos, this, 0);
        }
    }
}
