package com.headspin.skillbase.catalog.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.domain.CatalogSkillRepo;
import com.headspin.skillbase.catalog.providers.CatalogConfigProvider;
import com.headspin.skillbase.catalog.providers.CatalogFeaturesProvider;
import com.headspin.skillbase.catalog.providers.CatalogEventsProvider;
import com.headspin.skillbase.common.events.CatalogEvent;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Catalog skills service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class CatalogSkillsService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private CatalogSkillRepo repo;

    @Inject
    private CatalogConfigProvider conf;

    @Inject
    private CatalogFeaturesProvider feat;

    @Inject
    private CatalogEventsProvider evnt;

    /**
     * Inserts a new catalog skill.
     *
     * @param skill The new skill.
     * @return The id of the new skill.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid CatalogSkill skill) {
        UUID skill_id = repo.insert(skill);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_SKILL_CREATED,
            Json.createObjectBuilder()
                .add("skill_id", String.valueOf(skill.skill_id))
                .add("deployment_id", String.valueOf(skill.deployment_id))
                .add("category_id", String.valueOf(skill.category_id))
                .add("is_enabled", skill.is_enabled)
                .add("title", skill.title)
                .add("note", skill.note)
                .add("created_at", String.valueOf(skill.created_at))
                .add("updated_at", String.valueOf(skill.updated_at))
                .build());
        return skill_id;
    }

    /**
     * Deletes a catalog skill given an id.
     *
     * @param skill_id The requested skill id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID skill_id) {
        repo.delete(skill_id);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_SKILL_DELETED,
            Json.createObjectBuilder()
                .add("skill_id", String.valueOf(skill_id))
                .build());
    }

    /**
     * Updates an existing catalog skill.
     *
     * @param skill The updated skill.
     * @return The updated skill.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogSkill update(@NotNull @Valid CatalogSkill skill) {
        CatalogSkill updated = repo.update(skill);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_SKILL_UPDATED,
            Json.createObjectBuilder()
                .add("skill_id", String.valueOf(updated.skill_id))
                .add("deployment_id", String.valueOf(updated.deployment_id))
                .add("category_id", String.valueOf(updated.category_id))
                .add("is_enabled", updated.is_enabled)
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Returns a catalog skill given an id.
     *
     * @param skill_id The requested skill id.
     * @return An optional catalog skill.
     * @since 1.0
     */
//    @RolesAllowed({ "Member" })
    public Optional<CatalogSkill> findById(@NotNull UUID skill_id) {
        return repo.findById(skill_id);
    }

    /**
     * Returns a list of all catalog skills.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog skills.
     * @since 1.0
     */
//    @RolesAllowed({ "Member" })
    public List<CatalogSkill> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a list of all catalog skills with matching title.
     *
     * @param pattern The requested title pattern.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog skills.
     * @since 1.0
     */
//    @RolesAllowed({ "Member" })
    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

    /**
     * Returns a list of all catalog credentials given a skill id.
     *
     * @param skill_id The requested skill id.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog credentials.
     * @since 1.0
     */
//    @RolesAllowed({ "Member" })
    public List<CatalogCredential> findSkillCredentials(@NotNull UUID skill_id, String sort, Integer offset,
    Integer limit) {
        return repo.findSkillCredentials(skill_id, sort, offset, limit);
    }

    /**
     * Inserts a new credential given a skill id.
     *
     * @param skill_id The requested skill id.
     * @param credential_id The requested credential id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void insertSkillCredential(@NotNull UUID skill_id, @NotNull UUID credential_id) {
        repo.insertSkillCredential(skill_id, credential_id);
    }

    /**
     * Deletes an existing credential given a skill id.
     *
     * @param skill_id The requested skill id.
     * @param credential_id The requested credential id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void deleteSkillCredential(@NotNull UUID skill_id, @NotNull UUID credential_id) {
        repo.deleteSkillCredential(skill_id, credential_id);
    }

    /**
     * Returns a count of catalog skills.
     *
     * @return The count.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

//    @RolesAllowed({ "Admin" })
    public Integer test() {
        log.info("test:");
        conf.test();
        feat.test();
        evnt.test();
        return 0;
    }
}
