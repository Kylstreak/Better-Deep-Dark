package com.ks12.better_deep_dark.player;

import com.ks12.better_deep_dark.BetterDeepDark;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PlayerAttributes {
    public static final EntityAttribute DAMAGE_DEALT_WITH_WARDEN_AXE = new ClampedEntityAttribute("attribute.name.generic.damage_dealt_with_warden_axe", 0, 0, 12);

    public static void registerAll() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(BetterDeepDark.MOD_ID, "generic.damage_dealt_with_warden_axe"), DAMAGE_DEALT_WITH_WARDEN_AXE);
    }

}
