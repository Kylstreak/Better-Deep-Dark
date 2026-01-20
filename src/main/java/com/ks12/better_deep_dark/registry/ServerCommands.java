package com.ks12.better_deep_dark.registry;

import com.ks12.better_deep_dark.common.blocks.SkulkConduit;
import com.ks12.better_deep_dark.util.ModUtil;
import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ServerCommands {

    public static final Command<ServerCommandSource> RESET_COMMAND = RegistryHandler.registryEntryBase("resetconduit", (Command<ServerCommandSource>) context -> {
        final ServerCommandSource source = context.getSource();

        final @Nullable Entity sender = source.getEntity();
        if (sender != null) {
            final @Nullable ServerPlayerEntity player = source.getPlayer();
            if (player != null) {
                BlockHitResult rayCastResult = ModUtil.rayCastBlock(player, 4.5f);
                if (rayCastResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockPos = rayCastResult.getBlockPos();
                    World world = player.getWorld();
                    BlockState hitBlock = world.getBlockState(blockPos);
                    if (hitBlock.isOf(ModBlocks.SKULK_CONDUIT)) {
                        if (hitBlock.get(SkulkConduit.ACTIVE)) {
                            for (BlockPos p : SkulkConduit.collectAllInNetwork(world, blockPos).keySet()) {
                                BlockState s = world.getBlockState(p);
                                world.setBlockState(
                                        p,
                                        s.with(SkulkConduit.ACTIVE, false).with(SkulkConduit.SHOULD_ACTIVATE, false),
                                        Block.NOTIFY_LISTENERS
                                );
                            }
                        }
                    }
                    else source.sendError(Text.of("You must be looking at a skulk conduit block to run that command"));
                }
            }
            else source.sendError(Text.of("You must be a player to run that command"));
        }
        return -1;
    })
            .setRegistrationMethod(constructor-> CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal(constructor.name).executes(constructor.entry))))
            .build();

//    public static void registerAll() {
//        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("resetconduit")
//                .executes(RESET_COMMAND)));
//    }

    public static void load() {}
}
