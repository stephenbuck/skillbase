package com.headspin.skillbase.workflow.providers;

import com.headspin.skillbase.workflow.domain.WorkflowEvent;

import jakarta.transaction.Transactional;

public interface WorkflowProducerProvider {

    public void test();
    
    @Transactional
    public void produce(WorkflowEvent event);
}
