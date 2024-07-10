package com.headspin.skillbase.common.security;

public enum SecurityRole {
    ADMIN("Admin"),
    PUBLISHER("Publisher"),
    CREATOR("Creator"),
    MEMBER("Member");

    public final String name;

    private SecurityRole(String name) {
        this.name = name;
    }

    public String[] list() {
        return new String[] {
            ADMIN.name,
            PUBLISHER.name,
            CREATOR.name,
            MEMBER.name
        }
    }
}
