package com.headspin.skillbase.catalog.infrastructure.config;


import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.catalog.providers.CatalogConfigProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of the catalog config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogConfigProviderDefault implements CatalogConfigProvider {

    private final Config config;

    public CatalogConfigProviderDefault() {
        this.config = ConfigProvider.getConfig();
    }
    
    @Override
    public Optional<?> getOptionalValue(String key, Class<?> type) {
        return config.getOptionalValue(key, type);
    }

    @Override
    public void test() {
        log.info("test:");
        log.info("flipt url = {}", getOptionalValue("com.headspin.skillbase.catalog.flipt.url", String.class));
        log.info("kafka url = {}", getOptionalValue("com.headspin.skillbase.catalog.kafka.url", String.class));
    }
}
