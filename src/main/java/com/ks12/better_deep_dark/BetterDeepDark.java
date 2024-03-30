package com.ks12.better_deep_dark;

import com.ks12.better_deep_dark.blocks.ModBlocks;
import com.ks12.better_deep_dark.commands.ModCommands;
import com.ks12.better_deep_dark.items.ModItems;
import com.ks12.better_deep_dark.particles.ModParticle;
import net.fabricmc.api.ModInitializer;

public class BetterDeepDark implements ModInitializer {
    public static final String MOD_ID = "better_deep_dark";


    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
        ModItems.registerAll();
        ModCommands.registerAll();
        ModParticle.registerAll();
    }
}
