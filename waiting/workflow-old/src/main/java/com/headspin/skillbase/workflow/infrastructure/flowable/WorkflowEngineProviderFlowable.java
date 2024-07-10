package com.headspin.groupbase.workflow.infrastructure.flowable;

import com.headspin.groupbase.workflow.providers.WorkflowEngineProvider;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;

@Slf4j
@ApplicationScoped
public class WorkflowEngineProviderFlowable implements WorkflowEngineProvider {

    private ProcessEngineConfiguration config;
    private ProcessEngine processEngine;
    private RepositoryService repositoryService;

    public WorkflowEngineProviderFlowable() {
        log.info("workflow");

        config = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa").setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        processEngine = config.buildProcessEngine();

        repositoryService = processEngine.getRepositoryService();
    }

    @Override
    public void test() {

        Deployment deployment = repositoryService.createDeployment()
            .addClasspathResource("holiday-request.bpmn20.xml")
            .deploy();
        log.info("deployment = {}", deployment);

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment.getId())
            .singleResult();
        log.info("Found process definition : " + processDefinition.getName());

        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", "admin");
        variables.put("nrOfHolidays", "3");
        variables.put("description", "beach");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", variables);
        log.info("pi = {}", processInstance);

        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
            .taskCandidateGroup("managers")
            .list();
        log.info("You have " + tasks.size() + " tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            log.info((i + 1) + ") " + tasks.get(i).getName());
        }
    }
}
