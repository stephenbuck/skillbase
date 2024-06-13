package com.headspin.skillbase.members.domain;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "member_skill")
public record MemberSkill (

    @Column(name = "id") @Id @NotNull int id,

    @Column(name = "member_id") @ManyToOne @NotNull int memberId,

    @Column(name = "skill_id") @ManyToOne @NotNull int skillId,

    @Column(name = "status_code", nullable = false) @NotBlank String statusCode,

    @Column(name = "title") @NotNull String title,

    @Column(name = "note") @NotNull String note,

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date createdAt,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements Serializable {
}
