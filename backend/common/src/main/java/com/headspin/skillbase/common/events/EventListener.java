package com.headspin.skillbase.common.events;

import io.cloudevents.CloudEvent;

public interface EventListener {
    void onCloudEvent(String topic, CloudEvent event);
}
