package com.headspin.skillbase.member.infrastructure.config;

import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.headspin.skillbase.common.providers.CommonConfigProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Default implementation of the member config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class MemberConfigProviderDefault implements CommonConfigProvider {

    private final Config config;

    public MemberConfigProviderDefault() {
        this.config = ConfigProvider.getConfig();
    }

    @Override
    public Optional<?> getOptionalValue(String key, Class<?> type) {
        return config.getOptionalValue(key, type);
    }

    @Override
    public void test() {
        log.info("test:");
        log.info("flipt url = {}", getOptionalValue("com.headspin.skillbase.member.flipt.url", String.class));
        log.info("kafka url = {}", getOptionalValue("com.headspin.skillbase.member.kafka.url", String.class));
        log.info("keycloak url = {}", getOptionalValue("com.headspin.skillbase.member.keycloak.url", String.class));
    }
}
