package com.headspin.skillbase.workflow.infrastructure.config;

import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of workflow configure provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class WorkflowConfigProviderDefault implements WorkflowConfigProvider {

    private final Config config;

    public WorkflowConfigProviderDefault() {
        this.config = ConfigProvider.getConfig();
    }
    
    @Override
    public void test() {
        log.info("foo = {}", getOptionalValue("com.headspin.skillbase.workflow.foo", String.class));
        log.info("flipt url = {}", getOptionalValue("com.headspin.skillbase.workflow.flipt.url", String.class));
        log.info("kafka url = {}", getOptionalValue("com.headspin.skillbase.workflow.kafka.url", String.class));
    }

    @Override
    public Optional<?> getOptionalValue(String key, Class<?> type) {
        return config.getOptionalValue(key, type);
    }
}
