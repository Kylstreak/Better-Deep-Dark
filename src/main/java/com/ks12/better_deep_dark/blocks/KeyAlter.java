package com.ks12.better_deep_dark.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.ArrayVoxelShape;
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
                .allowsSpawning(Blocks::never));
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity){
            ItemEntity itemEntity = (ItemEntity) entity;
            if (itemEntity.getY() >= pos.getY() + 0.5) {
                //TODO: logic for wardens heart burned
                entity.damage(world.getDamageSources().inFire(), 1.0f);
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
