package com.headspin.skillbase.common.infrastructure.features;

import com.headspin.skillbase.common.providers.CommonFeaturesProvider;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;

/**
 * Unleash implementation of common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonFeaturesProviderUnleash implements CommonFeaturesProvider {

    private final UnleashConfig config;
    private final Unleash unleash;

    @Inject
    public CommonFeaturesProviderUnleash(
            final String configAppName,
            final String configInstanceId,
            final String configUnleashAPI,
            final String configAPIKey) {
        this.config = UnleashConfig.builder()
                .appName(configAppName)
                .instanceId(configInstanceId)
                .unleashAPI(configUnleashAPI)
                .apiKey(configAPIKey)
                .synchronousFetchOnInitialisation(true)
                .build();
        this.unleash = new DefaultUnleash(config);
    }

    @Override
    public boolean evaluateBoolean(@NotNull final String flag, final boolean def) {
        return unleash.isEnabled(flag, def);
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
