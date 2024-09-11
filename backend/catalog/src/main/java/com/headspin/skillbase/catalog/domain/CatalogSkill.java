package com.headspin.skillbase.catalog.domain;

import java.time.LocalDateTime;
import java.util.UUID;

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

import com.headspin.skillbase.common.domain.DomainEntity;

/**
 * Representation of a catalog skill entity.
 * 
 * Note that 'deployment_id' is a foreign key reference to a 'workflow deployment'.
 *
 * @author Stephen Buck
 * @since 1.0
 */

@Entity
@Cacheable
@Table(schema = "catalog", name = "skill")
public class CatalogSkill extends DomainEntity {

    @JsonbProperty("skill_id")
    @Column(name = "skill_id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID skill_id;

    @JsonbProperty("deployment_id")
    @Column(name = "deployment_id") public UUID deployment_id;

    @JsonbProperty("category_id")
    @Column(name = "category_id") public UUID category_id;

    @JsonbProperty("is_enabled")
    @Column(name = "is_enabled") @NotNull public boolean is_enabled;

    @JsonbProperty("title")
    @Column(name = "title") @NotNull @NotBlank public String title;

    @JsonbProperty("note")
    @Column(name = "note") @NotNull public String note;

    @JsonbProperty("image_id")
    @Column(name = "image_id") public String image_id;

    @JsonbProperty("created_at")
    @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public LocalDateTime created_at;

    @JsonbProperty("updated_at")
    @Column(name = "updated_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public LocalDateTime updated_at;

    @JsonbProperty("version")
    @Column(name = "version") @NotNull @Version public Integer version;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CatalogSkill {\n");
        stringBuilder.append("    skill_id      = " + skill_id + "\n");
        stringBuilder.append("    deployment_id = " + deployment_id + "\n");
        stringBuilder.append("    category_id   = " + category_id + "\n");
        stringBuilder.append("    is_enabled    = " + is_enabled + "\n");
        stringBuilder.append("    title         = " + title + "\n");
        stringBuilder.append("    note          = " + note + "\n");
        stringBuilder.append("    image_id      = " + image_id + "\n");
        stringBuilder.append("    created_at    = " + created_at + "\n");
        stringBuilder.append("    updated_at    = " + updated_at + "\n");
        stringBuilder.append("    version       = " + version + "\n");
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public static CatalogSkill fromJson(String json) throws Exception {
        return JsonbBuilder.create().fromJson(json, CatalogSkill.class);
    }
    
    public static String toJson(CatalogSkill skill) throws Exception {
        return JsonbBuilder.create().toJson(skill);
    }
}
