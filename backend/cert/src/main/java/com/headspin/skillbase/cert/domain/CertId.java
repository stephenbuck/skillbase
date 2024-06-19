package com.headspin.skillbase.cert.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class CertId implements Serializable {
    public UUID id;
}