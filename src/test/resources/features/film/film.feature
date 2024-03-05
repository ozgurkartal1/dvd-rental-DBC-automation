@film @smoke @regression
Feature: Film records retrieval

  Background:
    Given The database is accessible

  Scenario: Validate that film records retrieval is successful
    When I request the film records
    Then Validate that the size of film records is 1000

  Scenario Outline: Validate the film records with id
    When I request the film records with valid id <id>
    Then Validate that film title is "<title>" and release year is <year>

    Examples:
      | id  | title           | year |
      | 133 | Chamber Italian | 2006 |
      | 15  | Alien Center    | 2006 |
      | 184 | Core Suit       | 2006 |

  Scenario: Validate that film last update retrieval is successfull
    When I request for the last updates of film records
    Then All records of last updates size should be 1000
    And Validate that all last updates can not be null

