package com.ks12.better_deep_dark.registry;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

public class ModClientModels {
    public static Identifier SKULK_PYLON_CRYSTAL_ID = new Identifier("better_deep_dark", "block/skulk_pylon_crystal");

    public static void register() {
        ModelLoadingPlugin.register(ctx -> {
            ctx.addModels(SKULK_PYLON_CRYSTAL_ID);
        });
    }
}
