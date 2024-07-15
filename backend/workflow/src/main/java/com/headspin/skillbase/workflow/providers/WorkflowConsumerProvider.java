package com.headspin.skillbase.workflow.providers;

import jakarta.transaction.Transactional;

/**
 * Workflow consumer provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowConsumerProvider {

    public void test();
    
    @Transactional
    public void consume();
}
