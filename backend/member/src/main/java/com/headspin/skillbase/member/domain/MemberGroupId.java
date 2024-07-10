package com.headspin.skillbase.member.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class MemberGroupId implements Serializable {
    public UUID uuid;
}
