package com.headspin.skillbase.common.infrastructure.features;

import com.headspin.skillbase.common.providers.CommonFeaturesProvider;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of common features provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonFeaturesProviderDummy implements CommonFeaturesProvider {

    @Inject
    public CommonFeaturesProviderDummy() {
    }

    @Override
    public boolean evaluateBoolean(@NotNull final String flag, final boolean def) {
        return false;
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
