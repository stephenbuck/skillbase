package com.headspin.skillbase.catalog.app;

import com.headspin.skillbase.common.app.AppHealth;

/*
 * Application health for the catalog service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

// @Path("health")
// @Consumes({ MediaType.APPLICATION_JSON })
// @Produces({ MediaType.APPLICATION_JSON })
public class CatalogAppHealth extends AppHealth {

    public CatalogAppHealth() {
    }
/*
    @Produces
    @Readiness
    public HealthCheckResponse readiness() {
        return HealthCheckResponse.up("Catalog Readiness OK");
    }

    @Produces
    @Liveness
    public HealthCheckResponse liveness() {
        return HealthCheckResponse.up("Catalog Liveness OK");
    }

    @Produces
    @Startup
    public HealthCheckResponse startup() {
        return HealthCheckResponse.up("Catalog Startup OK");
    }
*/
}
