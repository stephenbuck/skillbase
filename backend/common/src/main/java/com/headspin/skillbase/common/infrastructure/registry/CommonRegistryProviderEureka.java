package com.headspin.skillbase.common.infrastructure.registry;

import com.headspin.skillbase.common.providers.CommonRegistryProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * Eureka implementation of the common registry provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonRegistryProviderEureka implements CommonRegistryProvider {

    public CommonRegistryProviderEureka(
            final String configEndpoints,
            final String configUsername,
            final String configPassword,
            final String configNamespace) {
    }

    @Override
    public String lookup(final String key) throws Exception {
        return null;
    }

    @Override
    public boolean register(final String key, final String val) throws Exception {
        return false;
    }

    @Override
    public boolean delete(final String key) throws Exception {
        return false;
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
