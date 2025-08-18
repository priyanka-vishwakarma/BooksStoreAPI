@signup
Feature: User Signup

  Scenario: Sign up and login with stored credentials
    Given the API is available
    When I sign up with stored credentials
    Then the signup should be successful or user already exists
    When I login with stored credentials
    Then I should receive a valid token

  Scenario: Sign up with an already registered email
    Given the API is available
    When I sign up with an existing email
    Then the API should return status 400
    And the response should indicate "Email already registered"

  Scenario: Sign up with invalid email format
    Given the API is available
    When I sign up with email "invalidEmail" and password "ValidPass123"
    Then the API should return status 400

  Scenario: Sign up with missing password
    Given the API is available
    When I sign up with email "newuser@test.com" and no password
    Then the API should return status 500
