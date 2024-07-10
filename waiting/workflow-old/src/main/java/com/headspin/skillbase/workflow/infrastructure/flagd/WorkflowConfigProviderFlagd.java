package com.headspin.groupbase.workflow.infrastructure.flagd;

import com.headspin.groupbase.workflow.providers.WorkflowFeatureProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class WorkflowConfigProviderFlagd implements WorkflowFeatureProvider {

    public WorkflowConfigProviderFlagd() {
        log.info("flagd");
    }

    @Override
    public String getValue(String key) {
        return null;
    }
}
