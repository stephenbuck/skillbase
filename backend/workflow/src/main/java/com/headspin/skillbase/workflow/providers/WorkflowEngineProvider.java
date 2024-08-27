package com.headspin.skillbase.workflow.providers;

import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.domain.WorkflowTask;

import java.util.UUID;

/**
 * Workflow engine provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowEngineProvider {

    public String insertDefinition(WorkflowDefinition definition);
    public void updateDefinition(WorkflowDefinition definition);
    public void deleteDefinition(UUID id);
    public String insertDeployment(WorkflowDeployment deployment);
    public void updateDeployment(WorkflowDeployment deployment);
    public void deleteDeployment(UUID id);
    public String startProcess(UUID id);
    public String insertInstance(WorkflowInstance instance);
    public void updateInstance(WorkflowInstance instance);
    public void deleteInstance(UUID id);
    public String insertTask(WorkflowTask task);
    public void updateTask(WorkflowTask task);
    public void deleteTask(UUID id);
    public void test();
}
