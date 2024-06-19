Overview

SkillBase is an application that allows an organization to develop and track the skill sets of its members. Organizations like schools, employers, or the military. The primary elements of the application are users, skills, and certifications. Users select skills and then follow a workflow to be granted certification. Users can search for other users that have specific skills. There is lots of room for integration with third-party applications for identity management, content management, social media, etc.

Backend

Domain-Driven Design

I’ve become a big fan of domain-driven design, primarily because it forces the architecture to focus on the business on its terms. I’m particularly interested in the intersection between DDD, microservices, and GraphQL. I’m going to use a GraphQL schema to represent the domain model and it should serve as a “single source of truth” from which other artifacts, such as SQL schemas and Java objects, can be created.

Event-Driven Architecture

An event-driven architecture is such a natural way of looking at applications that it’s hard to choose any other architecture. I’ve been doing some form of event-driven development for most of my career, so it’s gratifying to see it being so widely used these days. I’ll be using Kafka for the message broker and Cloud-Events for the event schemas.

Microservice Implementation

Past iterations have been implemented using a handful of services combined into one monolithic application, but there are definitely advantages to having a more fine-grained, microservice architecture and most new systems are being built this way. I’ll be using a combination of Jakarta EE and Spring Boot as they are the most popular microservice frameworks for Java.

Jakarta EE

Jakarta EE is the latest incarnation of the J2EE framework. I have extensive experience with J2EE so it will be interesting to see how the framework has advanced. I expect to use Jakarta EE, especially the MicroProfile, for the core framework.

Spring Boot

Spring Boot is more or less the go-to solution for Java microservices. I expect to use Spring Boot primarily for configuration and deployment.

Java

I’ve used Java for every backend implementation of this project so far and can’t see any compelling reason to switch over to something like Python, JavaScript, especially given the recent evolution of Java. I may use another language in subsequent iterations, but Java has changed so much in recent years that I really need to get some hands-on time with the new features like closures, records, etc.

GraphQL

One thing I’ve learned from all the reading I’ve been doing lately is that GraphQL is here to stay. It’s such a huge improvement over clumsy old REST that it’s hard to not use it. I expect that it will improve performance and simplify code, but what I’m most excited about is that can be used as a “single source of truth” when doing domain-driven design. Once a design has been captured in a GraphQL schema, it can be used to generate a variety of other artifacts like value objects, SQL tables, etc. It’s makes it much easier to keep everything in synch. I’m probably going to include a REST API as well, as it’s still very popular.

Kafka

I’ll be using the Kafka as the backbone of the application as it’s the 500-lb gorilla of message brokers and has performance to spare. It will also make the application more flexible, and easy to integrate into existing systems. I’ve got a lot of experience with message queueing and pub-sub systems, so I’m looking forward to using this. The event schemas will be based on Cloud-Events.

Docker

Containers are here to stay, and Docker is the gold standard. Having a good pipeline from the build system to a Docker system will help with making the application available in the “cloud” and when used with an orchestration system like Kubernetes, should help enormously with scaling the system up. There aren’t any alternatives that are as popular and flexible as Docker, so this is a keeper.

PostgreSQL

I’ve used the PostgreSQL database many times and, although it’s tempting to go all-in with a more modern NoSQL database, I think I’ll stick with a relational database at least for the primary datastore. Nobody ever got fired for recommending SQL and this should make the application easier to use in a variety of environments. I’m open to the possibility of using a NoSQL database, like MongoDB or CouchDB, for some parts of the system, especially on the “response” side.

Activiti

A key component of SkillBase is its ability to execute different workflows for each skill certification. Some certifications have no requirements and others may require complex steps and approvals. I’m going to use BPMN to let users model workflows and the Activiti engine will drive the processes. I’ll be using a web-based diagram editor for the BPMN modeler. Activiti works well with PostgreSQL, so it’ll fit right in.

Redis

I love Redis! It’s easy to use as a caching layer and it’s crazy fast. What’s not to love? There are plenty of other alternatives, like memcached and Hazelcast, but I don’t see anything better than Redis for this application.

Liquibase

I’ve used Liquibase for SQL database change management in a number of projects and really like its flexibility. I’ll be using it for this project to keep the multiple databases used for microservices in sync.

JUnit

I’ll be using JUnit 5 for unit testing. Not much new here.

ArchUnit

I recently discovered the ArchUnit framework and it’s useful for making sure the architecture of the project matches the requirements.

Arquillian

I’ll be using Arquillian for integration testing. I’ve never used it before, but it seems like it will handle the distributed components of the project.

Frontend

I’ve done heaps of frontend development work in the past, but I’m more focused on backend development these days, so I’m going to go with the most popular choices. In general, I’ll be developing a single-page style system based on the React framework.

React

I’ve worked with a lot of JavaScript frameworks like Angular, JQuery and Backbone, but React seems to be the framework du jour, so I’ll go with it. The React Router package seems like it’s pretty straightforward to use.

GraphQL

The backend will support GraphQL and REST so the client will have to follow along. I’ll be using the Relay client library since it’s designed for the React framework.

More coming soon!
