Feature: Credentials REST Endpoint
  Users should be able to interact with REST service endpoint

  Scenario: Insert a valid credential
    Given a running credentials endpoint
    When users insert a valid credential
    Then the server should return the inserted credential

  Scenario: Insert an invalid credential
    Given a running credentials endpoint
    When users insert an invalid credential
    Then the server should return an invalid credential failure

  Scenario: Update an existant valid credential
    Given a running credentials endpoint
    When users update an existant valid credential
    Then the server should return the updated credential

  Scenario: Update an existant invalid credential
    Given a running credentials endpoint
    When users update an existant invalid credential
    Then the server should return an invalid credential failure

  Scenario: Update a non-existant credential
    Given a running credentials endpoint
    When users update a non-existant credential
    Then the server should return failure

  Scenario: Delete an existant credential
    Given a running credentials endpoint
    When users delete an existant credential
    Then the server should return success

  Scenario: Delete a non-existant credential
    Given a running credentials endpoint
    When users delete a non-existant credential
    Then the server should return failure

  Scenario: Find an existant credential
    Given a running credentials endpoint
    When users find an existant credential
    Then the server should return the requested credential

  Scenario: Find a non-existant credential
    Given a running credentials endpoint
    When users find a non-existant credential
    Then the server should return failure

  Scenario: Find all credentials
    Given a running credentials endpoint
    When users find all credentials
    Then the server should return all credentials
