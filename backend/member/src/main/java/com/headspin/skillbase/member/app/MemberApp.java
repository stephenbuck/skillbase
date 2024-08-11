package com.headspin.skillbase.member.app;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.member.interfaces.rest.MemberUsersREST;
import com.headspin.skillbase.common.app.AppBase;
import com.headspin.skillbase.member.interfaces.rest.MemberAchievementsREST;
import com.headspin.skillbase.member.interfaces.rest.MemberGroupsREST;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;

/**
 * MemberApp is the main entry point.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@ApplicationPath("/member")
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class MemberApp extends AppBase {

    @Inject
    private JsonWebToken jwt;
    
    private final MemberAppConfig config;
    private final MemberAppControl control;
    private final MemberAppEvents events;
    private final MemberAppFeatures features;
    private final MemberAppHealth health;
    private final MemberAppMetrics metrics;
    private final MemberAppTelemetry telemetry;

    public MemberApp() {
        this.config = new MemberAppConfig();
        this.control = new MemberAppControl();
        this.events = new MemberAppEvents();
        this.features = new MemberAppFeatures();
        this.health = new MemberAppHealth();
        this.metrics = new MemberAppMetrics();
        this.telemetry = new MemberAppTelemetry();
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(MemberApp.class);
        classSet.add(MemberAchievementsREST.class);
        classSet.add(MemberGroupsREST.class);
        classSet.add(MemberUsersREST.class);
        return classSet;
    }
}
