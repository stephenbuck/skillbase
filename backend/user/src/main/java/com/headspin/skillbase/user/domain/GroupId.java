package com.headspin.skillbase.user.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class GroupId implements Serializable {
    public UUID uuid;
}