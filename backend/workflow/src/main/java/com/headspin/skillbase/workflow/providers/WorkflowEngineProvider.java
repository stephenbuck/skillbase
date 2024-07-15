package com.headspin.skillbase.workflow.providers;

import java.util.UUID;

import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.domain.WorkflowTask;

/**
 * Workflow engine provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

public interface WorkflowEngineProvider {

    public UUID insertDefinition(WorkflowDefinition definition);
    public boolean updateDefinition(WorkflowDefinition definition);
    public boolean deleteDefinition(UUID id);

    public UUID insertDeployment(WorkflowDeployment deployment);
    public boolean updateDeployment(WorkflowDeployment deployment);
    public boolean deleteDeployment(UUID id);

    public UUID insertInstance(WorkflowInstance instance);
    public boolean updateInstance(WorkflowInstance instance);
    public boolean deleteInstance(UUID id);

    public UUID insertTask(WorkflowTask task);
    public boolean updateTask(WorkflowTask task);
    public boolean deleteTask(UUID id);

    public void test();

}
