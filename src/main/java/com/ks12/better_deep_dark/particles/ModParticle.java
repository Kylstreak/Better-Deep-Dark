package com.ks12.better_deep_dark.particles;

import com.ks12.better_deep_dark.BetterDeepDark;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticle {
    public static final DefaultParticleType WARDEN_SWORD_SWEEP = FabricParticleTypes.simple();
    public static final DefaultParticleType WARDEN_AXE_SWEEP = FabricParticleTypes.simple();
    public static void registerAll() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(BetterDeepDark.MOD_ID, "warden_sword_sweep"), WARDEN_SWORD_SWEEP);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(BetterDeepDark.MOD_ID, "warden_axe_sweep"), WARDEN_AXE_SWEEP);
    }

    public static void registerClient() {
        ParticleFactoryRegistry.getInstance().register(WARDEN_SWORD_SWEEP, WardenSwordSweepParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(WARDEN_AXE_SWEEP, WardenAxeSweepParticle.Factory::new);
    }
}
