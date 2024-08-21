package com.headspin.skillbase.storage.app;

import com.headspin.skillbase.common.app.AppHealth;

/*
 * Application health for the Storage service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

// @Path("health")
// @Consumes({ MediaType.APPLICATION_JSON })
// @Produces({ MediaType.APPLICATION_JSON })
public class StorageAppHealth extends AppHealth {

    public StorageAppHealth() {
    }
/*
    @Produces
    @Readiness
    public HealthCheckResponse readiness() {
        return HealthCheckResponse.up("Storage Readiness OK");
    }

    @Produces
    @Liveness
    public HealthCheckResponse liveness() {
        return HealthCheckResponse.up("Storage Liveness OK");
    }

    @Produces
    @Startup
    public HealthCheckResponse startup() {
        return HealthCheckResponse.up("Storage Startup OK");
    }
*/
}
