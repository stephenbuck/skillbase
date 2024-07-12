package com.headspin.skillbase.workflow.infrastructure.engine;

import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.ws.rs.client.Client;

import lombok.extern.slf4j.Slf4j;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
@ApplicationScoped
public class WorkflowEngineProviderFlowable implements WorkflowEngineProvider {

    private class DeploymentResp {
    }

    private class ProcessDefinitionResp {
    }

    private class ProcessInstanceResp {
    }

    private Client client = ClientBuilder.newClient();
    private WebTarget targetService;
    private WebTarget targetRepository;
    private WebTarget targetDeployments;
    private WebTarget targetModels;
    private WebTarget targetProcessDefinitions;
    private WebTarget targetRuntime;
    private WebTarget targetProcessInstances;
    private WebTarget targetTasks;

    private String username = "rest-admin";
    private String password = "test";
    private String userpass = username + ":" + password;
    private String basicAuth;

    public WorkflowEngineProviderFlowable() {

        client = ClientBuilder.newClient();

        targetService = client.target("http://172.17.0.1:8081/flowable-rest/service");

        targetRepository = targetService.path("repository");
        targetDeployments = targetRepository.path("deployments");
        targetModels = targetRepository.path("models");
        targetProcessDefinitions = targetRepository.path("process-definitions");

        targetRuntime = targetService.path("runtime");
        targetProcessInstances = targetRuntime.path("process-instances");
        targetTasks = targetRuntime.path("tasks");

        basicAuth = "Basic " + Base64.getEncoder().encodeToString(userpass.getBytes());
    }

    private JsonObject createDeployment(String resourceName) {
        try {

            List<EntityPart> parts = Arrays.asList(
                    EntityPart.withName("file")
                            .fileName(resourceName)
                            .content(this.getClass().getResourceAsStream(resourceName))
                            .build());

            GenericEntity<List<EntityPart>> genericEntity = new GenericEntity<>(parts) {
            };

            var entity = Entity.entity(genericEntity, MediaType.MULTIPART_FORM_DATA);

            Response resp = targetDeployments
                    .request(MediaType.MULTIPART_FORM_DATA)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, basicAuth)
                    .post(entity);

            String respStr = resp.readEntity(String.class);
            JsonReader respReader = Json.createReader(new StringReader(respStr));
            JsonObject respObject = respReader.readObject();

            return respObject;
        }

