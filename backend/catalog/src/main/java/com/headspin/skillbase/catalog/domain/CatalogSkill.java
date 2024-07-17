package com.headspin.skillbase.catalog.domain;

import java.util.Date;
import java.util.UUID;

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
@Table(schema = "catalog", name = "skill")
public class CatalogSkill extends DomainEntity {

        @JsonbProperty("id")
        @Column(name = "id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID id;

        @JsonbProperty("deployment_id") public UUID deployment_id;

        @JsonbProperty("category_id")
        @Column(name = "category_id") public UUID category_id;

        @JsonbProperty("is_enabled")
        @Column(name = "is_enabled") @NotNull public boolean is_enabled;

        @JsonbProperty("title")
        @Column(name = "title") @NotNull @NotBlank public String title;

        @JsonbProperty("note")
        @Column(name = "note") @NotNull public String note;

        @JsonbProperty("image")
        @Column(name = "image") public byte[] image;

        @JsonbProperty("created_at")
        @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date createdAt;

        @JsonbProperty("updated_at")
        @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) public Date updatedAt;

        @Override
        public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("CatalogSkill {\n");
                stringBuilder.append("    id            = " + id + "\n");
                stringBuilder.append("    deployment_id = " + deployment_id + "\n");
                stringBuilder.append("    category_id   = " + category_id + "\n");
                stringBuilder.append("    title         = " + title + "\n");
                stringBuilder.append("    note          = " + note + "\n");
                stringBuilder.append("    createdAt     = " + createdAt + "\n");
                stringBuilder.append("    updatedAt     = " + updatedAt + "\n");
                stringBuilder.append("}\n");
                return stringBuilder.toString();
        }
}
