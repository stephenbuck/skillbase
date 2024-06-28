package com.headspin.skillbase.certify.domain;

import java.io.Serializable;

import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class CertifyDocumentId implements Serializable {
    public UUID uuid;
}
