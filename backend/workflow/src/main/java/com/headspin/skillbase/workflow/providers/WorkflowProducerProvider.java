package com.headspin.skillbase.workflow.providers;

import com.headspin.skillbase.workflow.domain.WorkflowEvent;

import jakarta.transaction.Transactional;

public interface WorkflowProducerProvider {

    @Transactional
    public void produce(WorkflowEvent event);
}
