package com.headspin.skillbase.common.events;

import java.util.UUID;

import jakarta.json.JsonObject;

public class MemberEvent extends SkillbaseEvent {

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

    public MemberEvent(String type, JsonObject data) {
        this(UUID.randomUUID(), type, data);
    }

    public MemberEvent(UUID id, String type, JsonObject data) {
        super(id, type, data);
    }

    @Override
    public String toString() {
        return
            "MemberEvent {\n" +
                "id   = " + id() + "\n" +
                "type = " + type() + "\n" +
                "data = " + data() + "\n" +
            "}\n";
    }
}