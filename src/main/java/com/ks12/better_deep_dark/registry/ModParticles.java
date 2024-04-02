package com.ks12.better_deep_dark.registry;

import com.ks12.better_deep_dark.BetterDeepDark;
import com.ks12.better_deep_dark.particles.WardenAxeSweepParticle;
import com.ks12.better_deep_dark.particles.WardenSwordSweepParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModParticles {
    public static final DefaultParticleType WARDEN_SWORD_SWEEP = RegistryHandler.registryEntry(Registries.PARTICLE_TYPE, "warden_sword_sweep", FabricParticleTypes.simple())
            .addClientRegistrationParameter(constructor-> ParticleFactoryRegistry.getInstance().register(constructor.entry, WardenSwordSweepParticle.Factory::new))
            .build();
    public static final DefaultParticleType WARDEN_AXE_SWEEP = RegistryHandler.registryEntry(Registries.PARTICLE_TYPE, "warden_axe_sweep", FabricParticleTypes.simple())
            .addClientRegistrationParameter(constructor-> ParticleFactoryRegistry.getInstance().register(constructor.entry, WardenAxeSweepParticle.Factory::new))
            .build();
    public static void load() {}

}