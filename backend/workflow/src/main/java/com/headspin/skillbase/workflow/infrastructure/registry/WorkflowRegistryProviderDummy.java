package com.headspin.skillbase.workflow.infrastructure.registry;

import com.headspin.skillbase.common.infrastructure.registry.CommonRegistryProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class WorkflowRegistryProviderDummy extends CommonRegistryProviderDummy {

    @Inject
    public WorkflowRegistryProviderDummy() {
    }
}
