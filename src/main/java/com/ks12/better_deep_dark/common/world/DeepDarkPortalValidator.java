package com.ks12.better_deep_dark.common.world;

import com.ks12.better_deep_dark.common.blocks.blockenitities.SkulkPylonBlockEntity;
import com.ks12.better_deep_dark.registry.ModBlocks;
import com.ks12.better_deep_dark.util.Triplet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public class DeepDarkPortalValidator {

    public static boolean createPortal(World world, BlockPos startingPos) {
        Direction.Axis plane = findPlaneAxis(world, startingPos);
        BlockPos bottomLeft = findBottomLeft(world, startingPos, plane);
        if (bottomLeft == null) return false;

        Triplet<Integer, Integer, Boolean> frame = validFrame(world, bottomLeft, plane);
        if (frame.third == false) return false;

        int width = frame.first;
        int height = frame.second;
        int intWidth = width - 2;
        int intHeight = height - 2;

        BlockPos intBottomLeft = bottomLeft.offset(Direction.from(plane, Direction.AxisDirection.NEGATIVE)).offset(Direction.UP);
        BlockPos.Mutable cursor = intBottomLeft.mutableCopy();

        for (int i = 0; i < intHeight; i++) {
            for (int j = 0; j < intWidth; j++) {
                cursor.set(intBottomLeft.getX(), cursor.getY(), intBottomLeft.getZ()).move(Direction.from(plane, Direction.AxisDirection.NEGATIVE), j);
                if (!world.getBlockState(cursor).isAir()) return false;
            }
            cursor.move(Direction.UP);
        }

        cursor.set(intBottomLeft);

        for (int i = 0; i < intHeight; i++) {
            for (int j = 0; j < intWidth; j++) {
                cursor.set(intBottomLeft.getX(), cursor.getY(), intBottomLeft.getZ()).move(Direction.from(plane, Direction.AxisDirection.NEGATIVE), j);
                world.setBlockState(cursor, Blocks.AMETHYST_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS); //TODO: placeholder block replace with portal block
            }
            cursor.move(Direction.UP);
        }
        //TODO: animation logic
        return true;

    }

    private static BlockPos findBottomLeft(World world, BlockPos startingPos,Direction.Axis planeAxis) {
        BlockPos.Mutable cursor = startingPos.mutableCopy();
        while (planeAxis == Direction.Axis.Y && world.getBlockState(cursor.down()).isOf(Blocks.REINFORCED_DEEPSLATE)) {
            cursor.move(Direction.DOWN);
            planeAxis = findPlaneAxis(world, cursor);
        }
        Direction scanDirection = Direction.from(planeAxis, Direction.AxisDirection.POSITIVE);
        int maxScan = 30;

        for (int i = 0; i < maxScan; i++) {
            BlockPos offset = cursor.offset(scanDirection);
            if (world.getBlockState(offset).isOf(Blocks.REINFORCED_DEEPSLATE)) cursor.set(offset);
            else return cursor.toImmutable();
        }
        return null;
    }


    private static Direction.Axis findPlaneAxis(World world, BlockPos testPos) {
        if (world.getBlockState(testPos.east()).isOf(Blocks.REINFORCED_DEEPSLATE) || world.getBlockState(testPos.west()).isOf(Blocks.REINFORCED_DEEPSLATE)) {
            return Direction.Axis.X;
        }

        else if (world.getBlockState(testPos.south()).isOf(Blocks.REINFORCED_DEEPSLATE) || world.getBlockState(testPos.north()).isOf(Blocks.REINFORCED_DEEPSLATE)) {
            return Direction.Axis.Z;
        }

        return Direction.Axis.Y;
    }

    // returns width height and weather or not the frame is valid
    private static Triplet<Integer, Integer, Boolean> validFrame(World world, BlockPos bottomLeft, Direction.Axis planeAxis) {
        Direction walkDir = Direction.from(planeAxis, Direction.AxisDirection.NEGATIVE);
        BlockPos.Mutable cursor = bottomLeft.mutableCopy();
        int maxScan = 50;
        int width = 1;
        int height = 1;
        for (int i = 0; i < maxScan; i++) {
            BlockPos offset = cursor.offset(walkDir);
            if (world.getBlockState(offset).isOf(Blocks.REINFORCED_DEEPSLATE)) {
                if (walkDir == Direction.from(planeAxis, Direction.AxisDirection.NEGATIVE)) width++;
                if (walkDir == Direction.UP) height++;
                if (offset.equals(bottomLeft))return new Triplet<>(width, height, true);

                cursor.set(offset);
            }
            else if (walkDir == Direction.from(planeAxis, Direction.AxisDirection.NEGATIVE)) walkDir = Direction.UP;
            else if (walkDir == Direction.UP) walkDir = Direction.from(planeAxis, Direction.AxisDirection.POSITIVE);
            else if (walkDir == Direction.from(planeAxis, Direction.AxisDirection.POSITIVE)) walkDir = Direction.DOWN;
        }
        return new Triplet<>(0, 0, false);
    }

    @Nullable
    public static BlockPos findFirst(World world, BlockPos pylon1, BlockPos pylon2) {
        Direction.Axis axis = axisBetween(pylon1, pylon2);
        if (axis == null) return null;
        Direction.Axis findOnAxis = rotate90(axis);
        Direction axisPos = Direction.from(findOnAxis, Direction.AxisDirection.POSITIVE);
        Direction axisNeg = Direction.from(findOnAxis, Direction.AxisDirection.NEGATIVE);
        Vec3d center = pylon1.toCenterPos().add(pylon2.toCenterPos()).multiply(0.5);
        BlockPos midpoint = BlockPos.ofFloored(center);

        int maxDistance = 30;
        int vertical = 20;

        int floor = midpoint.getY() - (vertical / 2);

        BlockPos.Mutable cursorPos = midpoint.mutableCopy();
        BlockPos.Mutable cursorNeg = midpoint.mutableCopy();

        for (int i = 1; i < maxDistance; i++) {
            cursorPos.set(midpoint.getX(), floor, midpoint.getZ());
            cursorNeg.set(midpoint.getX(), floor, midpoint.getZ());
            cursorPos.move(axisPos, i);
            cursorNeg.move(axisNeg, i);
            for (int j = 0; j <= vertical; j++) {
                if (world.getBlockState(cursorPos).isOf(Blocks.REINFORCED_DEEPSLATE)) return cursorPos.toImmutable();
                if (world.getBlockState(cursorNeg).isOf(Blocks.REINFORCED_DEEPSLATE)) return cursorNeg.toImmutable();
                cursorPos.move(Direction.UP);
                cursorNeg.move(Direction.UP);
            }
        }
        return null;
    }

    @Nullable
    public static BlockPos findSkulkPylon(World world, BlockPos pos, SkulkPylonBlockEntity be) {
        if (be.isActive() && !be.portalActivated) {

            int maxTravel = 15;
            Map<Direction, BlockPos.Mutable> directionMap = new EnumMap<>(Direction.class);
            for (Direction dir : Direction.Type.HORIZONTAL) {
                directionMap.put(dir, pos.mutableCopy());
            }

            for (int i = 0; i < maxTravel; i++) {
                for (Direction dir : directionMap.keySet()) {
                    BlockPos.Mutable pos1 = directionMap.get(dir);
                    pos1.move(dir);
                    if (world.getBlockState(pos1).isOf(ModBlocks.SKULK_PYLON)) {
                        return pos1.toImmutable();
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public static Direction.Axis axisBetween(BlockPos a, BlockPos b) {
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        int dz = a.getZ() - b.getZ();

        int nonZero = 0;
        if (dx != 0) nonZero++;
        if (dy != 0) nonZero++;
        if (dz != 0) nonZero++;

        // Must differ on exactly ONE axis
        if (nonZero != 1) return null;

        if (dx != 0) return Direction.Axis.X;
        if (dy != 0) return Direction.Axis.Y;
        return Direction.Axis.Z;
    }
    public static Direction.Axis rotate90(Direction.Axis axis) {
        return axis == Direction.Axis.X ? Direction.Axis.Z
                : axis == Direction.Axis.Z ? Direction.Axis.X
                : Direction.Axis.Y;
    }


}
