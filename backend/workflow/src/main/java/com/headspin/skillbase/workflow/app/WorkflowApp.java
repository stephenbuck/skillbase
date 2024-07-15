package com.headspin.skillbase.workflow.app;

import java.util.HashSet;
import java.util.Set;

import com.headspin.skillbase.workflow.interfaces.rest.WorkflowDefinitionsREST;
import com.headspin.skillbase.workflow.interfaces.rest.WorkflowDeploymentsREST;
import com.headspin.skillbase.workflow.interfaces.rest.WorkflowInstancesREST;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Application for the workflow service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@ApplicationPath("/workflow")
public class WorkflowApp extends Application {

    public WorkflowApp() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(WorkflowApp.class);
        classSet.add(WorkflowAppHealth.class);
        classSet.add(WorkflowDeploymentsREST.class);
        classSet.add(WorkflowDefinitionsREST.class);
        classSet.add(WorkflowInstancesREST.class);
        return classSet;
    }
}
