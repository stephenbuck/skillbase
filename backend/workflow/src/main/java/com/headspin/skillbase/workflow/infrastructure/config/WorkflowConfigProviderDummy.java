package com.headspin.skillbase.workflow.infrastructure.config;

import com.headspin.skillbase.common.infrastructure.config.CommonConfigProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common configure provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class WorkflowConfigProviderDummy extends CommonConfigProviderDummy {

    @Inject
    public WorkflowConfigProviderDummy() {
    }
}
