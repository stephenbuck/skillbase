package com.headspin.skillbase.common.events;

import java.util.UUID;

import jakarta.json.JsonObject;

public class MemberEvent extends SkillbaseEvent {

    public static final String MEMBER_EVENT_TOPIC = "skillbase_member_event";


    /**
     * The MEMBER_ACHIEVEMENT_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String MEMBER_ACHIEVEMENT_CREATED = "com.headspin.skillbase.member.achievement.created";

    /**
     * The MEMBER_ACHIEVEMENT_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>"
     *     }
     */
    public static final String MEMBER_ACHIEVEMENT_DELETED = "com.headspin.skillbase.member.achievement.deleted";

    /**
     * The MEMBER_ACHIEVEMENT_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String MEMBER_ACHIEVEMENT_UPDATED = "com.headspin.skillbase.member.achievement.updated";


    /**
     * The MEMBER_GROUP_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String MEMBER_GROUP_CREATED = "com.headspin.skillbase.member.group.created";

    /**
     * The MEMBER_GROUP_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>"
     *     }
     */
    public static final String MEMBER_GROUP_DELETED = "com.headspin.skillbase.member.group.deleted";

    /**
     * The MEMBER_GROUP_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String MEMBER_GROUP_UPDATED = "com.headspin.skillbase.member.group.updated";


    /**
     * The MEMBER_PROCESS_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String MEMBER_PROCESS_CREATED = "com.headspin.skillbase.member.process.created";

    /**
     * The MEMBER_PROCESS_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>"
     *     }
     */
    public static final String MEMBER_PROCESS_DELETED = "com.headspin.skillbase.member.process.deleted";

    /**
     * The MEMBER_PROCESS_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String MEMBER_PROCESS_UPDATED = "com.headspin.skillbase.member.process.updated";


    /**
     * The MEMBER_USER_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
    public static final String MEMBER_USER_CREATED = "com.headspin.skillbase.member.user.created";

    /**
     * The MEMBER_USER_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>"
     *     }
     */
    public static final String MEMBER_USER_DELETED = "com.headspin.skillbase.member.user.deleted";

    /**
     * The MEMBER_USER_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "id": "<id>",
     *         "title": "<title>"
     *     }
     */
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