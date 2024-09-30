package com.headspin.skillbase.image.infrastructure.registry;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.registry.CommonRegistryProviderConsul;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import lombok.extern.slf4j.Slf4j;

/**
 * Consul implementation of the common registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class ImageRegistryProviderConsul extends CommonRegistryProviderConsul {
    public ImageRegistryProviderConsul(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.consul.endpoints") final String configEndpoints,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.consul.username") final String configUsername,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.consul.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.consul.namespace") final String configNamespace) {
        super(configEndpoints, configUsername, configPassword, configNamespace);
    }
}
