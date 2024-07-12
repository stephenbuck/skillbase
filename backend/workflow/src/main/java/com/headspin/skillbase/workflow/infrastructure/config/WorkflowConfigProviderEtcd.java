package com.headspin.skillbase.workflow.infrastructure.config;

import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkflowConfigProviderEtcd implements WorkflowConfigProvider {

    public WorkflowConfigProviderEtcd() {
    }
    
    @Override
    public void test() {
        log.info("test");
    }

    @Override
    public String getValue(String key) {
        return null;
    }
}
