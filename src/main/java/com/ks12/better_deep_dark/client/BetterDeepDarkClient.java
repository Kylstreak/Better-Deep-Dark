package com.ks12.better_deep_dark.client;

import com.ks12.better_deep_dark.client.rendereres.shockwave.ClientShockWaves;
import com.ks12.better_deep_dark.client.rendereres.shockwave.ShockWaveRenderer;
import com.ks12.better_deep_dark.network.ClientRaycastEntity;
import com.ks12.better_deep_dark.registry.RegistryHandler;
import net.fabricmc.api.ClientModInitializer;

public class BetterDeepDarkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RegistryHandler.completeClientRegistrations();
        ClientRaycastEntity.registerListener();
        ShockWaveRenderer.registerClientHooks();
    }
}
