package com.headspin.skillbase.skill.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CategoryId implements Serializable {
    public UUID uuid;
}
