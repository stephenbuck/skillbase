package com.headspin.skillbase.identity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.auto.AddPackages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.headspin.skillbase.identity.interfaces.service.IdentityGroupService;
import com.headspin.skillbase.identity.interfaces.service.IdentityRoleService;
import com.headspin.skillbase.identity.interfaces.service.IdentityUserService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Disabled

@Slf4j
@ApplicationScoped
@ExtendWith(WeldJunit5Extension.class)
@AddPackages(IdentityGroupService.class)
@AddPackages(IdentityRoleService.class)
@AddPackages(IdentityUserService.class)
public class IdentityServiceTest {

    // @Inject
    private IdentityGroupService groupService;

    // @Inject
    private IdentityUserService userService;

    // @Inject
    private IdentityRoleService roleService;

    @BeforeAll
    public static void beforeAll() {
        log.info("unit");
    }

    @Test
    void testService() {
        assertNotNull(groupService, "Group service not found");
        assertNotNull(roleService, "Role service not found");
        assertNotNull(userService, "User service not found");
    }

    @Test
    void testMain() {
        log.info("service");
    }
}