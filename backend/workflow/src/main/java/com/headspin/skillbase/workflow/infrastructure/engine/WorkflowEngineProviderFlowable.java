package com.headspin.skillbase.workflow.infrastructure.engine;

import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowTask;
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
import java.util.UUID;

/**
 * Flowable implementation of workflow engine provider interface.
 * 
 * Mappings:
 * 
 *   WorkflowDeployment.peer_id is a Flowable deployment_id.
 *   WorkflowDefinition.peer_id is a Flowable definition_id.
 *   WorkflowInstance.peer_id is a Flowable instance_definition_id.
 *   WorkflowInstance.peer_id is a Flowable instance_instance_id.
 *   WorkflowTask.peer_id is a Flowable task_id.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowEngineProviderFlowable implements WorkflowEngineProvider {

    private final Client client = ClientBuilder.newClient();
    private final WebTarget targetService;
    private final WebTarget targetRepository;
    private final WebTarget targetDeployments;
    private final WebTarget targetDefinitions;
    private final WebTarget targetInstanceDefinitions;
    private final WebTarget targetRuntime;
    private final WebTarget targetInstanceInstances;
    private final WebTarget targetTasks;

    private final String username = "rest-admin";
    private final String password = "test";
    private final String userpass = username + ":" + password;
    private final String basicAuth;

    public WorkflowEngineProviderFlowable() {

        this.targetService = client.target("http://flowable:8081/flowable-rest/service");

        this.targetRepository = targetService.path("repository");
        this.targetDeployments = targetRepository.path("deployments");
        this.targetDefinitions = targetRepository.path("definitions");
        this.targetInstanceDefinitions = targetRepository.path("instance-definitions");

        this.targetRuntime = targetService.path("runtime");
        this.targetInstanceInstances = targetRuntime.path("instance-instances");
        this.targetTasks = targetRuntime.path("tasks");

        this.basicAuth = "Basic " + Base64.getEncoder().encodeToString(userpass.getBytes());
    }

    /*
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

    private JsonObject listDefinitions(String deploymentId) {
        try {
            Response resp = targetDefinitions
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

    private JsonObject listInstanceDefinitions(String deploymentId) {
        try {

            Response resp = targetInstanceDefinitions
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

    private JsonObject listInstanceInstances(String pid) {
        try {
            Response resp = targetInstanceInstances
                    .queryParam("instanceDefinitionId", pid)
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

    private JsonObject startInstanceInstance(String pdid) {
        try {

            JsonObjectBuilder jvb = Json.createObjectBuilder();
            jvb.add("name", "holiday");
            jvb.add("employee", "admin");
            jvb.add("nrOfHolidays", "3");
            jvb.add("description", "beach");

            JsonArrayBuilder jab = Json.createArrayBuilder();
            jab.add(jvb.build());

            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("instanceDefinitionId", pdid);
            // job.add("instanceDefinitionKey", "holidayRequest");
            job.add("returnVariables", true);
            job.add("variables", jab.build());

            JsonObject jvp = job.build();

            var start = Entity.json(jvp.toString());

            Response resps = targetInstanceInstances
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
                    // / .queryParam("instanceInstanceId", piid)
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

            JsonObject listDefinitionsResp = listDefinitions(did);
            log.info("listDefinitionsResp = {}", listDefinitionsResp);

            log.info("================================\n");

            JsonObject listInstanceDefinitionsResp = listInstanceDefinitions(did);
            log.info("listInstanceDefinitionsResp = {}", listInstanceDefinitionsResp);

            JsonArray pdlist = listInstanceDefinitionsResp.getJsonArray("data");
            log.info("pdlist = {}", pdlist);

            JsonObject pdobj = pdlist.getJsonObject(0);
            log.info("pdobj = {}", pdobj);

            String pdid = pdobj.getString("id");
            log.info("resp pdid = {}", pdid);

            log.info("================================\n");

            JsonObject listInstanceInstancesResp = listInstanceInstances(pdid);
            log.info("listInstanceInstancesResp = {}", listInstanceInstancesResp);

            log.info("================================\n");

            JsonObject startInstanceInstanceResp = startInstanceInstance(pdid);
            log.info("startInstanceInstanceResp = {}", startInstanceInstanceResp);

            String piid = startInstanceInstanceResp.getString("id");
            log.info("piid = {}", piid);

            log.info("================================\n");

            JsonObject listInstanceInstancesRespAfter = listInstanceInstances(pdid);
            log.info("listInstanceInstancesRespAfter = {}", listInstanceInstancesRespAfter);

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
        */

    @Override
    public UUID insertDefinition(WorkflowDefinition definition) {
        return null;
    }

    @Override
    public boolean updateDefinition(WorkflowDefinition definition) {
        return true;
    }

    @Override
    public boolean deleteDefinition(UUID id) {
        return true;
    }


    @Override
    public UUID insertDeployment(WorkflowDeployment deployment) {
        return null;
    }

    @Override
    public boolean updateDeployment(WorkflowDeployment deployment) {
        return true;
    }

    @Override
    public boolean deleteDeployment(UUID id) {
        return true;
    }


    @Override
    public UUID insertInstance(WorkflowInstance instance) {
        return null;
    }

    @Override
    public boolean updateInstance(WorkflowInstance instance) {
        return true;
    }

    @Override
    public boolean deleteInstance(UUID id) {
        return true;
    }


    @Override
    public UUID insertTask(WorkflowTask task) {
        return null;
    }

    @Override
    public boolean updateTask(WorkflowTask task) {
        return true;
    }

    @Override
    public boolean deleteTask(UUID id) {
        return true;
    }


    @Override
    public void test() {
        log.info("test");
//        testRest("/test.bpmn20.bpmn");
    }
}
