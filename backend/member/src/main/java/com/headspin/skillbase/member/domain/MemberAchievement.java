package com.headspin.skillbase.member.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.headspin.skillbase.common.domain.DomainEntity;

import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Cacheable
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

    @JsonbProperty("image_id")
    @Column(name = "image_id") public String image_id;

    @JsonbProperty("valid_for")
    @Column(name = "valid_for") @NotNull public Integer valid_for;

    @JsonbProperty("created_at")
    @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public LocalDateTime created_at;

    @JsonbProperty("updated_at")
    @Column(name = "updated_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public LocalDateTime updated_at;

    @JsonbProperty("version")
    @Column(name = "version") @NotNull @Version public Integer version;

    @Override
    public String toString() {
        return new StringBuilder()
            .append("MemberAchievement {\n")
            .append("    achievement_id = " + achievement_id + "\n")
            .append("    user_id        = " + user_id + "\n")
            .append("    title          = " + title + "\n")
            .append("    note           = " + note + "\n")
            .append("    image_id       = " + image_id + "\n")
            .append("    valid_for      = " + valid_for + "\n")
            .append("    created_at     = " + created_at + "\n")
            .append("    updated_at     = " + updated_at + "\n")
            .append("    version        = " + version + "\n")
            .append("}\n")
            .toString();
    }

    public String toETag() {
        return String.valueOf(hashCode());
    }

    public static MemberAchievement fromJson(final String json) throws Exception {
        return JsonbBuilder.create().fromJson(json, MemberAchievement.class);
    }
    
    public static String toJson(final MemberAchievement achievement) throws Exception {
        return JsonbBuilder.create().toJson(achievement);
    }
}
