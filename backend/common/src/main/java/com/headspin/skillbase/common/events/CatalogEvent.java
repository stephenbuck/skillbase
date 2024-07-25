package com.headspin.skillbase.common.events;

import java.net.URI;
import java.util.UUID;

import com.headspin.skillbase.common.domain.DomainEvent;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import jakarta.activation.MimeType;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;

public class CatalogEvent extends DomainEvent {

    private static URI source = URI.create("http://skillbase.com");

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

    private final UUID id;
    private final String type;
    private final Object data;

    public CatalogEvent(UUID id, String type, Object data) {
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

    public Object data() {
        return this.data;
    }

    public static CatalogEvent buildEvent(UUID id, String type, Object data) {
        return new CatalogEvent(id, type, data);
    }

    public static CloudEvent buildCloud(CatalogEvent event) {
        return CloudEventBuilder
                .v1()
                .withId(String.valueOf(event.id()))
                .withType(event.type())
                .withSource(source)
                .withData(MediaType.APPLICATION_JSON, String.valueOf(event.data).getBytes())
                .build();
    }

    @Override
    public String toString() {
        return
            "CatalogEvent {\n" +
                "id   = " + id + "\n" +
                "type = " + type + "\n" +
            "}\n";
    }

    public static class CategoryCreated {
        @JsonbProperty("id") @NotNull public UUID category_id;
        @JsonbProperty("title") @NotNull public String title;
        public CategoryCreated(@NotNull UUID category_id, @NotNull String title) {
            this.category_id = category_id;
            this.title = title;
        }
    }

    public static class CategoryDeleted {
        @JsonbProperty("id") @NotNull public UUID category_id;
        public CategoryDeleted(@NotNull UUID category_id) {
            this.category_id = category_id;
        }
    }

    public static class CategoryUpdated {
        @JsonbProperty("id") @NotNull public UUID category_id;
        @JsonbProperty("title") @NotNull public String title;
        public CategoryUpdated(@NotNull UUID category_id, @NotNull String title) {
            this.category_id = category_id;
            this.title = title;
        }

    }

    public static class SkillCreated {
        @JsonbProperty("skill_id") @NotNull public UUID skill_id;
        @JsonbProperty("title") @NotNull public String title;
        public SkillCreated(@NotNull UUID skill_id, @NotNull String title) {
            this.skill_id = skill_id;
            this.title = title;
        }
    }

    public static class SkillDeleted {
        @JsonbProperty("skill_id") @NotNull public UUID skill_id;
        public SkillDeleted(@NotNull UUID skill_id) {
            this.skill_id = skill_id;
        }
    }

    public static class SkillUpdated {
        @JsonbProperty("skill_id") @NotNull public UUID skill_id;
        @JsonbProperty("title") @NotNull public String title;
        public SkillUpdated(@NotNull UUID skill_id, @NotNull String title) {
            this.skill_id = skill_id;
            this.title = title;
        }
    }

    public static class CredentialCreated {
        @JsonbProperty("credential_id") @NotNull public UUID credential_id;
        @JsonbProperty("skill_id") @NotNull public UUID skill_id;
        @JsonbProperty("title") @NotNull public String title;
        public CredentialCreated(@NotNull UUID credential_id, @NotNull UUID skill_id, @NotNull String title) {
            this.credential_id = credential_id;
            this.skill_id = skill_id;
            this.title = title;
        }
    }

    public static class CredentialDeleted {
        @JsonbProperty("credential_id") @NotNull public UUID credential_id;
        public CredentialDeleted(@NotNull UUID credential_id) {
            this.credential_id = credential_id;
        }
    }

    public static class CredentialUpdated {
        @JsonbProperty("credential_id") @NotNull public UUID credential_id;
        @JsonbProperty("title") @NotNull public String title;
        public CredentialUpdated(@NotNull UUID credential_id, @NotNull String title) {
            this.credential_id = credential_id;
            this.title = title;
        }
    }

    /*
        CatalogEvent.CategoryCreated cc = new CatalogEvent.CategoryCreated();
        cc.id = UUID.randomUUID();
        cc.title = "<TBD>";

        Jsonb jb = new JsonBuilder.create();
        String json = jb.toJson(cc);
        CatalogEvent.CategoryCreated cd = jb.fromJson(jb, CatalogEvent.CategoryCreated.class);

        log.info("###################################");
        log.info("json = {}", json);
        log.info("###################################");
     */
}
