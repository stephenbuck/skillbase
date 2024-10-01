package com.headspin.skillbase.image.infrastructure.features;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.features.CommonFeaturesProviderUnleash;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Unleash implementation of the common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class ImageFeaturesProviderUnleash extends CommonFeaturesProviderUnleash {

    @Inject
    public ImageFeaturesProviderUnleash(
            @ConfigProperty(name = "com.headspin.skillbase.image.features.unleash.appname") final String configAppName,
            @ConfigProperty(name = "com.headspin.skillbase.image.features.unleash.instanceid") final String configInstanceId,
            @ConfigProperty(name = "com.headspin.skillbase.image.features.unleash.unleashapi") final String configUnleashAPI,
            @ConfigProperty(name = "com.headspin.skillbase.image.features.unleash.apikey") final String configAPIKey) {
        super(configAppName, configInstanceId, configUnleashAPI, configAPIKey);
    }
}
