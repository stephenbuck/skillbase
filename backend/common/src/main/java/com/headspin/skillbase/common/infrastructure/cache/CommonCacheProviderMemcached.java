package com.headspin.skillbase.common.infrastructure.cache;

import java.io.IOException;

import com.headspin.skillbase.common.providers.CommonCacheProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;

/**
 * Memcached implementation of the common cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class CommonCacheProviderMemcached implements CommonCacheProvider {

    private final int expiration;
    private final MemcachedClient client;

    public CommonCacheProviderMemcached(
        final String configAddresses,
        final Integer configExpiration
    ) throws IOException
    {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(configAddresses);
        this.client = builder.build();
        this.expiration = configExpiration;
    }
    
    @Override
    public String get(final String key) throws Exception {
        return client.get(key);
    }

    @Override
    public boolean set(final String key, final String val) throws Exception {
        return client.set(key, expiration, val);
    }

    @Override
    public boolean touch(final String key) throws Exception {
        return client.touch(key, expiration);
    }

    @Override
    public boolean exists(final String key) throws Exception {
        return get(key) != null;
    }

    @Override
    public boolean delete(final String key) throws Exception {
        return client.delete(key);
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
