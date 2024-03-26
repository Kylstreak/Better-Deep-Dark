package com.ks12.better_deep_dark.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class WardenSword extends SwordItem {
    public WardenSword() {
        super(WardenToolMaterial.INSTANCE, 3, -2.5f, new Item.Settings().fireproof());
    }
}
