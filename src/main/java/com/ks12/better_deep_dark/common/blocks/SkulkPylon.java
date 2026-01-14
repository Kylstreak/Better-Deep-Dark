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
        super(FabricBlockSettings.copyOf(Blocks.STONE));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape obj1 = VoxelShapes.cuboid(0f, 0f, 0f, 16/16f, 1.5/16f, 16/16f);
        VoxelShape obj2 = VoxelShapes.cuboid(0f, 31/16f, 0f, 16/16f, 32/16f, 16/16f);
        VoxelShape obj3 = VoxelShapes.cuboid(3.25/16f, 1/16f, 3.25/16f, 13/16f, 31/16f, 13/16f);
        return VoxelShapes.union(obj1, obj2, obj3);
    }
}
