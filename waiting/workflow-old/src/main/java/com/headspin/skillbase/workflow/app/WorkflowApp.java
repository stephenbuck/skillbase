package com.headspin.groupbase.workflow.app;

import java.util.HashSet;
import java.util.Set;

import com.headspin.groupbase.workflow.interfaces.rest.WorkflowCertsREST;
import com.headspin.groupbase.workflow.interfaces.rest.WorkflowModelsREST;
import com.headspin.groupbase.workflow.interfaces.rest.WorkflowProcessesREST;
import com.headspin.groupbase.workflow.interfaces.rest.WorkflowTasksREST;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/*
 * WorkflowApp is the main entry point.
 */

@ApplicationPath("workflow")
public class WorkflowApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(WorkflowCertsREST.class);
        classSet.add(WorkflowModelsREST.class);
        classSet.add(WorkflowProcessesREST.class);
        classSet.add(WorkflowTasksREST.class);
        return classSet;
    }
}
