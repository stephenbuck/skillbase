package com.headspin.skillbase.member.infrastructure.cache;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.common.infrastructure.cache.CommonCacheProviderValkey;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
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
public class MemberCacheProviderValkey extends CommonCacheProviderValkey {

    public MemberCacheProviderValkey(
            @ConfigProperty(name = "com.headspin.skillbase.member.cache.valkey.host") final String configHost,
            @ConfigProperty(name = "com.headspin.skillbase.member.cache.valkey.port") final Integer configPort,
            @ConfigProperty(name = "com.headspin.skillbase.member.cache.valkey.timeout") final Integer configTimeout,
            @ConfigProperty(name = "com.headspin.skillbase.member.cache.valkey.password") final String configPassword,
            @ConfigProperty(name = "com.headspin.skillbase.member.cache.valkey.maxtotal") final Integer configMaxTotal,
            @ConfigProperty(name = "com.headspin.skillbase.member.cache.valkey.maxidle") final Integer configMaxIdle,
            @ConfigProperty(name = "com.headspin.skillbase.member.cache.valkey.minidle") final Integer configMinIdle) {
                super(configHost, configPort, configTimeout, configPassword, configMaxTotal, configMaxIdle, configMinIdle);
    }
}
