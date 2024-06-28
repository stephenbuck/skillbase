package com.headspin.skillbase.catalog.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class CatalogSkillId implements Serializable {
    public UUID uuid;
}
