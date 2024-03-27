package com.ks12.better_deep_dark.items.tools;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class WardenHoe extends HoeItem {
    public WardenHoe() {
        super(WardenToolMaterial.INSTANCE, -6, 0, new FabricItemSettings());
    }
}
