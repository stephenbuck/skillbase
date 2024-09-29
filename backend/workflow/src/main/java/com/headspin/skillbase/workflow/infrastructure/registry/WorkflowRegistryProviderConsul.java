package com.headspin.skillbase.workflow.infrastructure.registry;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.registry.CommonRegistryProviderConsul;
import com.headspin.skillbase.common.infrastructure.registry.CommonRegistryProviderEtcd;

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
public class WorkflowRegistryProviderConsul extends CommonRegistryProviderConsul {
    public WorkflowRegistryProviderConsul(
            @ConfigProperty(name = "com.headspin.skillbase.workflow.registry.consul.endpoints") final String configEndpoints,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.registry.consul.username") final String configUsername,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.registry.consul.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.workflow.registry.consul.namespace") final String configNamespace) {
        super(configEndpoints, configUsername, configPassword, configNamespace);
    }
}
