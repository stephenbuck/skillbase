package com.headspin.skillbase.workflow.providers;

/**
 * Workflow feature provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

 public interface WorkflowFeatureProvider {

    public void test();

    public boolean evaluateBoolean(String key, boolean def);
}
