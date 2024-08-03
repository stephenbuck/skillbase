package com.headspin.skillbase.workflow.providers;

import com.headspin.skillbase.common.events.EventListener;

import java.util.Collection;

import jakarta.json.JsonObject;

/**
 * Workflow events provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowEventsProvider {

    /**
     * Produces an event with the specified topic, type, and JSON data.
     * 
     * @param topic
     * @param type
     * @param json
     */
    public void produce(String topic, String type, JsonObject json);

    /**
     * Starts consuming events with any of the the specified topics and
     * sends them to the specified listener.
     * 
     * @param topics
     * @param listener
     */
    public void consume(Collection<String> topics, EventListener listener);

    public void test();

}
