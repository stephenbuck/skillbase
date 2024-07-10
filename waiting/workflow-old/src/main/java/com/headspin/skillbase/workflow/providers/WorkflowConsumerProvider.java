package com.headspin.groupbase.workflow.providers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

public interface WorkflowConsumerProvider {

    @Transactional
    public void consume();
}
