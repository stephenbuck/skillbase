package com.headspin.skillbase.skill.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.skill.domain.Skill;
import com.headspin.skillbase.skill.domain.SkillEvent;
import com.headspin.skillbase.skill.domain.SkillRepo;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Stateless
@DeclareRoles({"Admin", "User"})
public class SkillService {

    @Inject
    private SkillRepo repo;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({"Admin"})
    void insert(
            @NotNull @Valid Skill skill) {
        repo.insert(skill);
        SkillEvent.build("com.headspin.skillbase.skill.inserted");
    }

    @Transactional
    @RolesAllowed({"Admin"})
    Skill update(
            @NotNull @Valid Skill skill) {
        Skill updated = repo.update(skill);
        SkillEvent.build("com.headspin.skillbase.skill.updated");
        return updated;
    }

    @Transactional
    void delete(
            @NotNull @Valid Skill skill) {
        repo.delete(skill);
        SkillEvent.build("com.headspin.skillbase.skill.deleted");
    }

    @Transactional
    @RolesAllowed({"Admin"})
    public void deleteById(
            @NotNull UUID id) {
        repo.deleteById(id);
        SkillEvent.build("com.headspin.skillbase.skill.deleted");
    }

    @RolesAllowed({"Admin"})
    public Optional<Skill> findById(
            @NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({"Admin"})
    public List<Skill> findAll(
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({"Admin"})
    public List<Skill> findAllByTitleLike(
            @NotNull String pattern,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

    @RolesAllowed({"Admin"})
    public List<Skill> findAllByCategoryId(
            @NotNull UUID categoryId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByCategoryId(categoryId, sort, offset, limit);
    }
}
