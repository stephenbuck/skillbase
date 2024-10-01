package com.headspin.skillbase.common.infrastructure.search;

import java.util.List;

import com.headspin.skillbase.common.providers.CommonSearchProvider;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common search provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonSearchProviderDummy implements CommonSearchProvider {

    @Inject
    public CommonSearchProviderDummy() {
    }

    @Override
    public List<String> search(@NotNull final String keyword, final String sort, final Integer offset,
            final Integer limit) {
        return null;
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