        catch (Exception e) {
            log.info(String.valueOf(e));
            return null;
        }
    }

    private JsonObject listDeployments() {
        try {
            Response resp = targetDeployments
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, basicAuth)
                    .get();

            String respStr = resp.readEntity(String.class);
            JsonReader respReader = Json.createReader(new StringReader(respStr));
            JsonObject respObject = respReader.readObject();

            return respObject;
        } catch (Exception e) {
            log.info(String.valueOf(e));
            throw e;
        }
    }

    private JsonObject listModels(String deploymentId) {
        try {
            Response resp = targetModels
                    .queryParam("deploymentId", deploymentId)
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, basicAuth)
                    .get();

            String respStr = resp.readEntity(String.class);
            JsonReader respReader = Json.createReader(new StringReader(respStr));
            JsonObject respObject = respReader.readObject();

            return respObject;
        } catch (Exception e) {
            log.info(String.valueOf(e));
            throw e;
        }
    }

    private JsonObject listProcessDefinitions(String deploymentId) {
        try {

            Response resp = targetProcessDefinitions
                    .queryParam("deploymentId", deploymentId)
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, basicAuth)
                    .get();

            String respStr = resp.readEntity(String.class);
            JsonReader respReader = Json.createReader(new StringReader(respStr));
            JsonObject respObject = respReader.readObject();

            return respObject;
        } catch (Exception e) {
            log.info(String.valueOf(e));
            throw e;
        }
    }

    private JsonObject listProcessInstances(String pid) {
        try {
            Response resp = targetProcessInstances
                    .queryParam("processDefinitionId", pid)
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, basicAuth)
                    .get();

            String respStr = resp.readEntity(String.class);
            JsonReader respReader = Json.createReader(new StringReader(respStr));
            JsonObject respObject = respReader.readObject();

            return respObject;
        } catch (Exception e) {
            log.info(String.valueOf(e));
            throw e;
        }
    }

    private JsonObject startProcessInstance(String pdid) {
        try {

            JsonObjectBuilder jvb = Json.createObjectBuilder();
            jvb.add("name", "holiday");
            jvb.add("employee", "admin");
            jvb.add("nrOfHolidays", "3");
            jvb.add("description", "beach");

            JsonArrayBuilder jab = Json.createArrayBuilder();
            jab.add(jvb.build());

            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("processDefinitionId", pdid);
            // job.add("processDefinitionKey", "holidayRequest");
            job.add("returnVariables", true);
            job.add("variables", jab.build());

            JsonObject jvp = job.build();

            var start = Entity.json(jvp.toString());

            Response resps = targetProcessInstances
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, basicAuth)
                    .post(start);

            String respStr = resps.readEntity(String.class);

            JsonReader respReader = Json.createReader(new StringReader(respStr));
            JsonObject respObject = respReader.readObject();

            return respObject;
        }

        catch (Exception e) {
            log.info(String.valueOf(e));
            throw e;
        }
    }

    private JsonObject listTasks(String piid) {
        try {
            Response resp = targetTasks
                    // / .queryParam("processInstanceId", piid)
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, basicAuth)
                    .get();

            String respStr = resp.readEntity(String.class);
            JsonReader respReader = Json.createReader(new StringReader(respStr));
            JsonObject respObject = respReader.readObject();

            return respObject;
        }

        catch (Exception e) {
            log.info(String.valueOf(e));
            throw e;
        }
    }

    private void claimTask() {

    }

    private void completeTask() {

    }

    private void resolveTask() {

    }

    private void delegateTask() {

    }

    private void testRest(String resourceName) {

        try {
            log.info("================================\n");

            JsonObject createResp = createDeployment(resourceName);
            log.info("createResp = {}", createResp);

            String did = createResp.getString("id");
            log.info("did = {}", did);

            log.info("================================\n");

            JsonObject listDeploymentsResp = listDeployments();
            log.info("listDeploymentsResp = {}", listDeploymentsResp);

            log.info("================================\n");

            JsonObject listModelsResp = listModels(did);
            log.info("listModelsResp = {}", listModelsResp);

            log.info("================================\n");

            JsonObject listProcessDefinitionsResp = listProcessDefinitions(did);
            log.info("listProcessDefinitionsResp = {}", listProcessDefinitionsResp);

            JsonArray pdlist = listProcessDefinitionsResp.getJsonArray("data");
            log.info("pdlist = {}", pdlist);

            JsonObject pdobj = pdlist.getJsonObject(0);
            log.info("pdobj = {}", pdobj);

            String pdid = pdobj.getString("id");
            log.info("resp pdid = {}", pdid);

            log.info("================================\n");

            JsonObject listProcessInstancesResp = listProcessInstances(pdid);
            log.info("listProcessInstancesResp = {}", listProcessInstancesResp);

            log.info("================================\n");

            JsonObject startProcessInstanceResp = startProcessInstance(pdid);
            log.info("startProcessInstanceResp = {}", startProcessInstanceResp);

            String piid = startProcessInstanceResp.getString("id");
            log.info("piid = {}", piid);

            log.info("================================\n");

            JsonObject listProcessInstancesRespAfter = listProcessInstances(pdid);
            log.info("listProcessInstancesRespAfter = {}", listProcessInstancesRespAfter);

            log.info("================================\n");

            JsonObject listTasksResp = listTasks(piid);
            log.info("listTasksResp = {}", listTasksResp);

            log.info("================================\n");

            JsonArray tklist = listTasksResp.getJsonArray("data");
            log.info("tklist = {}", tklist);

            JsonObject tkobj = tklist.getJsonObject(0);
            log.info("tkobj = {}", tkobj);

            String tkid = tkobj.getString("id");
            log.info("resp tkid = {}", tkid);

            log.info("================================\n");

        } catch (Exception e) {
            log.info(String.valueOf(e));
        } finally {
            // client.close();
        }

    }

    @Override
    public void test() {
        testRest("/test.bpmn20.bpmn");
    }
}
