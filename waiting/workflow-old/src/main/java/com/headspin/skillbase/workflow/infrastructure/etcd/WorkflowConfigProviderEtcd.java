package com.headspin.groupbase.workflow.infrastructure.etcd;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import com.headspin.groupbase.workflow.providers.WorkflowConfigProvider;

@Slf4j
@ApplicationScoped
public class WorkflowConfigProviderEtcd implements WorkflowConfigProvider {

    public WorkflowConfigProviderEtcd() {
        log.info("etcd");
    }

    @Override
    public String getValue(String key) {
        return null;
    }
}
