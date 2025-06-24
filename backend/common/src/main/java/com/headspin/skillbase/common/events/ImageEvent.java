package com.headspin.skillbase.common.events;

import java.util.UUID;

import jakarta.json.JsonObject;

public class ImageEvent extends SkillbaseEvent {

    public static final String STORAGE_EVENT_TOPIC = "skillbase_storage_event";

    /**
     * The STORAGE_FILE_CREATED event contains JSON data with the
     * following format:
     * 
     * {
     * "file_id": "<file_id>",
     * "created_at": "<created_at>",
     * "updated_at": "<updated_at>"
     * }
     */
    public static final String STORAGE_FILE_CREATED = "com.headspin.skillbase.storage.file.created";

    /**
     * The STORAGE_FILE_DELETED event contains JSON data with the
     * following format:
     * 
     * {
     * "file_id": "<file_id>"
     * }
     */
    public static final String STORAGE_FILE_DELETED = "com.headspin.skillbase.storage.file.deleted";

    /**
     * The STORAGE_FILE_UPDATED event contains JSON data with the
     * following format:
     * 
     * {
     * "file_id": "<file_id>",
     * "created_at": "<created_at>",
     * "updated_at": "<updated_at>"
     * }
     */
    public static final String STORAGE_FILE_UPDATED = "com.headspin.skillbase.storage.file.updated";

    public ImageEvent(String type, JsonObject data) {
        this(UUID.randomUUID(), type, data);
    }

    public ImageEvent(UUID id, String type, JsonObject data) {
        super(id, type, data);
    }

    public static ImageEvent of(UUID id, String type, JsonObject data) {
        return new ImageEvent(id, type, data);
    }

    @Override
    public String toString() {
        return "ImageEvent {\n" +
                "id   = " + id() + "\n" +
                "type = " + type() + "\n" +
                "data = " + data() + "\n" +
                "}\n";
    }
}
