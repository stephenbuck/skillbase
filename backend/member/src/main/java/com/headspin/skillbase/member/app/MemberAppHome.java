package com.headspin.skillbase.member.app;

import jakarta.inject.Inject;

/**
 * MemberAppHome.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public class MemberAppHome {

    private final MemberAppConfig config;
    private final MemberAppControl control;
    private final MemberAppEvents events;
    private final MemberAppFeatures features;
    private final MemberAppHealth health;
    private final MemberAppTelemetry telemetry;

    @Inject
    public MemberAppHome(
        final MemberAppConfig config,
        final MemberAppControl control,
        final MemberAppEvents events,
        final MemberAppFeatures features,
        final MemberAppHealth health,
        final MemberAppTelemetry telemetry        
    ) {
        this.config = config;
        this.control = control;
        this.events = events;
        this.features = features;
        this.health = health;
        this.telemetry = telemetry;
    }
}
