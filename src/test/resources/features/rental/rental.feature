@rental @smoke @regression
Feature: Rental records retrieval

  Background:
    Given The database is accessible

  Scenario: Validate that rental records retrieval is successfull
    When I request the rental records
    Then Validate that the size of rental records is 16044

  Scenario Outline: Validate the rental records with id
    When I request the rental records with valid id <id>
    Then Validate that rental date is "<rental-date>" and return date is "<return-date>"
    Examples:
      | id   | rental-date         | return-date         |
      | 127  | 2005-05-25 21:10:40 | 2005-06-02 21:38:40 |
      | 3445 | 2005-06-21 20:40:28 | 2005-06-29 00:47:28 |
      | 184  | 2005-05-26 05:29:49 | 2005-05-28 10:10:49 |
      | 5265 | 2005-07-09 14:15:01 | 2005-07-18 14:43:01 |

   Scenario: Validate the number of rentals for staffs
     When I request for numbers of rentals per staffs
     Then Validate that the size of different staffs is 2
     And Validate that each staff rental details is following:
       | staff_id | count |
       | 1        | 8040  |
       | 2        | 8004  |