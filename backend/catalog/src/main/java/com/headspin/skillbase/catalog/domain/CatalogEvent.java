package com.headspin.skillbase.catalog.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

public class CatalogEvent implements Serializable {

    private static URI source = URI.create("http://skillbase.com");

    public static final String CATALOG_EVENT_CATEGORY_INSERTED = "com.headspin.skillbase.catalog.category.inserted";
    public static final String CATALOG_EVENT_CATEGORY_DELETED = "com.headspin.skillbase.catalog.category.deleted";
    public static final String CATALOG_EVENT_CATEGORY_UPDATED = "com.headspin.skillbase.catalog.category.updated";

    public static final String CATALOG_EVENT_SKILL_INSERTED = "com.headspin.skillbase.catalog.skill.inserted";
    public static final String CATALOG_EVENT_SKILL_DELETED = "com.headspin.skillbase.catalog.skill.deleted";
    public static final String CATALOG_EVENT_SKILL_UPDATED = "com.headspin.skillbase.catalog.skill.updated";

    private UUID id;
    private String type;

    public CatalogEvent(UUID id, String type) {
        this.id = id;
        this.type = type;
    }

    public UUID id() {
        return this.id;
    }

    public String type() {
        return this.type;
    }

    public static CatalogEvent buildEvent(UUID id, String type) {
        return new CatalogEvent(id, type);
    }

    public static CloudEvent buildCloud(CatalogEvent event) {
        return CloudEventBuilder
                .v1()
                .withId(String.valueOf(event.id()))
                .withType(event.type())
                .withSource(source)
                .build();
    }
}