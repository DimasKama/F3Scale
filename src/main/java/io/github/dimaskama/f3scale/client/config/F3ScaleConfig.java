package io.github.dimaskama.f3scale.client.config;

import org.jetbrains.annotations.Nullable;

public class F3ScaleConfig extends JsonConfig {
    public transient boolean dirty;

    public float scale = 1.0F;

    public F3ScaleConfig(String path, @Nullable String defaultPath) {
        super(path, defaultPath);
    }
}
