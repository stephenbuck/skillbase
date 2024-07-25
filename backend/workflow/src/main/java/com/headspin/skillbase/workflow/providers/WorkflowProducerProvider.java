package com.headspin.skillbase.workflow.providers;

import com.headspin.skillbase.common.events.WorkflowEvent;

/**
 * Workflow producer provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowProducerProvider {

    public void test();
    
    public void produce(WorkflowEvent event);
}
