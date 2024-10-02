Feature: Testing a REST API
  Users should be able to submit GET and POST requests
  to a web service

  Scenario: Data Upload to a web service
    Given a running web service
    When users upload data on a project
    Then the server should handle it and return a success status
