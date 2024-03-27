package com.ks12.better_deep_dark.items.tools;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class WardenPickaxe extends PickaxeItem {
    public WardenPickaxe() {
        super(WardenToolMaterial.INSTANCE, 1, -2.8f, new FabricItemSettings());
    }
}
