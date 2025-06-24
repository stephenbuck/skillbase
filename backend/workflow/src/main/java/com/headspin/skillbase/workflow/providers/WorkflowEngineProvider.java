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

    String insertDefinition(WorkflowDefinition definition);

    void updateDefinition(WorkflowDefinition definition);

    void deleteDefinition(UUID id);

    String insertDeployment(WorkflowDeployment deployment);

    void updateDeployment(WorkflowDeployment deployment);

    void deleteDeployment(UUID id);

    String startProcess(UUID id);

    String insertInstance(WorkflowInstance instance);

    void updateInstance(WorkflowInstance instance);

    void deleteInstance(UUID id);

    String insertTask(WorkflowTask task);

    void updateTask(WorkflowTask task);

    void deleteTask(UUID id);

    void test();
}
