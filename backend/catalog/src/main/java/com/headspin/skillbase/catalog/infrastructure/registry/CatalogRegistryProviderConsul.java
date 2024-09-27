package com.headspin.skillbase.catalog.infrastructure.registry;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.registry.CommonRegistryProviderConsul;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Consul implementation of the catalog registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogRegistryProviderConsul extends CommonRegistryProviderConsul {
    public CatalogRegistryProviderConsul(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.consul.endpoints") final String configEndpoints,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.consul.username") final String configUsername,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.consul.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.consul.namespace") final String configNamespace) {
        super(configEndpoints, configUsername, configPassword, configNamespace);
    }
}
