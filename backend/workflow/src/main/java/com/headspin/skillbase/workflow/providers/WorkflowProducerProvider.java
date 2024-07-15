package com.headspin.skillbase.workflow.providers;

import com.headspin.skillbase.workflow.domain.WorkflowEvent;

import jakarta.transaction.Transactional;

/**
 * Workflow producer provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowProducerProvider {

    public void test();
    
    @Transactional
    public void produce(WorkflowEvent event);
}
