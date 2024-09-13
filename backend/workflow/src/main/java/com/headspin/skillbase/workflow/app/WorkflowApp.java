package com.headspin.skillbase.workflow.app;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.common.app.AppBase;
import com.headspin.skillbase.workflow.interfaces.rest.WorkflowDefinitionsREST;
import com.headspin.skillbase.workflow.interfaces.rest.WorkflowDeploymentsREST;
import com.headspin.skillbase.workflow.interfaces.rest.WorkflowInstancesREST;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;

/**
 * Application for the workflow service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@ApplicationPath("/workflow")
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
public class WorkflowApp extends AppBase {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private WorkflowAppHome home;

    public WorkflowApp() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classSet = new HashSet<>();
        classSet.add(WorkflowApp.class);
        classSet.add(WorkflowDeploymentsREST.class);
        classSet.add(WorkflowDefinitionsREST.class);
        classSet.add(WorkflowInstancesREST.class);
        return classSet;
    }
}
