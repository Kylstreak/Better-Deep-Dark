package com.ks12.better_deep_dark.items;

import com.ks12.better_deep_dark.BetterDeepDark;
import com.ks12.better_deep_dark.blocks.ModBlocks;
import com.ks12.better_deep_dark.items.tools.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item WARDEN_HEART = new Item(new FabricItemSettings());
    public static final Item WARDEN_SWORD = new WardenSword();
    public static final Item WARDEN_AXE = new WardenAxe();
    public static final Item WARDEN_HOE = new WardenHoe();
    public static final Item WARDEN_PICKAXE = new WardenPickaxe();
    public static final Item WARDEN_SHOVEL = new WardenShovel();
    public static final ItemGroup BDD_ITEM_GROUP = FabricItemGroup.builder()
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
            }))
            .build();
    public static void registerAll() {
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "warden_heart"), WARDEN_HEART);
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "warden_sword"), WARDEN_SWORD);
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "warden_axe"), WARDEN_AXE);
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "warden_hoe"), WARDEN_HOE);
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "warden_pickaxe"), WARDEN_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "warden_shovel"), WARDEN_SHOVEL);

        Registry.register(Registries.ITEM_GROUP, new Identifier(BetterDeepDark.MOD_ID, "item_group"), BDD_ITEM_GROUP);
    }
}
