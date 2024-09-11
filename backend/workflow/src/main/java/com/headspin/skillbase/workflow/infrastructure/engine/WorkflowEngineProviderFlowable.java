package com.headspin.skillbase.workflow.infrastructure.engine;

import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowTask;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceBuilder;
import org.flowable.task.api.Task;

/**
 * Flowable implementation of workflow engine provider interface.
 * 
 * Mappings:
 * 
 * WorkflowDeployment.peer_id is a Flowable deployment_id.
 * WorkflowDefinition.peer_id is a Flowable definition_id.
 * WorkflowInstance.peer_id is a Flowable instance_definition_id.
 * WorkflowInstance.peer_id is a Flowable instance_instance_id.
 * WorkflowTask.peer_id is a Flowable task_id.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowEngineProviderFlowable implements WorkflowEngineProvider {

    private final ProcessEngineConfiguration config;
    private final ProcessEngine engine;
    private final RepositoryService repository;
    private final RuntimeService runtime;
    private final TaskService task;

    @Inject
    public WorkflowEngineProviderFlowable(
        @ConfigProperty(name = "com.headspin.skillbase.workflow.engine.flowable.jdbc.url") String configJdbcUrl,
        @ConfigProperty(name = "com.headspin.skillbase.workflow.engine.flowable.jdbc.username") String configJdbcUsername,
        @ConfigProperty(name = "com.headspin.skillbase.workflow.engine.flowable.jdbc.password") String configJdbcPassword,
        @ConfigProperty(name = "com.headspin.skillbase.workflow.engine.flowable.jdbc.driver") String configJdbcDriver
    ) {
        this.config = new StandaloneProcessEngineConfiguration()
            .setJdbcUrl(configJdbcUrl)
            .setJdbcUsername(configJdbcUsername)
            .setJdbcPassword(configJdbcPassword)
            .setJdbcDriver(configJdbcDriver)
            .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        this.engine = config.buildProcessEngine();
        this.repository = engine.getRepositoryService();
        this.runtime = engine.getRuntimeService();
        this.task = engine.getTaskService();
    }

    @Override
    @Transactional
    public String insertDefinition(WorkflowDefinition definition) {

        String deploymentId = "";

        ProcessDefinition peerDefinition = repository.createProcessDefinitionQuery()
                .deploymentId(null) // BOZO
                .singleResult();
        log.info("peerDefinition = {}", peerDefinition.getName());

        return peerDefinition.getId();
    }

    @Override
    public void updateDefinition(WorkflowDefinition definition) {
    }

    @Override
    public void deleteDefinition(UUID id) {
    }

    /**
     * Creates a Flowable deployment based on a Skillbase deployment.
     * 
     * This method is transactional so that it can be combined with the
     * insertion of the Skillbase deployment into the database.
     * 
     * The steps are:
     * 1) Create an in-memory JAR (aka ZIP) file containing the BPMN file.
     * 2) Create a Flowable deployment with:
     *    - The Skillbase deployment title as the name
     *    - The Skillbase deployment id as the key
     *    - The BAR file contents
     * 
     * @param deployment The Skillable deployment
     * @return the Flowable deployment ID
     */
    @Override
    @Transactional
    public String insertDeployment(WorkflowDeployment deployment) {

        try {

            // Create an output stream for the Zip "file" bytes
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(bos);

            // Add the deployment BPMN as an entry
            ZipEntry ze = new ZipEntry(deployment.title);
            zos.putNextEntry(ze);
            zos.write(bos.toByteArray(), 0, bos.size());
    
            // Finish the Zip stream
            zos.finish();
    
            // Create an input stream from the Zip "file" bytes
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ZipInputStream zip = new ZipInputStream(bis);

            // Create a Flowable deployment with the Zip "file" and our ID as the key
            Deployment peerDeployment = repository.createDeployment()
                .name(deployment.title)
                .key(String.valueOf(deployment.deployment_id))
                .addZipInputStream(zip)
                .deploy();
            log.info("peerDeployment = {}", peerDeployment.getId());

            return peerDeployment.getId();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void updateDeployment(WorkflowDeployment deployment) {

    }

    @Override
    @Transactional
    public void deleteDeployment(UUID id) {

        Deployment peerDeployment = repository
            .createDeploymentQuery()
               .deploymentKey(String.valueOf(id))
               .singleResult();

        repository.deleteDeployment(peerDeployment.getId(), true);
    }

    @Override
    @Transactional
    public String startProcess(UUID id) {

        // ProcessDefinition definition = repository.getProcessDefinition(String.valueOf(id));

        ProcessInstance instance = runtime.createProcessInstanceBuilder()
            .processDefinitionKey(String.valueOf(id))
            .name(String.valueOf(id))
//            .variables()
            .start();

        return instance.getId();
    }

   
    @Override
    public String insertInstance(WorkflowInstance instance) {
        return null;
    }

    @Override
    public void updateInstance(WorkflowInstance instance) {
    }

    @Override
    public void deleteInstance(UUID id) {
        String peerId = String.valueOf(id);
        runtime.deleteProcessInstance(peerId, "");
    }

    @Override
    public String insertTask(WorkflowTask task) {
        return null;
    }

    @Override
    public void updateTask(WorkflowTask task) {
    }

    @Override
    public void deleteTask(UUID id) {
        String peerId = String.valueOf(id);
        task.deleteTask(peerId, "");
    }

    @Override
    public void test() {

        log.info("test");

        Deployment deployment = repository.createDeployment()
                .key("FOO")
                .addClasspathResource("test.bpmn20.xml")
                .deploy();
        log.info("deployment = {}", deployment.getName());
        ProcessDefinition definition = repository.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        log.info("definition = {}", definition.getName());

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", "Steve");
        variables.put("nrOfHolidays", 3);
        variables.put("description", "Burned Out");
        ProcessInstance instance = runtime.startProcessInstanceByKey("FOO", variables);
        log.info("instance = {}", instance.getName());

        List<Task> tasks = task.createTaskQuery().taskCandidateGroup("managers").list();
        log.info("You have {} tasks", tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            log.info((i + 1) + ") " + tasks.get(i).getName());
        }
    }
}
