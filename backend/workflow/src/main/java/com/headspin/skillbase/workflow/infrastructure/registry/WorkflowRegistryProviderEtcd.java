package com.headspin.skillbase.workflow.infrastructure.registry;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.registry.CommonRegistryProviderEtcd;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Etcd implementation of the workflow registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowRegistryProviderEtcd extends CommonRegistryProviderEtcd {
    public WorkflowRegistryProviderEtcd(
            @ConfigProperty(name = "com.headspin.skillbase.workflow.registry.etcd.endpoints") final String configEndpoints,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.registry.etcd.username") final String configUsername,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.registry.etcd.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.registry.etcd.namespace") final String configNamespace) {
        super(configEndpoints, configUsername, configPassword, configNamespace);
    }
}
