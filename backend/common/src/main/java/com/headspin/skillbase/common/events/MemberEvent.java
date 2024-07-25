package com.headspin.skillbase.common.events;

import java.net.URI;
import java.util.UUID;

import com.headspin.skillbase.common.domain.DomainEvent;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import jakarta.ws.rs.core.MediaType;

public class MemberEvent extends DomainEvent {

    private static URI source = URI.create("http://skillbase.com");

    public static final String MEMBER_EVENT_TOPIC = "skillbase_member_event";

    public static final String MEMBER_ACHIEVEMENT_CREATED = "com.headspin.skillbase.member.achievement.created";
    public static final String MEMBER_ACHIEVEMENT_DELETED = "com.headspin.skillbase.member.achievement.deleted";
    public static final String MEMBER_ACHIEVEMENT_UPDATED = "com.headspin.skillbase.member.achievement.updated";

    public static final String MEMBER_GROUP_CREATED = "com.headspin.skillbase.member.group.created";
    public static final String MEMBER_GROUP_DELETED = "com.headspin.skillbase.member.group.deleted";
    public static final String MEMBER_GROUP_UPDATED = "com.headspin.skillbase.member.group.updated";

    public static final String MEMBER_PROCESS_CREATED = "com.headspin.skillbase.member.process.created";
    public static final String MEMBER_PROCESS_DELETED = "com.headspin.skillbase.member.process.deleted";
    public static final String MEMBER_PROCESS_UPDATED = "com.headspin.skillbase.member.process.updated";

    public static final String MEMBER_USER_CREATED = "com.headspin.skillbase.member.user.created";
    public static final String MEMBER_USER_DELETED = "com.headspin.skillbase.member.user.deleted";
    public static final String MEMBER_USER_UPDATED = "com.headspin.skillbase.member.user.updated";

    private final UUID id;
    private final String type;
    private final Object data;

    public MemberEvent(UUID id, String type, Object data) {
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

    public static MemberEvent buildEvent(UUID id, String type, Object data) {
        return new MemberEvent(id, type, data);
    }

    public static CloudEvent buildCloud(MemberEvent event) {
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
            "MemberEvent {\n" +
                "id   = " + id + "\n" +
                "type = " + type + "\n" +
            "}\n";
    }
}