@books
Feature: Books API CRUD Operations

  Background:
    Given the API is available
    When I login with stored credentials
    Then I should receive a valid token

  Scenario: Book creation
    When I create a book with the following details
      | id  | name            | author        | published_year | book_summary        |
      | 1   | Effective Java  | Joshua Bloch  | 2018           | Best Java practices |
    Then the book should be created successfully
    And I should receive a valid book ID

#Retrieve created book
    When I retrieve the book by ID "1"
    Then the book details should match
      | name            | author        | published_year | book_summary        |
      | Effective Java  | Joshua Bloch  | 2018           | Best Java practices |

# Update the book
    When I update the book with the following details
      | name             | author        | published_year | book_summary          |
      | Updated Java     | Joshua Bloch  | 2019           | Updated book summary  |
    Then the book should be updated successfully

# Retrieve updated book
    When I retrieve the book by ID "1"
    Then the book details should match
      | name             | author        | published_year | book_summary          |
      | Updated Java     | Joshua Bloch  | 2019           | Updated book summary  |

# Delete the book
    When I delete the book by the saved ID
    Then the book should be deleted successfully

# Confirm deletion
    When I retrieve the book by ID "1"
    Then the API should return a 404
    And the response should indicate that the book was not found
