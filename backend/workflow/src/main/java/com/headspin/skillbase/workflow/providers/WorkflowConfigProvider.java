package com.headspin.skillbase.workflow.providers;

/**
 * Workflow config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowConfigProvider {

    public void test();
    
    public String getValue(String key);

}
