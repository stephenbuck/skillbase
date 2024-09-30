package com.headspin.skillbase.member.infrastructure.cache;

import java.io.IOException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.cache.CommonCacheProviderMemcached;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import lombok.extern.slf4j.Slf4j;

/**
 * Memcached implementation of the common cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class MemberCacheProviderMemcached extends CommonCacheProviderMemcached {

    public MemberCacheProviderMemcached(
        @ConfigProperty(name = "com.headspin.skillbase.member.cache.memcached.xmemcached.addresses") final String configAddresses,
        @ConfigProperty(name = "com.headspin.skillbase.member.cache.memcached.xmemcached.expiration", defaultValue="0") final Integer configExpiration
    ) throws IOException
    {
        super(configAddresses, configExpiration);
    }
}
