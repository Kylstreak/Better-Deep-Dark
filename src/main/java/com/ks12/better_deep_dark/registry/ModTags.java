package com.ks12.better_deep_dark.registry;

import com.ks12.better_deep_dark.BetterDeepDark;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Items {
        public static final TagKey<Item> SCULK_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(BetterDeepDark.MOD_ID, "sculk"));
    }
}
