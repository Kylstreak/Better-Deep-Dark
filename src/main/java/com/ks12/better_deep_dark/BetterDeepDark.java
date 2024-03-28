package com.ks12.better_deep_dark;

import com.ks12.better_deep_dark.blocks.ModBlocks;
import com.ks12.better_deep_dark.commands.ModCommands;
import com.ks12.better_deep_dark.items.ModItems;
import com.ks12.better_deep_dark.particles.ModParticle;
import com.ks12.better_deep_dark.player.PlayerAttributes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.impl.lib.tinyremapper.extension.mixin.common.Logger;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class BetterDeepDark implements ModInitializer {
    public static final String MOD_ID = "better_deep_dark";


    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
        ModItems.registerAll();
        ModCommands.registerAll();
        ModParticle.registerAll();
        PlayerAttributes.registerAll();
    }
}
