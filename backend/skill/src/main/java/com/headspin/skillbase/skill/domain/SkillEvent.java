package com.headspin.skillbase.skill.domain;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

/*
 * com.headspin.skillbase.skill.deleted
 * com.headspin.skillbase.skill.inserted
 * com.headspin.skillbase.skill.updated
 */
public class SkillEvent {

    public static CloudEvent build(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }
}