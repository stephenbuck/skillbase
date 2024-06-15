package com.headspin.skillbase.certificate.domain;

import java.io.Serializable;
import java.util.UUID;

import org.jmolecules.ddd.types.Identifier;

import jakarta.persistence.Embeddable;

@Embeddable
public class CertificateId implements Identifier, Serializable {
    public UUID id;
}