package com.headspin.skillbase.workflow.app;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.workflow.interfaces.rest.WorkflowDefinitionsREST;
import com.headspin.skillbase.workflow.interfaces.rest.WorkflowDeploymentsREST;
import com.headspin.skillbase.workflow.interfaces.rest.WorkflowInstancesREST;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Application for the workflow service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@ApplicationPath("/workflow")
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class WorkflowApp extends Application {

    @Inject
    private JsonWebToken jwt;

    private final WorkflowAppConfig config;
    private final WorkflowAppControl control;
    private final WorkflowAppEvents events; 
    private final WorkflowAppFeatures features; 
    private final WorkflowAppHealth health; 
    private final WorkflowAppMetrics metrics;
    private final WorkflowAppTelemetry telemetry; 

    public WorkflowApp() {
        this.config = new WorkflowAppConfig();
        this.control = new WorkflowAppControl();
        this.events = new WorkflowAppEvents();
        this.features = new WorkflowAppFeatures();
        this.health = new WorkflowAppHealth();
        this.metrics = new WorkflowAppMetrics();
        this.telemetry = new WorkflowAppTelemetry();
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(WorkflowApp.class);
        classSet.add(WorkflowDeploymentsREST.class);
        classSet.add(WorkflowDefinitionsREST.class);
        classSet.add(WorkflowInstancesREST.class);
        return classSet;
    }
}
