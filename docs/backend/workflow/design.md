**Design:**

**Domain Entities:**

* WorkflowModel
* WorkflowProcess

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

**Subscribed Events:**

SKILLBASE_CATALOG_SKILL_CREATED:
In response to a skill created event, the workflow service creates the corresponding
workflow deployment entity.

SKILLBASE_CATALOG_SKILL_DELETED:
In response to a skill deleted event, the workflow service deletes the corresponding
workflow deployment entity.

SKILLBASE_CATALOG_SKILL_UPDATED:
In response to a skill updated event, the workflow service updates the corresponding
workflow deployment entity.


SKILLBASE_CATALOG_CREDENTIAL_CREATED:
In response to a credential created event, the workflow service creates the corresponding
workflow definition entity.

SKILLBASE_CATALOG_CREDENTIAL_DELETED:
In response to a credential deleted event, the workflow service deletes the corresponding
workflow definition entity.

SKILLBASE_CATALOG_CREDENTIAL_UPDATED:
In response to a credential updated event, the workflow service updates the corresponding
workflow definition entity.


SKILLBASE_MEMBER_PROCESS_CREATED:
SKILLBASE_MEMBER_PROCESS_DELETED:
SKILLBASE_MEMBER_PROCESS_UPDATED:

SKILLBASE_MEMBER_USER_CREATED:
SKILLBASE_MEMBER_USER_DELETED:
SKILLBASE_MEMBER_USER_UPDATED:


See Backend / Design for common design elements.
