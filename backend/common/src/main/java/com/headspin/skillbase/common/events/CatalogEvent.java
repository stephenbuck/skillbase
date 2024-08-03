package com.headspin.skillbase.common.events;

import java.util.UUID;

import jakarta.json.JsonObject;

public class CatalogEvent extends SkillbaseEvent {

    public static final String CATALOG_EVENT_TOPIC  = "skillbase_catalog_event";


    /**
     * The CATALOG_CATEGORY_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String CATALOG_CATEGORY_CREATED = "com.headspin.skillbase.catalog.category.created";
 
    /**
     * The CATALOG_CATEGORY_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>"
     *     }
     */
    public static final String CATALOG_CATEGORY_DELETED = "com.headspin.skillbase.catalog.category.deleted";
 
    /**
     * The CATALOG_CATEGORY_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */    
    public static final String CATALOG_CATEGORY_UPDATED = "com.headspin.skillbase.catalog.category.updated";


    /**
     * The CATALOG_CREDENTIAL_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String CATALOG_CREDENTIAL_CREATED = "com.headspin.skillbase.catalog.credential.created";

    /**
     * The CATALOG_CREDENTIAL_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>"
     *     }
     */
    public static final String CATALOG_CREDENTIAL_DELETED = "com.headspin.skillbase.catalog.credential.deleted";

    /**
     * The CATALOG_CREDENTIAL_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String CATALOG_CREDENTIAL_UPDATED = "com.headspin.skillbase.catalog.credential.updated";


    /**
     * The CATALOG_SKILL_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String CATALOG_SKILL_CREATED = "com.headspin.skillbase.catalog.skill.created";

    /**
     * The CATALOG_SKILL_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>"
     *     }
     */
    public static final String CATALOG_SKILL_DELETED = "com.headspin.skillbase.catalog.skill.deleted";

    /**
     * The CATALOG_SKILL_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
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
