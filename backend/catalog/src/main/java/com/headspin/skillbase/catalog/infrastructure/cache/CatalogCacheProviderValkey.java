package com.headspin.skillbase.catalog.infrastructure.cache;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.cache.CommonCacheProviderValkey;
import com.headspin.skillbase.common.providers.CommonCacheProvider;

import io.valkey.Jedis;
import io.valkey.JedisPool;
import io.valkey.JedisPoolConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Valkey implementation of the common cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CatalogCacheProviderValkey extends CommonCacheProviderValkey {
    @Inject
    public CatalogCacheProviderValkey(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.host") final String configHost,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.port") final Integer configPort,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.timeout") final Integer configTimeout,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.maxtotal") final Integer configMaxTotal,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.maxidle") final Integer configMaxIdle,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.minidle") final Integer configMinIdle) {
                super(configHost, configPort, configTimeout, configPassword, configMaxTotal, configMaxIdle, configMinIdle);
    }
}
