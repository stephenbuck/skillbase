package com.headspin.skillbase.identity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.auto.AddPackages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.headspin.skillbase.identity.domain.IdentityGroupRepo;
import com.headspin.skillbase.identity.domain.IdentityRoleRepo;
import com.headspin.skillbase.identity.domain.IdentityUserRepo;
import com.headspin.skillbase.identity.infrastructure.jpa.IdentityGroupRepoJPA;
import com.headspin.skillbase.identity.infrastructure.jpa.IdentityRoleRepoJPA;
import com.headspin.skillbase.identity.infrastructure.jpa.IdentityUserRepoJPA;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Disabled

@Slf4j
@ApplicationScoped
@ExtendWith(WeldJunit5Extension.class)
@AddPackages(IdentityGroupRepo.class)
@AddPackages(IdentityRoleRepo.class)
@AddPackages(IdentityUserRepo.class)
@AddPackages(IdentityGroupRepoJPA.class)
@AddPackages(IdentityRoleRepoJPA.class)
@AddPackages(IdentityUserRepoJPA.class)
public class IdentityRepoTest {

    // @Inject
    // private IdentityUserRepo userRepo;
    private IdentityUserRepoJPA userRepo = new IdentityUserRepoJPA();

    // @Inject
    // private IdentityGroupRepo groupRepo;

    // @Inject
    // private IdentityRoleRepo roleRepo;

    @BeforeAll
    public static void beforeAll() {
        log.info("repo");
    }

    @Test
    void testJPA() {
        // assertNotNull(groupRepo, "JPA group repo not found");
        // assertNotNull(roleRepo, "JPA role repo not found");
        assertNotNull(userRepo, "JPA user repo not found");
    }

    @Test
    void testMain() {
        log.info("repo");
        // log.info("count = {}", userRepo.count());
    }
}