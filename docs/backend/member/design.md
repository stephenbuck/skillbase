
Design:

The member service implements member profiles itself, but delegates to a third-party IAM for authentication, authorization, and JWT.


Domain Entities:

* MemberGroup
* MemberUser


Domain Events:

* SKILLBASE_MEMBER_GROUP_INSERTED
* SKILLBASE_MEMBER_GROUP_DELETED
* SKILLBASE_MEMBER_GROUP_UPDATED

* SKILLBASE_MEMBER_USER_INSERTED
* SKILLBASE_MEMBER_USER_DELETED
* SKILLBASE_MEMBER_USER_UPDATED


See Backend / Design for common design elements.

