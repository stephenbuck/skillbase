package com.headspin.groupbase.workflow.providers;

import jakarta.enterprise.context.ApplicationScoped;

public interface WorkflowFeatureProvider {

    public String getValue(String key);
}
