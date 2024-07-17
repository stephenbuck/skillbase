**Design:**

The catalog service is responsible for managing a set of categories, skills,
and credentials that can be achieved. Categories (e.g. Safety) are a free-form
hierarchy used to organize skills. Skills (e.g. CPR) are defined within a
category and have a corresponding deployment entity in the workflow service.
Credential (e.g. License) are associated with a Skill and have a corresponding
process definition in the workflow service. To achieve a credential, a user
follows its workflow process.


**Domain Entities:**

* CatalogCategory
* CatalogCredential
* CatalogSkill


**Domain Events:**

* SKILLBASE_CATALOG_CATEGORY_CREATED
* SKILLBASE_CATALOG_CATEGORY_DELETED
* SKILLBASE_CATALOG_CATEGORY_UPDATED

* SKILLBASE_CATALOG_CREDENTIAL_CREATED
* SKILLBASE_CATALOG_CREDENTIAL_DELETED
* SKILLBASE_CATALOG_CREDENTIAL_UPDATED

* SKILLBASE_CATALOG_SKILL_CREATED
* SKILLBASE_CATALOG_SKILL_DELETED
* SKILLBASE_CATALOG_SKILL_UPDATED


**Subscribed Events:**

TBD


See Backend / Design for common design elements.
