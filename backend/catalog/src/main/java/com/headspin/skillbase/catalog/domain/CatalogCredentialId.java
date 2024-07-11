package com.headspin.skillbase.catalog.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CatalogCredentialId implements Serializable {
    public UUID uuid;
}
