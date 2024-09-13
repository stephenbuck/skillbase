package com.headspin.skillbase.member.infrastructure.cache;

import com.headspin.skillbase.common.providers.CommonCacheProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the member cache provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class MemberCacheProviderDummy implements CommonCacheProvider {

    public MemberCacheProviderDummy() {
    }
    
    @Override
    public String get(final String key) throws Exception {
        return null;
    }

    @Override
    public boolean set(final String key, final String object) throws Exception {
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
