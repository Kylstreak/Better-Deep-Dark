package com.ks12.better_deep_dark.common.tools.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.HoeItem;

public class WardenHoe extends HoeItem {
    public WardenHoe() {
        super(WardenToolMaterial.INSTANCE, -6, 0, new FabricItemSettings());
    }
}
