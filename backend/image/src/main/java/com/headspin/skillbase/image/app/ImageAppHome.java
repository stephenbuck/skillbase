package com.headspin.skillbase.image.app;

import jakarta.inject.Inject;

/*
 * ImageAppHome.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public class ImageAppHome{

    private final ImageAppConfig config;
    private final ImageAppControl control;
    private final ImageAppEvents events;
    private final ImageAppFeatures features;
    private final ImageAppHealth health;
    private final ImageAppTelemetry telemetry;

    @Inject
    public ImageAppHome(
        final ImageAppConfig config,
        final ImageAppControl control,
        final ImageAppEvents events,
        final ImageAppFeatures features,
        final ImageAppHealth health,
        final ImageAppTelemetry telemetry
    ) {
        this.config = config;
        this.control = control;
        this.events = events;
        this.features = features;
        this.health = health;
        this.telemetry = telemetry;
    }
}
