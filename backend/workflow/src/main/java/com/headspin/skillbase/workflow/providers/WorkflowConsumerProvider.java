package com.headspin.skillbase.workflow.providers;

import jakarta.transaction.Transactional;

public interface WorkflowConsumerProvider {

    public void test();
    
    @Transactional
    public void consume();
}
