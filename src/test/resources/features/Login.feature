@login
Feature: User Login

  Scenario: Login with incorrect password
    Given the API is available
    When I login with email "validuser" and password "WrongPassword"
    Then the API should return status 400
    And the response should indicate "Incorrect email or password"

  Scenario: Login with unregistered email
    Given the API is available
    When I login with email "unregistered@test.com" and password "SomePassword"
    Then the API should return status 400
    And the response should indicate "Incorrect email or password"

  Scenario: Login with missing email
    Given the API is available
    When I login with no email and password "SomePassword"
    Then the API should return status 400
    And the response should indicate "Incorrect email or password"
