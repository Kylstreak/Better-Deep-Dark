package com.ks12.better_deep_dark.common.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SkulkPylon extends Block {
    public SkulkPylon() {
        super(FabricBlockSettings.copyOf(Blocks.STONE).luminance(15));
    }

    public static final VoxelShape shape = VoxelShapes.union(
            VoxelShapes.cuboid(1 / 16f, 0f, 1 / 16f, 15 / 16f, 5 / 16f, 15 / 16f),
            VoxelShapes.cuboid(0f, 0f, 0f, 3 / 16f, 5 / 16f, 3 / 16f),
            VoxelShapes.cuboid(0f, 0f, 13 / 16f, 3 / 16f, 5 / 16f, 16 / 16f),
            VoxelShapes.cuboid(13 / 16f, 0f, 13 / 16f, 16 / 16f, 5 / 16f, 16 / 16f),
            VoxelShapes.cuboid(13 / 16f, 0f, 0f, 16 / 16f, 5 / 16f, 3 / 16f),
            VoxelShapes.cuboid(2 / 16f, 5 / 16f, 2 / 16f, 3 / 16f, 7 / 16f, 14 / 16f),
            VoxelShapes.cuboid(13 / 16f, 5 / 16f, 2 / 16f, 14 / 16f, 7 / 16f, 14 / 16f),
            VoxelShapes.cuboid(3 / 16f, 5 / 16f, 2 / 16f, 13 / 16f, 7 / 16f, 3 / 16f),
            VoxelShapes.cuboid(3 / 16f, 5 / 16f, 13 / 16f, 13 / 16f, 7 / 16f, 14 / 16f),
            VoxelShapes.cuboid(4 / 16f, 5 / 16f, 4 / 16f, 12 / 16f, 11 / 16f, 12 / 16f),
            VoxelShapes.cuboid(4 / 16f, 10 / 16f, 3 / 16f, 12 / 16f, 12 / 16f, 5 / 16f),
            VoxelShapes.cuboid(4 / 16f, 10 / 16f, 11 / 16f, 12 / 16f, 12 / 16f, 13 / 16f),
            VoxelShapes.cuboid(3 / 16f, 10 / 16f, 3 / 16f, 5 / 16f, 12 / 16f, 13 / 16f),
            VoxelShapes.cuboid(11 / 16f, 10 / 16f, 3 / 16f, 13 / 16f, 12 / 16f, 13 / 16f)
    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }
}
