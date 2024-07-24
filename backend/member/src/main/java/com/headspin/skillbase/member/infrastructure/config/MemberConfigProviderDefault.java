package com.headspin.skillbase.member.infrastructure.config;

import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.member.providers.MemberConfigProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberConfigProviderDefault implements MemberConfigProvider {

    private final Config config;

    public MemberConfigProviderDefault() {
        this.config = ConfigProvider.getConfig();
    }

    @Override
    public void test() {
        log.info("foo = {}", getOptionalValue("com.headspin.skillbase.member.foo", String.class));
        log.info("flipt url = {}", getOptionalValue("com.headspin.skillbase.member.flipt.url", String.class));
        log.info("kafka url = {}", getOptionalValue("com.headspin.skillbase.member.kafka.url", String.class));
        log.info("keycloak url = {}", getOptionalValue("com.headspin.skillbase.member.keycloak.url", String.class));
    }
    
    @Override
    public Optional<?> getOptionalValue(String key, Class<?> type) {
        return config.getOptionalValue(key, type);
    }
}
