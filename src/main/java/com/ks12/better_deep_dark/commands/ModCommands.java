package com.ks12.better_deep_dark.commands;

import com.ks12.better_deep_dark.blocks.ModBlocks;
import com.ks12.better_deep_dark.blocks.SkulkConduit;
import com.ks12.better_deep_dark.util.ModUtil;
import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ModCommands {

    public static final Command<ServerCommandSource> RESET_COMMAND = context -> {
        final ServerCommandSource source = context.getSource();

        final @Nullable Entity sender = source.getEntity();
        if (sender != null) {
            final @Nullable ServerPlayerEntity player = source.getPlayer();
            if (player != null) {
                BlockHitResult rayCastResult = ModUtil.rayCastBlock(player, 4.5f);
                if (rayCastResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockPos = rayCastResult.getBlockPos();
                    BlockState hitBlock = player.getWorld().getBlockState(blockPos);
                    if (hitBlock.isOf(ModBlocks.SKULK_CONDUIT)) {
                        SkulkConduit conduitBlock = (SkulkConduit) hitBlock.getBlock();
                        if (hitBlock.get(SkulkConduit.ACTIVE)) {
                            conduitBlock.activateFirst(player.getWorld(), blockPos, false);
                            return 1;
                        }
                    }
                    else source.sendError(Text.of("You must be looking at a skulk conduit block to run that command"));
                }
            }
            else source.sendError(Text.of("You must be a player to run that command"));
        }
        return -1;
    };

    public static void registerAll() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("resetconduit")
                .executes(RESET_COMMAND)));
    }
}
