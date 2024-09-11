echo '{
  "name": "skillbase-catalog",
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "plugin.name": "pgoutput",
    "tasks.max": "1",
    "database.hostname": "postgres",
    "database.port": "5432",
    "database.user": "postgres",
    "database.password": "postgres",
    "database.dbname": "skillbase",
    "database.server.name": "postgres",
    "schema.include.list": "skillbase",
    "table.include.list": "catalog.category, catalog.skill, catalog.credential, catalog.outbox",
    "topic.prefix": "skillbase-catalog-cdc"
  }
}' | http POST http://localhost:8083/connectors

echo '{
  "name": "skillbase-member",
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "plugin.name": "pgoutput",
    "tasks.max": "1",
    "database.hostname": "postgres",
    "database.port": "5432",
    "database.user": "postgres",
    "database.password": "postgres",
    "database.dbname": "skillbase",
    "database.server.name": "postgres",
    "schema.include.list": "skillbase",
    "table.include.list": "member.user, member.group, member.process, member.achievement, member.outbox",
    "topic.prefix": "skillbase-member-cdc"
  }
}' | http POST http://localhost:8083/connectors

echo '{
  "name": "skillbase-workflow",
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "plugin.name": "pgoutput",
    "tasks.max": "1",
    "database.hostname": "postgres",
    "database.port": "5432",
    "database.user": "postgres",
    "database.password": "postgres",
    "database.dbname": "skillbase",
    "database.server.name": "postgres",
    "schema.include.list": "skillbase",
    "table.include.list": "workflow.deployment, workflow.member, workflow.instance, workflow.task",
    "topic.prefix": "skillbase-workflow-cdc"
  }
}' | http POST http://localhost:8083/connectors

