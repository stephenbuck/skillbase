package com.headspin.skillbase.catalog.infrastructure.config;


import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.catalog.providers.CatalogConfigProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogConfigProviderDefault implements CatalogConfigProvider {

    private final Config config;

    public CatalogConfigProviderDefault() {
        this.config = ConfigProvider.getConfig();
    }
    
    @Override
    public void test() {
        log.info("foo = {}", getOptionalValue("com.headspin.skillbase.catalog.foo", String.class));
        log.info("flipt url = {}", getOptionalValue("com.headspin.skillbase.catalog.flipt.url", String.class));
        log.info("kafka url = {}", getOptionalValue("com.headspin.skillbase.catalog.kafka.url", String.class));
    }

    @Override
    public Optional<?> getOptionalValue(String key, Class<?> type) {
        return config.getOptionalValue(key, type);
    }
}
