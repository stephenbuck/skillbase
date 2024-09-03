package com.headspin.skillbase.catalog.app;

import jakarta.inject.Inject;

/*
 * CatalogAppHome.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public class CatalogAppHome {

    private final CatalogAppConfig config;
    private final CatalogAppControl control;
    private final CatalogAppEvents events;
    private final CatalogAppFeatures features;
    private final CatalogAppHealth health;
    private final CatalogAppTelemetry telemetry;

    @Inject
    public CatalogAppHome(
        final CatalogAppConfig config,
        final CatalogAppControl control,
        final CatalogAppEvents events,
        final CatalogAppFeatures features,
        final CatalogAppHealth health,
        final CatalogAppTelemetry telemetry
    ) {
        this.config = config;
        this.control = control;
        this.events = events;
        this.features = features;
        this.health = health;
        this.telemetry = telemetry;
    }
}
