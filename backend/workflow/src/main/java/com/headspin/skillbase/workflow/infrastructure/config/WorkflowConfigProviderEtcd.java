package com.headspin.skillbase.workflow.infrastructure.config;

import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * Etcd implementation of workflow configure provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

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
