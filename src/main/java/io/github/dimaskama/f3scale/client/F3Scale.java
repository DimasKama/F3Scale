package io.github.dimaskama.f3scale.client;

import io.github.dimaskama.f3scale.client.config.F3ScaleConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class F3Scale implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("F3Scale");
    public static final F3ScaleConfig CONFIG = new F3ScaleConfig("config/f3scale.json", null);

    @Override
    public void onInitializeClient() {
        CONFIG.loadOrCreate();
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            if (CONFIG.dirty) CONFIG.save(false);
        });
    }
}
