package com.headspin.skillbase.workflow.providers;

import jakarta.transaction.Transactional;

public interface WorkflowConsumerProvider {

    @Transactional
    public void consume();
}
