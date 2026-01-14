package com.ks12.better_deep_dark.registry;

import com.ks12.better_deep_dark.common.blocks.blockenitities.KeyAlterBlockEntity;
import com.ks12.better_deep_dark.client.rendereres.blockentityrenderers.KeyAlterBlockEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;

public class ModBlockEntities {
    public static final BlockEntityType<KeyAlterBlockEntity> KEY_ALTER_BLOCK_ENTITY = RegistryHandler.registryEntry(Registries.BLOCK_ENTITY_TYPE, "key_alter", FabricBlockEntityTypeBuilder.create(
            KeyAlterBlockEntity::new,
            ModBlocks.KEY_ALTER
    ).build()
    ).addClientRegistrationParameter(rec ->
            BlockEntityRendererRegistry.register(
                    rec.entry,
                    KeyAlterBlockEntityRenderer::new
            )
            ).build();

    public static void load() {}
}
