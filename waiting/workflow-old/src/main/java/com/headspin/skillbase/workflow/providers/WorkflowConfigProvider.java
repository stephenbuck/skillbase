package com.headspin.groupbase.workflow.providers;

import jakarta.enterprise.context.ApplicationScoped;

public interface WorkflowConfigProvider {

    public String getValue(String key);
}
