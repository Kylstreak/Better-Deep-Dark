package com.ks12.better_deep_dark.sounds;

import com.ks12.better_deep_dark.BetterDeepDark;
import net.minecraft.client.sound.SoundEngine;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final Identifier CONDUIT_CRACK_SOUND_ID = new Identifier(BetterDeepDark.MOD_ID, "skulk_conduit_crack");
    public static SoundEvent CONDUIT_CRACK_SOUND = SoundEvent.of(CONDUIT_CRACK_SOUND_ID);
    public static final Identifier CONDUIT_ACTIVATE_SOUND_ID = new Identifier(BetterDeepDark.MOD_ID, "skulk_conduit_activate");
    public static SoundEvent CONDUIT_ACTIVATE_SOUND = SoundEvent.of(CONDUIT_ACTIVATE_SOUND_ID);
    public static void registerAll() {
        Registry.register(Registries.SOUND_EVENT, CONDUIT_ACTIVATE_SOUND_ID, CONDUIT_ACTIVATE_SOUND);
        Registry.register(Registries.SOUND_EVENT, CONDUIT_CRACK_SOUND_ID, CONDUIT_CRACK_SOUND);
    }
}
