package com.headspin.skillbase.member.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class MemberProcessId implements Serializable {
    public UUID uuid;
}
