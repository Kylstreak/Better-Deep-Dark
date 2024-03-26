package com.ks12.better_deep_dark;

import com.ks12.better_deep_dark.blocks.ModBlocks;
import com.ks12.better_deep_dark.commands.ModCommands;
import com.ks12.better_deep_dark.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BetterDeepDark implements ModInitializer {
    public static final String MOD_ID = "better_deep_dark";


    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
        ModBlocks.updateMappings();
        ModItems.registerAll();
        ModCommands.registerAll();
    }
}
