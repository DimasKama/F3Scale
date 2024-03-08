package io.github.dimaskama.f3scale.client.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.dimaskama.f3scale.client.F3ScaleConfigScreen;

public class F3ScaleModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return F3ScaleConfigScreen::new;
    }
}
