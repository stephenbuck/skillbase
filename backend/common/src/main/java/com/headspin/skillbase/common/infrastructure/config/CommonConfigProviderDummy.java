package com.headspin.skillbase.common.infrastructure.config;

import java.util.Optional;

import com.headspin.skillbase.common.providers.CommonConfigProvider;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of common config provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonConfigProviderDummy implements CommonConfigProvider {

    @Inject
    public CommonConfigProviderDummy() {
    }

    @Override
    public Optional<?> getOptionalValue(@NotNull final String key, @NotNull final Class<?> type) {
        return null;
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
