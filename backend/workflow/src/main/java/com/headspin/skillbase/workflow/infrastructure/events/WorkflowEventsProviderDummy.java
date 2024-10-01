package com.headspin.skillbase.workflow.infrastructure.events;

import com.headspin.skillbase.common.infrastructure.events.CommonEventsProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common events provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class WorkflowEventsProviderDummy extends CommonEventsProviderDummy {

    @Inject
    public WorkflowEventsProviderDummy() {
    }
}
