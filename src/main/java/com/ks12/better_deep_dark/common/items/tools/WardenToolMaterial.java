package com.ks12.better_deep_dark.common.items.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class WardenToolMaterial implements ToolMaterial {
    public static final WardenToolMaterial INSTANCE = new WardenToolMaterial();
    @Override
    public int getDurability() {
        return 4062;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10.0f;
    }

    @Override
    public float getAttackDamage() {
        return 6.0f;
    }

    @Override
    public int getMiningLevel() {
        return 5;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        //TODO: Don't forget to update this cuz ik you will dumbass
        return null;
    }
}
