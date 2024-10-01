package com.headspin.skillbase.catalog.domain;

import java.time.LocalDateTime;
import java.util.Date;
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
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.headspin.skillbase.common.domain.DomainEntity;

/**
 * Representation of a catalog credential entity.
 * 
 * Note that 'model_id' is a foreign key reference to a 'workflow model'.
 *
 * @author Stephen Buck
 * @since 1.0
 */

@Entity
@Cacheable
@Table(schema = "catalog", name = "credential")
public class CatalogCredential extends DomainEntity {

    @JsonbProperty("credential_id")
    @Column(name = "credential_id")
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID credential_id;

    @JsonbProperty("skill_id")
    @Column(name = "skill_id")
    @NotNull
    public UUID skill_id;

    @JsonbProperty("is_enabled")
    @Column(name = "is_enabled")
    @NotNull
    public boolean is_enabled;

    @JsonbProperty("title")
    @Column(name = "title")
    @NotNull
    @NotBlank
    public String title;

    @JsonbProperty("note")
    @Column(name = "note")
    @NotNull
    public String note;

    @JsonbProperty("image_id")
    @Column(name = "image_id")
    public String image_id;

    @JsonbProperty("bpmn_id")
    @Column(name = "bpmn_id")
    public String bpmn_id;

    @JsonbProperty("created_at")
    @Column(name = "created_at")
    @NotNull
    public Date created_at;

    @JsonbProperty("updated_at")
    @Column(name = "updated_at")
    @NotNull
    public Date updated_at;

    @JsonbProperty("version")
    @Column(name = "version")
    @NotNull
    @Version
    public Integer version;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("CatalogCredential {\n")
                .append("    credential_id = " + credential_id + "\n")
                .append("    skill_id      = " + skill_id + "\n")
                .append("    is_enabled    = " + is_enabled + "\n")
                .append("    title         = " + title + "\n")
                .append("    note          = " + title + "\n")
                .append("    image_id      = " + image_id + "\n")
                .append("    bpmn_id       = " + bpmn_id + "\n")
                .append("    created_at    = " + created_at + "\n")
                .append("    updated_at    = " + updated_at + "\n")
                .append("    version       = " + version + "\n")
                .append("}\n")
                .toString();
    }

    public String toETag() {
        return String.valueOf(hashCode());
    }

    public static CatalogCredential fromJson(final String json) throws Exception {
        return JsonbBuilder.create().fromJson(json, CatalogCredential.class);
    }

    public static String toJson(final CatalogCredential credential) throws Exception {
        return JsonbBuilder.create().toJson(credential);
    }
}
