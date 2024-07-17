**Design:**

The workflow service is responsible for executing process definitions for
user achievement of skill credentials. The most common case is a user selects
a skill credential and starts a process instance to achieve it. The workflow
process is defined by BPMN and can be an arbitrarily complex combination of
user tasks, manual tasks, email message, etc. It results in a pass or fail
result. A pass result causes a user achievement to be created.


**Domain Entities:**

* WorkflowDeployment
* WorkflowDefinition
* WorkflowInstance
* WorkflowTask


**Domain Events:**

* SKILLBASE_WORKFLOW_DEFINITION_CREATED
* SKILLBASE_WORKFLOW_DEFINITION_DELETED
* SKILLBASE_WORKFLOW_DEFINITION_UPDATED

* SKILLBASE_WORKFLOW_DEPLOYMENT_CREATED
* SKILLBASE_WORKFLOW_DEPLOYMENT_DELETED
* SKILLBASE_WORKFLOW_DEPLOYMENT_UPDATED

* SKILLBASE_WORKFLOW_INSTANCE_CREATED
* SKILLBASE_WORKFLOW_INSTANCE_DELETED
* SKILLBASE_WORKFLOW_INSTANCE_UPDATED

* SKILLBASE_WORKFLOW_TASK_CREATED
* SKILLBASE_WORKFLOW_TASK_DELETED
* SKILLBASE_WORKFLOW_TASK_UPDATED

* SKILLBASE_WORKFLOW_PROCESS_STARTED
* SKILLBASE_WORKFLOW_PROCESS_STOPPED
* SKILLBASE_WORKFLOW_PROCESS_PASSED
* SKILLBASE_WORKFLOW_PROCESS_FAILED


**Subscribed Events:**

SKILLBASE_CATALOG_SKILL_CREATED:
In response to a skill created event, the workflow service creates a corresponding
workflow deployment entity.

SKILLBASE_CATALOG_SKILL_DELETED:
In response to a skill deleted event, the workflow service deletes its corresponding
workflow deployment entity.

SKILLBASE_CATALOG_SKILL_UPDATED:
In response to a skill updated event, the workflow service updates its corresponding
workflow deployment entity.


SKILLBASE_CATALOG_CREDENTIAL_CREATED:
In response to a credential created event, the workflow service creates a corresponding
workflow definition entity.

SKILLBASE_CATALOG_CREDENTIAL_DELETED:
In response to a credential deleted event, the workflow service deletes its corresponding
workflow definition entity.

SKILLBASE_CATALOG_CREDENTIAL_UPDATED:
In response to a credential updated event, the workflow service updates its corresponding
workflow definition entity.



See Backend / Design for common design elements.
