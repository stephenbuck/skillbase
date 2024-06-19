package com.headspin.skillbase.skill.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class SkillId implements Serializable {
    public UUID uuid;
}
