package com.headspin.skillbase.workflow.app;
    
import jakarta.inject.Inject;

/**
 * WorkflowAppHome.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public class WorkflowAppHome{

    private final WorkflowAppConfig config;
    private final WorkflowAppControl control;
    private final WorkflowAppEvents events; 
    private final WorkflowAppFeatures features; 
    private final WorkflowAppHealth health; 
    private final WorkflowAppTelemetry telemetry; 

    @Inject
    public WorkflowAppHome(
        final WorkflowAppConfig config,
        final WorkflowAppControl control,
        final WorkflowAppEvents events,
        final WorkflowAppFeatures features, 
        final WorkflowAppHealth health,
        final WorkflowAppTelemetry telemetry 
    ) {
        this.config = config;
        this.control = control;
        this.events = events;
        this.features = features;
        this.health = health;
        this.telemetry = telemetry;
    }
}
