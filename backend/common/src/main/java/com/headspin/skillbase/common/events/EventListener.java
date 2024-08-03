package com.headspin.skillbase.common.events;

import io.cloudevents.CloudEvent;

public interface EventListener {
    public void onCloudEvent(String topic, CloudEvent event);
}
