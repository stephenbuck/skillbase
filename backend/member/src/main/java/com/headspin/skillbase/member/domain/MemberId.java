package com.headspin.skillbase.member.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

import org.jmolecules.ddd.types.Identifier;

@Embeddable
public class MemberId implements Identifier, Serializable {

    public UUID uuid;

}