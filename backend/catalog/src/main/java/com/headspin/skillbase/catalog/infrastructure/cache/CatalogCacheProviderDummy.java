package com.headspin.skillbase.catalog.infrastructure.cache;

import com.headspin.skillbase.common.providers.CommonCacheProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the catalog cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogCacheProviderDummy implements CommonCacheProvider {

    public CatalogCacheProviderDummy() {
    }
    
    @Override
    public String get(final String key) throws Exception {
        return null;
    }

    @Override
    public boolean set(final String key, final String val) throws Exception {
        return false;
    }

    @Override
    public boolean touch(final String key) throws Exception {
        return false;
    }

    @Override
    public boolean exists(final String key) throws Exception {
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
