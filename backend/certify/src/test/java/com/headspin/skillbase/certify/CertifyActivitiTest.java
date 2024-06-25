package com.headspin.skillbase.certify;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

@Slf4j
public class CertifyActivitiTest {

    @Test
    void demoTestMethod() {

        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        log.info("processEngineConfiguration = {}", processEngineConfiguration);
        assertNotNull(processEngineConfiguration);

        ProcessEngine processEngine = processEngineConfiguration
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcUrl("jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000").buildProcessEngine();
        log.info("processEngine = {}", processEngine);
        assertNotNull(processEngine);

        RepositoryService repoService = processEngine.getRepositoryService();
        log.info("repoService = {}", repoService);
        assertNotNull(repoService);

        Deployment deployment = repoService.createDeployment().addClasspathResource("certify.bpmn20.xml").deploy();
        log.info("deployment = {}", deployment);
        assertNotNull(deployment);

        RuntimeService runtimeService = processEngine.getRuntimeService();
        log.info("runtimeService = {}", runtimeService);
        assertNotNull(runtimeService);

        Map<String, Object> variables = new HashMap<>();
        variables.put("employeeName", "John");
        variables.put("numberOfDays", 4);
        variables.put("vacationMotivation", "I need a break!");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("certify", variables);
        log.info("processInstance = {}", processInstance);
        assertNotNull(processInstance);

        TaskService taskService = processEngine.getTaskService();
        log.info("taskService = {}", taskService);
        assertNotNull(taskService);

        Long count = runtimeService.createProcessInstanceQuery().count();
        assertTrue(count >= 1);
    }

}