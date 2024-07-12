package com.headspin.skillbase.member.infrastructure.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.member.providers.MemberConfigProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberConfigProviderDefault implements MemberConfigProvider {

    private Config config;

    public MemberConfigProviderDefault() {
        config = ConfigProvider.getConfig();
    }

    @Override
    public void test() {
        log.info("value = {}", getValue("dog"));
    }
    
    @Override
    public String getValue(String key) {
        return config.getValue(key, String.class);
    }
}
