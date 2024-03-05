@filmdetails @smoke @regression
Feature: Film details records retrieval

  Background:
    Given The database is accessible

  Scenario: Validate that film details records retrieval is successful
    When I request the film details records
    Then Validate that the size of film details records is 997
    And Validate that for all films number of actors should be greater than zero
    And Validate that release years of films can not be null
    And Validate that category of films can not be null
    And Validate that film titles should be unique


  Scenario Outline: Validate that film details which is choosen by title retrieval is successful
    When I request the film details with title "<title>"
    Then Validate that for specified film film actors list should be like "<actor-list>"
    Examples:
      | title            | actor-list                                                                                                                                     |
      | Academy Dinosaur | Penelope Guiness, Christian Gable, Lucille Tracy, Sandra Peck, Johnny Cage, Mena Temple, Warren Nolte, Oprah Kilmer, Rock Dukakis, Mary Keitel |
      | Ace Goldfinger   | Bob Fawcett, Minnie Zellweger, Sean Guiness, Chris Depp                                                                                        |
      | Adaptation Holes | Nick Wahlberg, Bob Fawcett, Cameron Streep, Ray Johansson, Julianne Dench                                                                      |
      | Affair Prejudice | Jodie Degeneres, Scarlett Damon, Kenneth Pesci, Fay Winslet, Oprah Kilmer                                                                      |
      | African Egg      | Gary Phoenix, Dustin Tautou, Matthew Leigh, Matthew Carrey, Thora Temple                                                                       |
      | Agent Truman     | Kirsten Paltrow, Sandra Kilmer, Jayne Neeson, Warren Nolte, Morgan Williams, Kenneth Hoffman, Reese West                                       |
      | Airplane Sierra  | Jim Mostel, Richard Penn, Oprah Kilmer, Mena Hopper, Michael Bolger                                                                            |
      | Airport Pollock  | Fay Kilmer, Gene Willis, Susan Davis, Lucille Dee                                                                                              |
      | Alabama Devil    | Christian Gable, Elvis Marx, Rip Crawford, Mena Temple, Rip Winslet, Warren Nolte, Greta Keitel, William Hackman, Meryl Allen                  |
      | Aladdin Calendar | Alec Wayne, Judy Dean, Val Bolger, Ray Johansson, Renee Tracy, Jada Ryder, Greta Malden, Rock Dukakis                                          |


