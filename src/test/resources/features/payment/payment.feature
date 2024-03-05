@film @smoke @regression
Feature: Payment records retrieval

  Background:
    Given The database is accessible

  Scenario: Validate that payment records retrieval is successful
    When I request the payment records
    Then the size of payment records should be 14596
    And payment ids should be unique

  Scenario: Validate that payment records retrieval with payment id
    When I request with three payment id as  17503, 17504, 17505
    Then the payment record is in the following:
      | payment_id | customer_id | staff_id | rental_id | amount | payment_date               |
      | 17503      | 341         | 2        | 1520      | 7.99   | 2007-02-15 22:25:46.996577 |
      | 17504      | 341         | 1        | 1778      | 1.99   | 2007-02-16 17:23:14.996577 |
      | 17505      | 341         | 1        | 1849      | 7.99   | 2007-02-16 22:41:45.996577 |
    And the size of payment records should be 3

  Scenario: Validate that the count of payment for different month
    When I request the count of payment records for different months name
    Then the size of payment records should be 4
    And the payment records should match with following details:
      | month    | count |
      | April    | 6754  |
      | March    | 5644  |
      | February | 2016  |
      | May      | 182   |









