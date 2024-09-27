package com.headspin.skillbase.catalog.infrastructure.registry;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.registry.CommonRegistryProviderEureka;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Eureka implementation of the catalog registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogRegistryProviderEureka extends CommonRegistryProviderEureka {
    public CatalogRegistryProviderEureka(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.eureka.endpoints") final String configEndpoints,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.eureka.username") final String configUsername,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.eureka.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.eureka.namespace") final String configNamespace) {
        super(configEndpoints, configUsername, configPassword, configNamespace);
    }
}
