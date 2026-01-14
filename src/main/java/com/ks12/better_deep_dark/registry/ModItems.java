package com.ks12.better_deep_dark.registry;

import com.ks12.better_deep_dark.common.tools.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

public class ModItems {
    public static final Item WARDEN_HEART = RegistryHandler.registryEntry(Registries.ITEM, "warden_heart", new Item(new FabricItemSettings())).build();
    public static final Item WARDEN_SWORD = RegistryHandler.registryEntry(Registries.ITEM, "warden_sword", new WardenSword()).build();
    public static final Item WARDEN_AXE = RegistryHandler.registryEntry(Registries.ITEM, "warden_axe", new WardenAxe()).build();
    public static final Item WARDEN_HOE = RegistryHandler.registryEntry(Registries.ITEM, "warden_hoe", new WardenHoe()).build();
    public static final Item WARDEN_PICKAXE = RegistryHandler.registryEntry(Registries.ITEM, "warden_pickaxe", new WardenPickaxe()).build();
    public static final Item WARDEN_SHOVEL = RegistryHandler.registryEntry(Registries.ITEM, "warden_shovel", new WardenShovel()).build();
    public static final ItemGroup ITEM_GROUP = RegistryHandler.registryEntry(Registries.ITEM_GROUP, "item_group", FabricItemGroup.builder()
            .icon(()-> new ItemStack(WARDEN_AXE))
            .displayName(Text.translatable("itemGroup.better_deep_dark"))
            .entries(((displayContext, entries) -> {
                entries.add(WARDEN_HEART);
                entries.add(WARDEN_SWORD);
                entries.add(WARDEN_AXE);
                entries.add(WARDEN_HOE);
                entries.add(WARDEN_PICKAXE);
                entries.add(WARDEN_SHOVEL);
                entries.add(ModBlocks.KEY_ALTER);
                entries.add(ModBlocks.SKULK_CONDUIT);
                entries.add(ModBlocks.SKULK_PYLON);
            })).build())
            .build();

    public static void load() {}
}