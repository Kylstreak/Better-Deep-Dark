package com.ks12.better_deep_dark.common.tools.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.PickaxeItem;

public class WardenPickaxe extends PickaxeItem {
    public WardenPickaxe() {
        super(WardenToolMaterial.INSTANCE, 1, -2.8f, new FabricItemSettings());
    }
}
