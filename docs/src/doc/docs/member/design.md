**Design:**

The member service is responsible for managing users, groups, and achievements.
Users represent member profiles. Users can be members of Groups. Achievements
are associated with a user.

The member service delegates some of its functionality to a third-party IAM
(e.g. Keycloak) for authentication, authorization, and JWT tokens.

File storage, for images and BPMN models, is based on a POSIX filesystem
provided by the JuiceFS filesystem. The member service will use the following
directory layout. File names will be based on UUIDs and MIME types will be
handled by file extensions.

```
skillbase
    member
        <user-uuid>
            <file-uuid.<file-extension>>
            ...
        ...
```

**Domain Entities:**

* MemberAchievement
* MemberGroup
* MemberProcess
* MemberUser

**Domain Events:**

* SKILLBASE_MEMBER_ACHIEVEMENT_CREATED
* SKILLBASE_MEMBER_ACHIEVEMENT_DELETED
* SKILLBASE_MEMBER_ACHIEVEMENT_UPDATED

* SKILLBASE_MEMBER_GROUP_CREATED
* SKILLBASE_MEMBER_GROUP_DELETED
* SKILLBASE_MEMBER_GROUP_UPDATED

* SKILLBASE_MEMBER_PROCESS_CREATED
* SKILLBASE_MEMBER_PROCESS_DELETED
* SKILLBASE_MEMBER_PROCESS_UPDATED

* SKILLBASE_MEMBER_USER_CREATED
* SKILLBASE_MEMBER_USER_DELETED
* SKILLBASE_MEMBER_USER_UPDATED


**Subscribed Events**

SKILLBASE_WORKFLOW_INSTANCE_CREATED:
In response to a workflow instance created event, the member service creates a corresponding
MemberProcess entity.

SKILLBASE_WORKFLOW_INSTANCE_DELETED:
In response to a workflow instance delete event, the member service deletes its corresponding MemberProcess entity.

SKILLBASE_WORKFLOW_INSTANCE_UPDATED:
In response to a workflow instance updated event, the member service updates its corresponding
MemberProcess entity.


SKILLBASE_WORKFLOW_PROCESS_STARTED:
TBD

SKILLBASE_WORKFLOW_PROCESS_STOPPED:
TBD

SKILLBASE_WORKFLOW_PROCESS_UPDATED:
TBD

SKILLBASE_WORKFLOW_PROCESS_PASSED:
In response to a process pass event, the member service creates a corresponding
MemberAchievement entity.

SKILLBASE_WORKFLOW_PROCESS_FAILED:
TBD


See Backend / Design for common design elements.
