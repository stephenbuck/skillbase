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
 * Representation of a catalog credential entity.
 * 
 * Note that 'model_id' is a foreign key reference to a 'workflow model'.
 *
 * @author Stephen Buck
 * @since 1.0
 */

@Entity
@Table(schema = "catalog", name = "credential")
public class CatalogCredential extends DomainEntity {

        @JsonbProperty("credential_id")
        @Column(name = "credential_id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID credential_id;

        @JsonbProperty("model_id")
        @Column(name = "model_id") public UUID model_id;

        @JsonbProperty("skill_id")
        @Column(name = "skill_id") @NotNull public UUID skill_id;

        @JsonbProperty("is_enabled")
        @Column(name = "is_enabled") @NotNull public boolean is_enabled;

        @JsonbProperty("title")
        @Column(name = "title") @NotNull @NotBlank public String title;

        @JsonbProperty("note")
        @Column(name = "note") @NotNull public String note;

        @JsonbProperty("image_id")
        @Column(name = "image_id") public String image_id;

        @JsonbProperty("bpmn_id")
        @Column(name = "bpmn_id") public String bpmn_id;
        
        @JsonbProperty("created_at")
        @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date created_at;

        @JsonbProperty("updated_at")
        @Column(name = "updated_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date updated_at;

        @Override
        public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("CatalogCredential {\n");
                stringBuilder.append("    credential_id = " + credential_id + "\n");
                stringBuilder.append("    model_id      = " + model_id + "\n");
                stringBuilder.append("    skill_id      = " + skill_id + "\n");
                stringBuilder.append("    title         = " + title + "\n");
                stringBuilder.append("    image_id      = " + image_id + "\n");
                stringBuilder.append("    bpmn_id       = " + bpmn_id + "\n");
                stringBuilder.append("    created_at    = " + created_at + "\n");
                stringBuilder.append("    updated_at    = " + updated_at + "\n");
                stringBuilder.append("}\n");
                return stringBuilder.toString();
        }
}
