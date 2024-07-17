package com.headspin.skillbase.member.app;

import com.headspin.skillbase.common.app.AppHealth;

// @Path("health")
// @Consumes({ MediaType.APPLICATION_JSON })
// @Produces({ MediaType.APPLICATION_JSON })
public class MemberAppHealth extends AppHealth {

    public MemberAppHealth() {
    }
/*
    @Produces
    @Readiness
    public HealthCheckResponse readiness() {
        return HealthCheckResponse.up("Member Readiness OK");
    }

    @Produces
    @Liveness
    public HealthCheckResponse liveness() {
        return HealthCheckResponse.up("Member Liveness OK");
    }

    @Produces
    @Startup
    public HealthCheckResponse startup() {
        return HealthCheckResponse.up("Member Startup OK");
    }
        */
}
