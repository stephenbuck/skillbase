package com.headspin.skillbase.common.events;

import java.util.UUID;

import jakarta.json.JsonObject;

public class CatalogEvent extends SkillbaseEvent {

    public static final String CATALOG_EVENT_TOPIC = "skillbase_catalog_event";

    public static final String CATALOG_CATEGORY_CREATED = "com.headspin.skillbase.catalog.category.created";
    public static final String CATALOG_CATEGORY_DELETED = "com.headspin.skillbase.catalog.category.deleted";
    public static final String CATALOG_CATEGORY_UPDATED = "com.headspin.skillbase.catalog.category.updated";

    public static final String CATALOG_CREDENTIAL_CREATED = "com.headspin.skillbase.catalog.credential.created";
    public static final String CATALOG_CREDENTIAL_DELETED = "com.headspin.skillbase.catalog.credential.deleted";
    public static final String CATALOG_CREDENTIAL_UPDATED = "com.headspin.skillbase.catalog.credential.updated";

    public static final String CATALOG_SKILL_CREATED = "com.headspin.skillbase.catalog.skill.created";
    public static final String CATALOG_SKILL_DELETED = "com.headspin.skillbase.catalog.skill.deleted";
    public static final String CATALOG_SKILL_UPDATED = "com.headspin.skillbase.catalog.skill.updated";

    public CatalogEvent(String type, JsonObject data) {
        this(UUID.randomUUID(), type, data);
    }

    public CatalogEvent(UUID id, String type, JsonObject data) {
        super(id, type, data);
    }

    @Override
    public String toString() {
        return
            "CatalogEvent {\n" +
                "id   = " + id() + "\n" +
                "type = " + type() + "\n" +
                "data = " + data() + "\n" +
            "}\n";
    }
}
