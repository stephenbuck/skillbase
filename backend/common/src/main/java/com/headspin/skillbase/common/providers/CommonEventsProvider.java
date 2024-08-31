package com.headspin.skillbase.common.providers;

import java.util.Collection;

import com.headspin.skillbase.common.events.EventListener;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotNull;

/**
 * Common events provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface CommonEventsProvider {

    /**
     * Produces an event with the specified topic, type, and JSON data.
     * 
     * @param topic
     * @param type
     * @param json
     */
    public void produce(@NotNull final String topic, @NotNull final String type, @NotNull final JsonObject json);

    /**
     * Starts consuming events with any of the the specified topics and
     * sends them to the specified listener.
     * 
     * @param topics
     * @param listener
     */
    public void consume(@NotNull final Collection<String> topics, @NotNull final EventListener listener);

    public void test();
    
}
