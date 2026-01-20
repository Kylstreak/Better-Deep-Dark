package com.ks12.better_deep_dark.common.blocks;

import com.ks12.better_deep_dark.common.blocks.blockenitities.SkulkPylonBlockEntity;
import com.ks12.better_deep_dark.common.world.DeepDarkPortalValidator;
import com.ks12.better_deep_dark.registry.ModBlockEntities;
import com.ks12.better_deep_dark.registry.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SkulkPylon extends Block implements BlockEntityProvider {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    public SkulkPylon() {
        super(FabricBlockSettings.copyOf(Blocks.STONE).luminance(15));
        setDefaultState(getDefaultState().with(ACTIVE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
        super.appendProperties(builder);
    }

    public static final VoxelShape COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1, 0, 1, 15, 5, 15),

            Block.createCuboidShape(0, 0, 0, 3, 5, 3),
            Block.createCuboidShape(0, 0, 13, 3, 5, 16),
            Block.createCuboidShape(13, 0, 13, 16, 5, 16),
            Block.createCuboidShape(13, 0, 0, 16, 5, 3),

            Block.createCuboidShape(2, 5, 2, 3, 7, 14),
            Block.createCuboidShape(13, 5, 2, 14, 7, 14),
            Block.createCuboidShape(3, 5, 2, 13, 7, 3),
            Block.createCuboidShape(3, 5, 13, 13, 7, 14),

            Block.createCuboidShape(4, 5, 4, 12, 11, 12),
            Block.createCuboidShape(4, 10, 3, 12, 12, 5),
            Block.createCuboidShape(4, 10, 11, 12, 12, 13),
            Block.createCuboidShape(3, 10, 3, 5, 12, 13),
            Block.createCuboidShape(11, 10, 3, 13, 12, 13)
    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SkulkPylonBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient && type == ModBlockEntities.SKULK_PYLON_BLOCK_ENTITY) {
            return (w, p, s, be) -> SkulkPylonBlockEntity.tick(w, p, s, (SkulkPylonBlockEntity) be);
        }
        return null;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient()) return;
        BlockState blockState = world.getBlockState(sourcePos);
        BlockState blockState1 = world.getBlockState(pos);
        if (blockState.isOf(ModBlocks.SKULK_CONDUIT) && blockState1.isOf(ModBlocks.SKULK_PYLON)) {
            if (!blockState1.get(ACTIVE) && blockState.get(SkulkPylon.ACTIVE)) {
                world.setBlockState(pos, blockState1.with(ACTIVE, true), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockEntity(pos) instanceof SkulkPylonBlockEntity be) {
            BlockPos pylon2 = DeepDarkPortalValidator.findSkulkPylon(world, pos, be);
            if (world.getBlockEntity(pylon2) instanceof SkulkPylonBlockEntity be2) {

            }
        }
    }
}
