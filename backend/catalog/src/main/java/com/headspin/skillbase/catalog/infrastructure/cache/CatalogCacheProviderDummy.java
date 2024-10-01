package com.headspin.skillbase.catalog.infrastructure.cache;

import com.headspin.skillbase.common.infrastructure.cache.CommonCacheProviderDummy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CatalogCacheProviderDummy extends CommonCacheProviderDummy {
    public CatalogCacheProviderDummy() {
    }
}
