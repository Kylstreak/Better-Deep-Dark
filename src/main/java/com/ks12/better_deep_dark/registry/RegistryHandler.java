package com.ks12.better_deep_dark.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public  class RegistryHandler {
    private static final List<RegistryEntryConstructor<?, ?>> registryEntries = new ArrayList<>();


    public static <V, T extends V> RegistryEntryConstructor<V, T> registryEntry(Registry<V> registry, String name, T entry) {
        RegistryEntryConstructor<V, T> rEntry = new RegistryEntryConstructor<>(name, registry, entry);
        registryEntries.add(rEntry);
        if (registry == Registries.BLOCK) {
            if (!(entry instanceof Block block)) {
                throw new IllegalStateException(
                        "Attempted to register non-Block into BLOCK registry:\n id: %s, entry class: %s".formatted(
                                name,
                                entry.getClass().getName()
                        )
                );
            }
            registryEntries.add(new RegistryEntryConstructor<>(name, Registries.ITEM, new BlockItem(block, new FabricItemSettings())));
        }
        return rEntry;
    }

    public static <V, T extends V> RegistryEntryConstructor<V, T> registryEntry(Registry<V> registry, Identifier id, T entry) {
        RegistryEntryConstructor<V, T> rEntry = new RegistryEntryConstructor<>(id, registry, entry);
        registryEntries.add(rEntry);
        if (registry == Registries.BLOCK) {
            if (!(entry instanceof Block block)) {
                throw new IllegalStateException(
                        "Attempted to register non-Block into BLOCK registry:\n id: %s, entry class: %s".formatted(
                                id,
                                entry.getClass().getName()
                        )
                );
            }
            registryEntries.add(new RegistryEntryConstructor<>(id, Registries.ITEM, new BlockItem(block, new FabricItemSettings())));
        }
        return rEntry;
    }

    //RegistryEntryConstructor.setRegistrationMethod MUST be called with this or registration will fail
    public static <V, T extends V> RegistryEntryConstructor<V, T> registryEntryBase(String name, T entry) {
        RegistryEntryConstructor<V, T> rEntry = new RegistryEntryConstructor<>(name, null, entry);
        registryEntries.add(rEntry);
        return rEntry;
    }


    public static void completeAllRegistrations() {
        registryEntries.forEach(RegistryEntryConstructor::completeRegistration);
    }

    public static void completeClientRegistrations() {
        registryEntries.forEach(RegistryEntryConstructor::completeClientRegistration);
    }

    public static void initCommon() {
        ModBlocks.load();
        ModItems.load();
        ModParticles.load();
        ModSounds.load();
        ServerCommands.load();
    }
}
