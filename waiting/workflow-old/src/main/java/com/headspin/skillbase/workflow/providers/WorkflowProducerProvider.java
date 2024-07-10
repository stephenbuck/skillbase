package com.headspin.groupbase.workflow.providers;

import com.headspin.groupbase.workflow.domain.WorkflowEvent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

public interface WorkflowProducerProvider {

    @Transactional
    public void produce(WorkflowEvent event);
}
