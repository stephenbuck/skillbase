package com.headspin.skillbase.catalog.infrastructure.cache;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.cache.CommonCacheProviderRedis;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Redis implementation of the catalog cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogCacheProviderRedis extends CommonCacheProviderRedis {

    public CatalogCacheProviderRedis(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.redis.lettuce.uri") final String configURI) {
        super(configURI);
    }
}