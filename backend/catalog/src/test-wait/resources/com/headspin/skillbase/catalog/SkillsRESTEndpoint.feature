Feature: Skills REST Endpoint
  Users should be able to interact with REST service endpoint

  Scenario: Insert a valid skill
    Given a running skills endpoint
    When users insert a valid skill
    Then the server should return the inserted skill

  Scenario: Insert an invalid skill
    Given a running skills endpoint
    When users insert an invalid skill
    Then the server should return an invalid skill failure

  Scenario: Update an existant valid skill
    Given a running skills endpoint
    When users update an existant valid skill
    Then the server should return the updated skill

  Scenario: Update an existant invalid skill
    Given a running skills endpoint
    When users update an existant invalid skill
    Then the server should return an invalid skill failure

  Scenario: Update a non-existant skill
    Given a running skills endpoint
    When users update a non-existant skill
    Then the server should return failure

  Scenario: Delete an existant skill
    Given a running skills endpoint
    When users delete an existant skill
    Then the server should return success

  Scenario: Delete a non-existant skill
    Given a running skills endpoint
    When users delete a non-existant skill
    Then the server should return failure

  Scenario: Find an existant skill
    Given a running skills endpoint
    When users find an existant skill
    Then the server should return the requested skill

  Scenario: Find a non-existant skill
    Given a running skills endpoint
    When users find a non-existant skill
    Then the server should return failure

  Scenario: Find all skills
    Given a running skills endpoint
    When users find all skills
    Then the server should return all skills
