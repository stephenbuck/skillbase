package com.headspin.skillbase.workflow.infrastructure.flowable;

import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Client;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
public class WorkflowEngineProviderFlowable implements WorkflowEngineProvider {

    public WorkflowEngineProviderFlowable() {
    }

    @Override
    public void test() {

        log.info("TEST TEST TEST");

try {

        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://172.17.0.1:8081/flowable-rest");
        WebTarget info = base.path("service/repository/deployments");

        String credentials = "rest-admin:test";
        String base64encoded = Base64.getEncoder().encodeToString(credentials.getBytes());

        String result = info
//            .path("{id}")
//            .queryParam("foo", "bar")
            .request(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "Basic " + base64encoded)
            .get(String.class);


        log.info("================================");
        log.info("result = {}", result);
        log.info("================================");
}
catch (Exception e) {
    log.info(String.valueOf(e));
}






        /*

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
        */
    }
}
