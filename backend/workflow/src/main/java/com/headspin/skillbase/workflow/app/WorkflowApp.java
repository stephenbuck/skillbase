package com.headspin.skillbase.workflow.app;

import java.util.HashSet;
import java.util.Set;

import com.headspin.skillbase.workflow.interfaces.rest.WorkflowModelsREST;
import com.headspin.skillbase.workflow.interfaces.rest.WorkflowProcessesREST;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/*
 * WorkflowApp is the main entry point.
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
        classSet.add(WorkflowModelsREST.class);
        classSet.add(WorkflowProcessesREST.class);
        return classSet;
    }
}
