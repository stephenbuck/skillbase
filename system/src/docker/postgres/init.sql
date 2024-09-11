ALTER USER postgres WITH REPLICATION;
SELECT 'CREATE DATABASE skillbase'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'skillbase')\gexec
