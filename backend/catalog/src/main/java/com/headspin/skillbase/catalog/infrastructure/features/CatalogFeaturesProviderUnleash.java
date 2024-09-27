package com.headspin.skillbase.catalog.infrastructure.features;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.features.CommonFeaturesProviderFlipt;
import com.headspin.skillbase.common.infrastructure.features.CommonFeaturesProviderUnleash;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Unleash implementation of catalog features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogFeaturesProviderUnleash extends CommonFeaturesProviderUnleash {

    @Inject
    public CatalogFeaturesProviderUnleash(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.features.unleash.appname") final String configAppName,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.features.unleash.instanceid") final String configInstanceId,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.features.unleash.unleashapi") final String configUnleashAPI,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.features.unleash.apikey") final String configAPIKey) {
        super(configAppName, configInstanceId, configUnleashAPI, configAPIKey);
    }
}
