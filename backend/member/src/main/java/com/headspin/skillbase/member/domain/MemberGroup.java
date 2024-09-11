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
@Table(schema = "member", name = "group")
public class MemberGroup extends DomainEntity {

    @JsonbProperty("group_id")
    @Column(name = "group_id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID group_id;

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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MemberGroup {\n");
        stringBuilder.append("    group_id   = " + group_id + "\n");
        stringBuilder.append("    title      = " + title + "\n");
        stringBuilder.append("    note       = " + note + "\n");
        stringBuilder.append("    image_id   = " + image_id + "\n");
        stringBuilder.append("    valid_for  = " + valid_for + "\n");
        stringBuilder.append("    created_at = " + created_at + "\n");
        stringBuilder.append("    updated_at = " + updated_at + "\n");
        stringBuilder.append("    version    = " + version + "\n");
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public static MemberGroup fromJson(String json) throws Exception {
        return JsonbBuilder.create().fromJson(json, MemberGroup.class);
    }
    
    public static String toJson(MemberGroup group) throws Exception {
        return JsonbBuilder.create().toJson(group);
    }
}
