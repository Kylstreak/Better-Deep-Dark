package com.ks12.better_deep_dark.registry;

import com.ks12.better_deep_dark.BetterDeepDark;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModSounds {
    public static final Identifier CONDUIT_CRACK_SOUND_ID = new Identifier(BetterDeepDark.MOD_ID, "skulk_conduit_crack");
    public static SoundEvent CONDUIT_CRACK_SOUND = RegistryHandler.registryEntry(Registries.SOUND_EVENT, CONDUIT_CRACK_SOUND_ID, SoundEvent.of(CONDUIT_CRACK_SOUND_ID)).build();
    public static final Identifier CONDUIT_ACTIVATE_SOUND_ID = new Identifier(BetterDeepDark.MOD_ID, "skulk_conduit_activate");
    public static SoundEvent CONDUIT_ACTIVATE_SOUND = RegistryHandler.registryEntry(Registries.SOUND_EVENT, CONDUIT_ACTIVATE_SOUND_ID, SoundEvent.of(CONDUIT_ACTIVATE_SOUND_ID)).build();
    public static final Identifier SONIC_CHARGE_SOUND_ID = new Identifier(BetterDeepDark.MOD_ID, "sonic_charge");
    public static SoundEvent SONIC_CHARGE_SOUND = RegistryHandler.registryEntry(Registries.SOUND_EVENT, SONIC_CHARGE_SOUND_ID, SoundEvent.of(SONIC_CHARGE_SOUND_ID)).build();
    public static final Identifier SONIC_BLAST_SOUND_ID = new Identifier(BetterDeepDark.MOD_ID, "sonic_blast");
    public static SoundEvent SONIC_BLAST_SOUND = RegistryHandler.registryEntry(Registries.SOUND_EVENT, SONIC_BLAST_SOUND_ID, SoundEvent.of(SONIC_BLAST_SOUND_ID)).build();
    public static void load() {}

}
