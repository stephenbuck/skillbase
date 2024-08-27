package com.headspin.skillbase.image.infrastructure.config;


import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.common.providers.CommonConfigProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of the Image config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class ImageConfigProviderDefault implements CommonConfigProvider {

    private final Config config;

    public ImageConfigProviderDefault() {
        this.config = ConfigProvider.getConfig();
    }
    
    @Override
    public Optional<?> getOptionalValue(String key, Class<?> type) {
        return config.getOptionalValue(key, type);
    }

    @Override
    public void test() {
        log.info("test:");
        log.info("flipt url = {}", getOptionalValue("com.headspin.skillbase.storage.flipt.url", String.class));
        log.info("kafka url = {}", getOptionalValue("com.headspin.skillbase.storage.kafka.url", String.class));
    }
}
