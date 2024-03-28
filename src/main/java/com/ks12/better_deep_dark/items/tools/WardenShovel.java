package com.ks12.better_deep_dark.items.tools;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class WardenShovel extends ShovelItem {
    public WardenShovel() {
        super(WardenToolMaterial.INSTANCE, 1.5f, -3.0f, new FabricItemSettings());
    }
}
