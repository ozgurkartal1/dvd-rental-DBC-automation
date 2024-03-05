@staff @smoke @regression
Feature: Staff records retrieval

  Background:
    Given The database is accessible

  Scenario: Validate that staff records retrieval is successful
    When I request the staff records
    Then Validate that the size of staff records is 2
    And The staff emails must contain staff firstname and lastname

  Scenario Outline: Validate that staff records retrieval with staff id
    When I request the staff records with id <staff-id>
    Then Validate that specified staff's firstname "<first-name>" and lastname "<last-name>"
    Examples:
      | staff-id | first-name | last-name |
      | 1        | Mike       | Hillyer   |
      | 2        | Jon        | Stephens  |

    Scenario: Validate staff working activity
      When I request the activity records for all staffs
      Then validate that the all staffs are active to work
      And Validate that the size of staff records is 2
