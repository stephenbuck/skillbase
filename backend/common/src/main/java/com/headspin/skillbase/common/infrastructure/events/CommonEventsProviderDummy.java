package com.headspin.skillbase.common.infrastructure.events;

import java.util.Collection;

import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.common.events.EventListener;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Dummy implementation of the common events provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class CommonEventsProviderDummy implements CommonEventsProvider {

    @Inject
    public CommonEventsProviderDummy() {
    }

    @Override
    public void produce(@NotNull final String topic, @NotNull final String type, @NotNull final String json) {
        log.info("produce(" + topic + ")");
    }

    @Override
    public void consume(@NotNull final Collection<String> topics, @NotNull final EventListener listener) {
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
