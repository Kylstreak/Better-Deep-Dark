package com.ks12.better_deep_dark.blocks;

import com.ks12.better_deep_dark.BetterDeepDark;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block KEY_ALTER = new KeyAlter();
    public static final Block UNNAMED = new Unnamed();

    public static void registerAll()  {
        Registry.register(Registries.BLOCK, new Identifier(BetterDeepDark.MOD_ID, "key_alter"), KEY_ALTER);
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "key_alter"), new BlockItem(KEY_ALTER, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, new Identifier(BetterDeepDark.MOD_ID, "unnamed"), UNNAMED);
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "unnamed"), new BlockItem(UNNAMED, new FabricItemSettings()));
    }

    public static void updateMappings() {
        BlockRenderLayerMap.INSTANCE.putBlock(KEY_ALTER, RenderLayer.getCutout());
    }
}
