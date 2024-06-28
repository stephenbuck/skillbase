package com.headspin.skillbase.identity.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class IdentityUserId implements Serializable {
    public UUID uuid;
}