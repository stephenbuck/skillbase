package com.headspin.skillbase.catalog.infrastructure.features;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.features.CommonFeaturesProviderFlipt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Flipt implementation of catalog features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogFeaturesProviderFlipt extends CommonFeaturesProviderFlipt {

    @Inject
    public CatalogFeaturesProviderFlipt(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.features.flipt.url") final String configUrl,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.features.flipt.namespace") final String configNamespace) {
        super(configUrl, configNamespace);
    }
}
