package com.ks12.better_deep_dark.registry;

import com.ks12.better_deep_dark.common.blocks.KeyAlter;
import com.ks12.better_deep_dark.common.blocks.SkulkConduit;
import com.ks12.better_deep_dark.common.blocks.SkulkPylon;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registries;

public class ModBlocks {
    public static final Block KEY_ALTER = RegistryHandler.registryEntry(Registries.BLOCK, "key_alter", new KeyAlter())
            .addClientRegistrationParameter(constructor-> BlockRenderLayerMap.INSTANCE.putBlock(constructor.entry, RenderLayer.getCutout()))
            .build();
    public static final Block SKULK_CONDUIT = RegistryHandler.registryEntry(Registries.BLOCK, "skulk_conduit", new SkulkConduit()).build();
    public static final Block SKULK_PYLON = RegistryHandler.registryEntry(Registries.BLOCK, "skulk_pylon", new SkulkPylon()).addClientRegistrationParameter(constructor -> {
        BlockRenderLayerMap.INSTANCE.putBlock(constructor.entry, RenderLayer.getTranslucent());
    }).build();

    public static void load() {}
}