package com.headspin.skillbase.workflow.providers;

public interface WorkflowFeatureProvider {

    public void test();

    public boolean evaluateBoolean(String key, boolean def);
}
