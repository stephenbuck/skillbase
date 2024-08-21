package com.headspin.skillbase.storage.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class StorageCategoryId implements Serializable {
    public UUID uuid;
}
