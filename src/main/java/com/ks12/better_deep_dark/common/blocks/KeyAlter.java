package com.ks12.better_deep_dark.common.blocks;

import com.ks12.better_deep_dark.BetterDeepDark;
import com.ks12.better_deep_dark.common.blocks.blockenitities.KeyAlterBlockEntity;
import com.ks12.better_deep_dark.registry.ModBlocks;
import com.ks12.better_deep_dark.registry.ModItems;
import com.ks12.better_deep_dark.registry.ModTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class KeyAlter extends Block implements BlockEntityProvider {


    public static final int NON_SKULK_HOLD_TICKS = 20 * 3; // 3 seconds
    public static final int SKULK_HOLD_TICKS = 20 * 5; // 5 seconds

    public static final VoxelShape SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(2 / 16f, 0f, 2 / 16f, 14 / 16f, 0.25 / 16f, 14 / 16f),
            VoxelShapes.cuboid(11 / 16f, 14 / 16f, 4 / 16f, 12 / 16f, 16 / 16f, 7 / 16f),
            VoxelShapes.cuboid(9 / 16f, 14 / 16f, 4 / 16f, 12 / 16f, 16 / 16f, 5 / 16f),
            VoxelShapes.cuboid(3.5 / 16f, 0.25 / 16f, 3.5 / 16f, 12.5 / 16f, 0.5 / 16f, 12.5 / 16f),
            VoxelShapes.cuboid(5 / 16f, 0.5 / 16f, 5 / 16f, 11 / 16f, 13.5 / 16f, 11 / 16f),
            VoxelShapes.cuboid(3.25 / 16f, 13.5 / 16f, 3.25 / 16f, 12.75 / 16f, 14 / 16f, 12.75 / 16f),
            VoxelShapes.cuboid(4 / 16f, 14 / 16f, 4 / 16f, 7 / 16f, 16 / 16f, 5 / 16f),
            VoxelShapes.cuboid(4 / 16f, 14 / 16f, 9 / 16f, 5 / 16f, 16 / 16f, 12 / 16f),
            VoxelShapes.cuboid(4 / 16f, 14 / 16f, 4 / 16f, 5 / 16f, 16 / 16f, 7 / 16f),
            VoxelShapes.cuboid(4 / 16f, 14 / 16f, 11 / 16f, 7 / 16f, 16 / 16f, 12 / 16f),
            VoxelShapes.cuboid(9 / 16f, 14 / 16f, 11 / 16f, 12 / 16f, 16 / 16f, 12 / 16f),
            VoxelShapes.cuboid(11 / 16f, 14 / 16f, 9 / 16f, 12 / 16f, 16 / 16f, 12 / 16f)
    );

    public KeyAlter() {
        super(FabricBlockSettings.create()
                .nonOpaque()
                .strength(-1.0f, 3600000.0f)
                .dropsNothing()
                .allowsSpawning(Blocks::never)
                .luminance(10));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof KeyAlterBlockEntity alter)) return ActionResult.PASS;

        ItemStack held = player.getStackInHand(hand);

        if (alter.getStack().isEmpty() && !held.isEmpty()) {
            ItemStack stack = held.split(1);
            alter.setStack(stack);
            BetterDeepDark.LOGGER.warn(stack.isIn(ModTags.Items.SCULK_ITEMS));
            world.scheduleBlockTick(pos, this, stack.isIn(ModTags.Items.SCULK_ITEMS) ? SKULK_HOLD_TICKS : NON_SKULK_HOLD_TICKS);
            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof KeyAlterBlockEntity alter)) return;
        ItemStack stack = alter.getStack();
        if (world instanceof ServerWorld && !stack.isEmpty()) {
            ItemStack stackCopy = alter.getStack().copy();
            alter.setStack(ItemStack.EMPTY);

            if (stackCopy.isOf(ModItems.WARDEN_HEART)) {
                BlockState belowState;
                BlockPos.Mutable mutable = pos.mutableCopy().move(Direction.DOWN);
                if ((belowState = world.getBlockState(mutable)).isOf(ModBlocks.SKULK_CONDUIT)) {
                    SkulkConduit skulkConduit = (SkulkConduit) belowState.getBlock();
                    skulkConduit.activateFirst(world, mutable, true);
                }
            }
            else {
                ItemEntity item = new ItemEntity(
                        world,
                        pos.getX() + 0.5,
                        pos.getY() + 1.0,
                        pos.getZ() + 0.5,
                        stackCopy
                );

                double xzSpeed = 0.01 + world.random.nextDouble() * 0.04;
                double xDir = world.random.nextBoolean() ? xzSpeed : -xzSpeed;
                double zDir = world.random.nextBoolean() ? xzSpeed : -xzSpeed;

                item.setVelocity(xDir, 0.5, zDir);

                world.spawnEntity(item);
            }
        }
    }

    // Kept here in case shit breaks
    /*@Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getY() >= pos.getY() + 0.7) {
                if (itemEntity.getStack().isOf(ModItems.WARDEN_HEART)) {
                    BlockState belowState;
                    BlockPos.Mutable mutable = pos.mutableCopy().move(Direction.DOWN);
                    if ((belowState = world.getBlockState(mutable)).isOf(ModBlocks.SKULK_CONDUIT)) {
                        SkulkConduit skulkConduit = (SkulkConduit) belowState.getBlock();
                        skulkConduit.activateFirst(world, mutable, true);
                    }
                }
                entity.kill();
            }
        }
    }*/

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KeyAlterBlockEntity(pos, state);
    }


}
