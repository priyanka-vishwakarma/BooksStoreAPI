@books
Feature: Books API Negative Scenarios and Validations

  Background:
    Given the API is available
    When I login with stored credentials
    Then I should receive a valid token

  Scenario Outline: Fail to create a book with invalid input
    When I create a book with the following details
      | id   | name   | author   | published_year | book_summary |
      | <id> | <name> | <author> | <year>         | <summary>    |
    Then the API should return a <status>
    Examples:
      | id | name | author       | year | summary            | status |
      | 0  |      | Joshua Bloch | 2018 | Missing name field | 500    |
      | 2  | Java | John Doe     |      | Missing year value | 500    |


  Scenario: Fail to create a book with an existing ID
    Given a book already exists with the following details
      | id | name          | author     | published_year | book_summary      |
      | 1  | Java Patterns | Jane Smith | 2020           | Duplicate ID test |
    When I create a book with the same ID
      | id | name          | author     | published_year | book_summary  |
      | 1  | Java Advanced | Jane Smith | 2021           | Conflict case |
    Then the API should return a 500

  Scenario: Try to get a book with invalid ID
    When I retrieve the book by ID "100"
    Then the API should return a 404
    And the response should indicate that the book was not found
