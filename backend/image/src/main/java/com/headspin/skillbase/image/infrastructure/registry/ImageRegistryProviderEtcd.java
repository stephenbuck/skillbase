package com.headspin.skillbase.image.infrastructure.registry;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.registry.CommonRegistryProviderEtcd;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import lombok.extern.slf4j.Slf4j;

/**
 * Etcd implementation of the common registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class ImageRegistryProviderEtcd extends CommonRegistryProviderEtcd {
    public ImageRegistryProviderEtcd(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.etcd.endpoints") final String configEndpoints,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.etcd.username") final String configUsername,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.etcd.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.registry.etcd.namespace") final String configNamespace) {
        super(configEndpoints, configUsername, configPassword, configNamespace);
    }
}
