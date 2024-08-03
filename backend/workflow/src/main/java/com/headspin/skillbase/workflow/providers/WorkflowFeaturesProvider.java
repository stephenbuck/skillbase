package com.headspin.skillbase.workflow.providers;

/**
 * Workflow features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

 public interface WorkflowFeaturesProvider {

    public boolean evaluateBoolean(String key, boolean def);

    public void test();

}
