package com.ks12.better_deep_dark.common.items.tools;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ShovelItem;

public class WardenShovel extends ShovelItem {
    public WardenShovel() {
        super(WardenToolMaterial.INSTANCE, 1.5f, -3.0f, new FabricItemSettings());
    }
}
