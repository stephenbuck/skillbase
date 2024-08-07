package com.headspin.skillbase.member.domain;

import java.util.Date;
import java.util.UUID;

import com.headspin.skillbase.common.domain.DomainEntity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(schema = "member", name = "achievement")
public class MemberAchievement extends DomainEntity {

        @JsonbProperty("achievement_id")
        @Column(name = "achievement_id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID achievement_id;

        @JsonbProperty("user_id")
        @Column(name = "user_id") @NotNull public UUID user_id;

        @JsonbProperty("state")
        @Column(name = "state") public String state;

        @JsonbProperty("title")
        @Column(name = "title") @NotNull @NotBlank public String title;

        @JsonbProperty("note")
        @Column(name = "note") @NotNull public String note;

        @JsonbProperty("image")
        @Column(name = "image") public byte[] image;

        @JsonbProperty("created_at")
        @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date created_at;

        @JsonbProperty("updated_at")
        @Column(name = "updated_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date updated_at;

        @Override
        public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MemberAchievement {\n");
                stringBuilder.append("    achievement_id = " + achievement_id + "\n");
                stringBuilder.append("    user_id        = " + user_id + "\n");
                stringBuilder.append("    title          = " + title + "\n");
                stringBuilder.append("    note           = " + note + "\n");
                stringBuilder.append("    created_at     = " + created_at + "\n");
                stringBuilder.append("    updated_at     = " + updated_at + "\n");
                stringBuilder.append("}\n");
                return stringBuilder.toString();
        }
}
