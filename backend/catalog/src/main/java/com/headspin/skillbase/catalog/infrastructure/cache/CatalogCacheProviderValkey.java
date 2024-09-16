package com.headspin.skillbase.catalog.infrastructure.cache;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.providers.CommonCacheProvider;

import io.valkey.Jedis;
import io.valkey.JedisPool;
import io.valkey.JedisPoolConfig;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Valkey implementation of the catalog cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class CatalogCacheProviderValkey implements CommonCacheProvider {

    private final JedisPool jedisPool;

    public CatalogCacheProviderValkey(
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.host") final String configHost,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.port") final Integer configPort,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.timeout") final Integer configTimeout,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.maxtotal") final Integer configMaxTotal,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.maxidle") final Integer configMaxIdle,
            @ConfigProperty(name = "com.headspin.skillbase.catalog.cache.valkey.minidle") final Integer configMinIdle) {

        // It is recommended that maxTotal = maxIdle = 2*minIdle for best performance
        final JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(configMaxTotal);
        config.setMaxIdle(configMaxIdle);
        config.setMinIdle(configMinIdle);

        this.jedisPool = new JedisPool(
                config,
                configHost,
                configPort,
                configTimeout,
                configPassword);
    }

    @Override
    public String get(final String key) throws Exception {
        try (final Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    @Override
    public boolean set(final String key, final String val) throws Exception {
        try (final Jedis jedis = jedisPool.getResource()) {
            return "OK".equals(jedis.set(key, val));
        }
    }

    @Override
    public boolean touch(final String key) throws Exception {
        try (final Jedis jedis = jedisPool.getResource()) {
            return jedis.touch(key) == 1;
        }
    }

    @Override
    public boolean exists(final String key) throws Exception {
        try (final Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    @Override
    public boolean delete(final String key) throws Exception {
        try (final Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key) == 1;
        }
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
