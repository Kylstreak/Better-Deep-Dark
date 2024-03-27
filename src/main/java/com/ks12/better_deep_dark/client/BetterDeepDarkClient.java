package com.ks12.better_deep_dark.client;

import com.ks12.better_deep_dark.particles.ModParticle;
import net.fabricmc.api.ClientModInitializer;

public class BetterDeepDarkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModParticle.registerClient();
    }
}
