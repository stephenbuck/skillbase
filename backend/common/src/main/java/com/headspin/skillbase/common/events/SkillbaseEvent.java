package com.headspin.skillbase.common.events;

import java.util.UUID;

import com.headspin.skillbase.common.domain.DomainEvent;

import jakarta.json.JsonObject;

public abstract class SkillbaseEvent extends DomainEvent {

    public static final String SKILLBASE_EVENT_SOURCE = "http://skillbase.com";

    private final UUID id;
    private final String type;
    private final JsonObject data;

    public SkillbaseEvent(UUID id, String type, JsonObject data) {
        this.id = id;
        this.type = type;
        this.data = data;
    }

    public UUID id() {
        return this.id;
    }

    public String type() {
        return this.type;
    }

    public JsonObject data() {
        return this.data;
    }
}
