package com.headspin.skillbase.skill.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CategoryId implements org.jmolecules.ddd.types.Identifier, Serializable {
    public UUID uuid;
}
