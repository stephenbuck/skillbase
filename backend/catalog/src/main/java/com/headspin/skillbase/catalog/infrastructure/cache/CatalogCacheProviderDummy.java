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
    public String get(String key) throws Exception {
        return null;
    }

    @Override
    public boolean set(String key, String object) throws Exception {
        return false;
    }

    @Override
    public boolean touch(String key) throws Exception {
        return false;
    }

    @Override
    public boolean exists(String key) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return false;
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
