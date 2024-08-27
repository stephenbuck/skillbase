package com.headspin.skillbase.image.app;

import com.headspin.skillbase.common.app.AppHealth;

/*
 * Application health for the Image service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

// @Path("health")
// @Consumes({ MediaType.APPLICATION_JSON })
// @Produces({ MediaType.APPLICATION_JSON })
public class ImageAppHealth extends AppHealth {

    public ImageAppHealth() {
    }
/*
    @Produces
    @Readiness
    public HealthCheckResponse readiness() {
        return HealthCheckResponse.up("Image Readiness OK");
    }

    @Produces
    @Liveness
    public HealthCheckResponse liveness() {
        return HealthCheckResponse.up("Image Liveness OK");
    }

    @Produces
    @Startup
    public HealthCheckResponse startup() {
        return HealthCheckResponse.up("Image Startup OK");
    }
*/
}
