Feature: Categories REST Endpoint
  Users should be able to interact with REST service endpoint

  Scenario: Insert a valid category
    Given a running categories endpoint
    When users insert a valid category
    Then the server should return the inserted category

  Scenario: Insert an invalid category
    Given a running categories endpoint
    When users insert an invalid category
    Then the server should return an invalid category failure

  Scenario: Update an existant valid category
    Given a running categories endpoint
    When users update an existant valid category
    Then the server should return the updated category

  Scenario: Update an existant invalid category
    Given a running categories endpoint
    When users update an existant invalid category
    Then the server should return an invalid category failure

  Scenario: Update a non-existant category
    Given a running categories endpoint
    When users update a non-existant category
    Then the server should return failure

  Scenario: Delete an existant category
    Given a running categories endpoint
    When users delete an existant category
    Then the server should return success

  Scenario: Delete a non-existant category
    Given a running categories endpoint
    When users delete a non-existant category
    Then the server should return failure

  Scenario: Find an existant category
    Given a running categories endpoint
    When users find an existant category
    Then the server should return the requested category

  Scenario: Find a non-existant category
    Given a running categories endpoint
    When users find a non-existant category
    Then the server should return failure

  Scenario: Find all categories
    Given a running categories endpoint
    When users find all categories
    Then the server should return all categories
