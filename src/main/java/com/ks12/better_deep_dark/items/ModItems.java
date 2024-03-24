package com.ks12.better_deep_dark.items;

import com.ks12.better_deep_dark.BetterDeepDark;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item WARDEN_HEART = new Item(new FabricItemSettings());
    public static void registerAll() {
        Registry.register(Registries.ITEM, new Identifier(BetterDeepDark.MOD_ID, "warden_heart"), WARDEN_HEART);
    }
}
