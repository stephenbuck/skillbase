package com.headspin.skillbase.catalog.domain;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

/*
 * com.headspin.skillbase.catalog.category.deleted
 * com.headspin.skillbase.catalog.category.inserted
 * com.headspin.skillbase.catalog.category.updated
 * com.headspin.skillbase.catalog.skill.deleted
 * com.headspin.skillbase.catalog.skill.inserted
 * com.headspin.skillbase.catalog.skill.updated
 */
public class CatalogEvent {

    public static CloudEvent build(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }
}