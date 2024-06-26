package com.ks12.better_deep_dark.blocks;

import com.ks12.better_deep_dark.BetterDeepDark;
import com.ks12.better_deep_dark.registry.ModBlocks;
import com.ks12.better_deep_dark.registry.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class KeyAlter extends Block {
    public KeyAlter() {
        super(FabricBlockSettings.create()
                .nonOpaque()
                .strength(-1.0f, 3600000.0f)
                .dropsNothing()
                .allowsSpawning(Blocks::never)
                .luminance(10));
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity itemEntity){
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
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape obj1 = VoxelShapes.cuboid(2/16f, 0f, 2/16f, 14/16f, 0.25/16f, 14/16f);
        VoxelShape obj2 = VoxelShapes.cuboid(11/16f, 14/16f, 4/16f, 12/16f, 16/16f, 7/16f);
        VoxelShape obj3 = VoxelShapes.cuboid(9/16f, 14/16f, 4/16f, 12/16f, 16/16f, 5/16f);
        VoxelShape obj4 = VoxelShapes.cuboid(3.5/16f, 0.25/16f, 3.5/16f, 12.5/16f, 0.5/16f, 12.5/16f);
        VoxelShape obj5 = VoxelShapes.cuboid(5/16f, 0.5/16f, 5/16f, 11/16f, 13.5/16f, 11/16f);
        VoxelShape obj6 = VoxelShapes.cuboid(3.25/16f, 13.5/16f, 3.25/16f, 12.75/16f, 14/16f, 12.75/16f);
        VoxelShape obj7 = VoxelShapes.cuboid(4/16f, 14/16f, 4/16f, 7/16f, 16/16f, 5/16f);
        VoxelShape obj8 = VoxelShapes.cuboid(4/16f, 14/16f, 9/16f, 5/16f, 16/16f, 12/16f);
        VoxelShape obj9 = VoxelShapes.cuboid(4/16f, 14/16f, 4/16f, 5/16f, 16/16f, 7/16f);
        VoxelShape obj10 = VoxelShapes.cuboid(4/16f, 14/16f, 11/16f, 7/16f, 16/16f, 12/16f);
        VoxelShape obj11 = VoxelShapes.cuboid(9/16f, 14/16f, 11/16f, 12/16f, 16/16f, 12/16f);
        VoxelShape obj12 = VoxelShapes.cuboid(11/16f, 14/16f, 9/16f, 12/16f, 16/16f, 12/16f);
        return VoxelShapes.union(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12);
    }
}
