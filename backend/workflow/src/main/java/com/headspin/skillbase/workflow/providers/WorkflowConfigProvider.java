package com.headspin.skillbase.workflow.providers;

import java.util.Optional;

/**
 * Workflow config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowConfigProvider {

    public Optional<?> getOptionalValue(String key, Class<?> type);

    public void test();

}
