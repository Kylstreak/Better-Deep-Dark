package com.ks12.better_deep_dark.network;

import com.ks12.better_deep_dark.client.rendereres.shockwave.ClientShockWaves;
import com.ks12.better_deep_dark.client.rendereres.shockwave.ShockWaveInstance;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientRendererNetworking {

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.isPaused()) return;

            for (ShockWaveInstance s : ClientShockWaves.ACTIVE) {
                s.ageTicks++;
            }

            ClientShockWaves.ACTIVE.removeIf(ShockWaveInstance::done);
        });
    }
}
